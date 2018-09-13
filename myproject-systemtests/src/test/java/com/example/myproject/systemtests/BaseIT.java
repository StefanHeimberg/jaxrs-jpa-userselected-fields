package com.example.myproject.systemtests;

import com.example.myproject.systemtests.helpers.ClientHelper;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

/**
 *
 * @author Stefan Heimberg <kontakt@stefanheimberg.ch>
 */
public abstract class BaseIT {

    protected static Client client;
    protected static String serviceLocation;

    @BeforeAll
    static void init() throws NoSuchAlgorithmException, KeyManagementException {
        // location ist default auf https://localhost:8443/ für die lokale entwicklung
        serviceLocation = System.getProperty("myproject-service.location", "https://localhost:8443/");
        client = ClientHelper.newClient(true);
    }

    @AfterAll
    static void destroy() {
        client.close();
    }
    
    protected Client client() {
        return client;
    }
    
    protected WebTarget serviceTarget() {
        return client.target(serviceLocation);
    }
    
    protected WebTarget baseTarget() {
        return client.target(serviceLocation).path("/api/latest");
    }
    
}
