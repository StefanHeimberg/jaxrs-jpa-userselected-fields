package com.example.myproject.systemtests.smoketests.health.model;

import java.util.List;

/**
 *
 * @author Stefan Heimberg <kontakt@stefanheimberg.ch>
 */
public class Health {
    
    public HealthCheckState outcome;
    public List<HealthCheckProcedure> checks;
    
}
