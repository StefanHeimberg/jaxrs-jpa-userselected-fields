package com.example.myproject.flyway;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.flywaydb.core.Flyway;

/**
 *
 * @author Stefan Heimberg <kontakt@stefanheimberg.ch>
 */
@ApplicationScoped
public class FlywayMigrator {

    @Inject
    private Flyway flyway;

    public void handleMigration(@Observes @Initialized(ApplicationScoped.class) Object event) {
        flyway.migrate();
    }

}
