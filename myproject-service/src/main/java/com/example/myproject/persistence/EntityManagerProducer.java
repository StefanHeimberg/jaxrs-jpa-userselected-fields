package com.example.myproject.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Stefan Heimberg <kontakt@stefanheimberg.ch>
 */
@ApplicationScoped
public class EntityManagerProducer {
    
    @Produces
    @PersistenceContext
    private EntityManager em;
    
}
