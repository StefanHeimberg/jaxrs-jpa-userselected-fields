package com.example.myproject.todo.api;

import com.example.myproject.json.JsonHelper;
import com.example.myproject.time.DateTimeUtil;
import com.example.myproject.todo.business.TodoAttributeEnum;
import com.example.myproject.todo.business.TodoLoadingCallback;
import com.example.myproject.todo.business.TodoNotFoundException;
import com.example.myproject.todo.business.TodoService;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Stefan Heimberg <kontakt@stefanheimberg.ch>
 */
public class TodoResource {

    private final TodoService todoService;
    private final Long id;

    public TodoResource(final TodoService todoService, final Long id) {
        this.todoService = todoService;
        this.id = id;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTodo(@QueryParam("fields") final String fields) {
        EnumSet<TodoAttributeEnum> attributes;
        try {
            attributes = enumSetOf(fields);
            if (null == attributes) {
                attributes = EnumSet.allOf(TodoAttributeEnum.class);
            }
        } catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }

        final JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        try {
            todoService.findTodoById(id, getTodoLoadingCallback(jsonObjectBuilder), attributes);
        } catch (final TodoNotFoundException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (final IllegalArgumentException ex) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        final JsonObject jsonObject = jsonObjectBuilder.build();
        final String jsonString = JsonHelper.toFormattedString(jsonObject);
        return Response.ok(jsonString).build();
    }

    private TodoLoadingCallback getTodoLoadingCallback(final JsonObjectBuilder jsonObjectBuilder) {
        return new TodoLoadingCallback() {
            @Override
            public void setId(final Long id) {
                jsonObjectBuilder.add(TodoAttributeEnum.ID.getId(), id.toString());
            }

            @Override
            public void setSummary(final String summary) {
                jsonObjectBuilder.add(TodoAttributeEnum.SUMMARY.getId(), summary);
            }

            @Override
            public void setDescription(final String description) {
                if (null != description) {
                    jsonObjectBuilder.add(TodoAttributeEnum.DESCRIPTION.getId(), description);
                }
            }

            @Override
            public void setInsertTimestamp(final LocalDateTime insertTimestamp) {
                jsonObjectBuilder.add(TodoAttributeEnum.INSERTTIMESTAMP.getId(), DateTimeUtil.toFormattedString(insertTimestamp));
            }

            @Override
            public void setUpdateTimestamp(final LocalDateTime updateTimestamp) {
                if (null != updateTimestamp) {
                    jsonObjectBuilder.add(TodoAttributeEnum.UPDATETIMESTAMP.getId(), DateTimeUtil.toFormattedString(updateTimestamp));
                }
            }
        };
    }

    public static EnumSet<TodoAttributeEnum> enumSetOf(final String ids) {
        if (null == ids) {
            return null;
        }

        final List<String> fieldIds = Arrays.asList(ids.trim().split(",")).stream()
                .map(id -> id.trim())
                .filter(id -> !id.isEmpty())
                .collect(Collectors.toList());

        return enumSetOf(fieldIds);
    }

    public static EnumSet<TodoAttributeEnum> enumSetOf(final List<String> ids) {
        if (null == ids) {
            return null;
        }

        if (ids.isEmpty()) {
            return EnumSet.allOf(TodoAttributeEnum.class);
        } else {
            return EnumSet.copyOf(ids.stream()
                    .filter(id -> !id.isEmpty())
                    .map(id -> TodoAttributeEnum.valueOfId(id))
                    .collect(Collectors.toList()));
        }
    }

}
