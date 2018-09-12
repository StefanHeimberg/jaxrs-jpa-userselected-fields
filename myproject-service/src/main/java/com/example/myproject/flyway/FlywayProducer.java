package com.example.myproject.flyway;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;

/**
 *
 * @author Stefan Heimberg <kontakt@stefanheimberg.ch>
 */
@ApplicationScoped
public class FlywayProducer {
    
    @Resource
    private DataSource dataSource;
    
    @Produces
    public Flyway produceFlyway() {
        final Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        return flyway;
    }
    
}
