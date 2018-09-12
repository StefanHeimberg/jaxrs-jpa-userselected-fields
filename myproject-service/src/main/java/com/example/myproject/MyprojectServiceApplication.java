package com.example.myproject;

import com.example.myproject.todo.api.TodoCollectionResource;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author Stefan Heimberg <kontakt@stefanheimberg.ch>
 */
@ApplicationPath("/api/latest")
public class MyprojectServiceApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet();
        
        // add root resources
        classes.add(TodoCollectionResource.class);
        
        return classes;
    }
    
}
