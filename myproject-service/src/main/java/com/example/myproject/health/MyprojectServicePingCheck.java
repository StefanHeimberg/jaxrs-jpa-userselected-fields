package com.example.myproject.health;

import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

/**
 * Der MyprojectServicePingCheck ein zusätzliche check für die HealthCheck API welcher
 * mit "UP" bestätigt dass die Applikation deployed wurde. Dieser Check wird
 * unter anderem indirekt über die HEALTHCHECK direktive von Docker bei den
 * Systemtests verwendet.
 *
 * @author Stefan Heimberg <kontakt@stefanheimberg.ch>
 */
@Health
@ApplicationScoped
public class MyprojectServicePingCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named("myproject-service-ping-check")
                .up()
                .withData("pong", 1)
                .withData("timestamp", System.currentTimeMillis())
                .build();
    }

}
