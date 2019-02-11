/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package features;

import app.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class UserFeature {
    @Autowired
    private MockMvc endpoints;

    @Test
    public void assertThatAClientCanCreateAnUser() throws Exception {
        final String json = "{ \"name\": \"Gabriel\", \"age\": 26 }";

        endpoints
                .perform(
                        post("/users")
                                .contentType("application/json")
                                .content(json)
                )
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    public void assertThatAClientCanFetchAnExistingUser() throws Exception {
        final String json = "{ \"name\": \"Gabriel\", \"age\": 26 }";

        final String location = endpoints
                .perform(
                        post("/users")
                                .contentType("application/json")
                                .content(json)
                )
                .andReturn()
                .getResponse()
                .getHeader("Location");

        assertNotNull(location);

        endpoints
                .perform(get(location))
                .andExpect(status().isOk());
    }
}
