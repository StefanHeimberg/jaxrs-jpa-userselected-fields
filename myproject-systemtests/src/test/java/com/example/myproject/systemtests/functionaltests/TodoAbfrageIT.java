package com.example.myproject.systemtests.functionaltests;

import com.example.myproject.systemtests.BaseIT;
import static com.example.myproject.systemtests.helpers.AssertionsHelper.assertEqualsStatusMediaType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Stefan Heimberg <kontakt@stefanheimberg.ch>
 */
public class TodoAbfrageIT extends BaseIT {

    @Test
    @Disabled
    public void test() {
        final Response response = baseTarget().path("/todos").request().get();
        assertEqualsStatusMediaType(Response.Status.OK, MediaType.APPLICATION_JSON_TYPE, response);
    }
    
}
