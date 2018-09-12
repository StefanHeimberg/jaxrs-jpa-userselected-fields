package com.example.myproject.todo.api;

import com.example.myproject.todo.business.TodoService;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Stefan Heimberg <kontakt@stefanheimberg.ch>
 */
@Path("/todos")
@ApplicationScoped
public class TodoCollectionResource {
    
    @Inject
    private TodoService todoService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTodos() {
        // TODO implement
        return Response.serverError().build();
    }
    
    @Path("/{id}")
    public TodoResource getTodoById(@PathParam("id") Long id) {
        return new TodoResource(todoService, id);
    }
    
}
