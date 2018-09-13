package com.example.myproject.systemtests.functionaltests;

import com.example.myproject.systemtests.BaseIT;
import static com.example.myproject.systemtests.helpers.AssertionsHelper.assertEqualsStatusMediaType;
import static com.example.myproject.systemtests.helpers.AssertionsHelper.assertEqualsStatus;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Stefan Heimberg <kontakt@stefanheimberg.ch>
 */
public class TodoAbfrageIT extends BaseIT {

    @Test
    public void assert_todo_not_exists() {
        final Response response = baseTarget()
                .path("todos").path("99")
                .request().get();
        assertEqualsStatus(Response.Status.NOT_FOUND, response);
    }

    @Test
    public void assert_todo_exists() {
        final Response response = baseTarget()
                .path("todos").path("1111")
                .request().get();

        assertEqualsStatusMediaType(Response.Status.OK, MediaType.APPLICATION_JSON_TYPE, response);
        assertTrue(response.hasEntity());
    }
    
}
