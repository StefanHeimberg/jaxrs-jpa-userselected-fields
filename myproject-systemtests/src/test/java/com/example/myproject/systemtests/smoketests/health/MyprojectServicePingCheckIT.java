package com.example.myproject.systemtests.smoketests.health;

import com.example.myproject.systemtests.BaseIT;
import static com.example.myproject.systemtests.helpers.AssertionsHelper.assertEqualsStatusMediaType;
import com.example.myproject.systemtests.smoketests.health.model.Health;
import com.example.myproject.systemtests.smoketests.health.model.HealthCheckProcedure;
import com.example.myproject.systemtests.smoketests.health.model.HealthCheckState;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author Stefan Heimberg <kontakt@stefanheimberg.ch>
 */
public class MyprojectServicePingCheckIT extends BaseIT {

    @Test
    public void test() {
        final Response response = serviceTarget().path("/health").request().get();
        assertEqualsStatusMediaType(Response.Status.OK, MediaType.APPLICATION_JSON_TYPE, response);
        
        assertEquals(true, response.hasEntity());
        final Health health = response.readEntity(Health.class);

        assertNotNull(health.checks);

        final Optional<HealthCheckProcedure> kontoServicePingCheckOpt = health.checks.stream()
                .filter(check -> "myproject-service-ping-check".equals(check.name))
                .findFirst();

        assertTrue(kontoServicePingCheckOpt.isPresent(), "myproject-service-ping-check procedure not installed on remote side!");
        final HealthCheckProcedure kontoServiceCheck = kontoServicePingCheckOpt.get();

        assertSame(HealthCheckState.UP, kontoServiceCheck.state);
    }

}
