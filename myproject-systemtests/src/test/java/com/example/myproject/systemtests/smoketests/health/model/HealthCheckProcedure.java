package com.example.myproject.systemtests.smoketests.health.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author Stefan Heimberg <kontakt@stefanheimberg.ch>
 */
// Json properties ignorieren welche hier nicht aufgelistet sind. Dies ist
// notwendig weil in der HealthCheck API noch das "data" Element existiert."
// Dieses Objekt jedoch ist dynamischer Natur und kann nicht mit statischen
// Mappings implementiert werden.
@JsonIgnoreProperties(ignoreUnknown = true)
public class HealthCheckProcedure {

    public String name;
    public String type;
    public HealthCheckState state;

}
