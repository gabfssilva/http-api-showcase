package endpoint;

import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import repositories.UserRepository;

@RestController
public class UserEndpoint {
    private UserRepository repository;

    @Autowired
    public UserEndpoint(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping(
            path = "/users/{id}",
            produces = "application/json"
    )
    public ResponseEntity<User> fetchUserById(@PathVariable("id") Long id) {
        return repository
                .findById(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(
            path = "/users",
            produces = "application/json",
            consumes = "application/json"
    )
    public ResponseEntity<User> insertUser(User user) {
        final User savedUser = repository.save(user);

        final HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/users/" + savedUser.getId());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
