package com.example.myproject.systemtests.helpers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author Stefan Heimberg <kontakt@stefanheimberg.ch>
 */
public final class AssertionsHelper {

    public static void assertEqualsStatus(final Response.Status expectedStatus, final Response response) {
        assertEquals(expectedStatus.getStatusCode(), response.getStatus());
    }

    public static void assertEqualsStatusMediaType(final Response.Status expectedStatus, final MediaType expectedMediaType, final Response response) {
        assertEquals(expectedStatus.getStatusCode(), response.getStatus());
        assertEquals(expectedMediaType, response.getMediaType());
    }

    private AssertionsHelper() {
    }

}
