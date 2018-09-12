package com.example.myproject.todo.business;

import static com.example.myproject.todo.business.TodoAttributeEnum.ID;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

/**
 *
 * @author Stefan Heimberg <kontakt@stefanheimberg.ch>
 */
@ApplicationScoped
public class TodoService {
    
    @Inject
    private EntityManager em;
    
    public void findTodoById(final Long id, final TodoLoadingCallback callback, final EnumSet<TodoAttributeEnum> attributes) throws TodoNotFoundException {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Tuple> cq = cb.createTupleQuery();

        final Root<Todo> todoRoot = cq.from(Todo.class);

        final EnumSet<TodoAttributeEnum> effectiveAttributes;
        if (null == attributes || attributes.isEmpty()) {
            effectiveAttributes = EnumSet.allOf(TodoAttributeEnum.class);
        } else {
            effectiveAttributes = attributes;
        }

        final ArrayList<Selection<?>> selectAttributes = new ArrayList<>(effectiveAttributes.size());

        final Path<Long> idPath = todoRoot.get(Todo_.id);
        final Path<String> summaryPath = todoRoot.get(Todo_.summary);
        final Path<String> descriptionPath = todoRoot.get(Todo_.description);
        final Path<LocalDateTime> insertTimestampPath = todoRoot.get(Todo_.insertTimestamp);
        final Path<LocalDateTime> updateTimestampPath = todoRoot.get(Todo_.updateTimestamp);

        effectiveAttributes.forEach(a -> {
            switch (a) {
                case ID:
                    selectAttributes.add(idPath);
                    break;
                case SUMMARY:
                    selectAttributes.add(summaryPath);
                    break;
                case DESCRIPTION:
                    selectAttributes.add(descriptionPath);
                    break;
                case INSERTTIMESTAMP:
                    selectAttributes.add(insertTimestampPath);
                    break;
                case UPDATETIMESTAMP:
                    selectAttributes.add(updateTimestampPath);
                    break;
                default:
                    throw new IllegalArgumentException("attribute " + a + " not implemented");
            }
        });

        cq.multiselect(selectAttributes.toArray(new Selection<?>[selectAttributes.size()]));

        cq.where(cb.equal(todoRoot.get(Todo_.id), id));

        final TypedQuery<Tuple> query = em.createQuery(cq);
        final List<Tuple> resultList = query.getResultList();

        if (resultList.isEmpty()) {
            throw new TodoNotFoundException();
        }

        final Tuple tuple = resultList.get(0);

        try {
            effectiveAttributes.forEach(a -> {
                switch (a) {
                    case ID:
                        callback.setId(tuple.get(idPath));
                        break;
                    case SUMMARY:
                        callback.setSummary(tuple.get(summaryPath));
                        break;
                    case DESCRIPTION:
                        callback.setDescription(tuple.get(descriptionPath));
                        break;
                    case INSERTTIMESTAMP:
                        callback.setInsertTimestamp(tuple.get(insertTimestampPath));
                        break;
                    case UPDATETIMESTAMP:
                        callback.setUpdateTimestamp(tuple.get(updateTimestampPath));
                        break;
                    default:
                        throw new IllegalArgumentException("attribute " + a + " not implemented");
                }
            });
        } catch (final IllegalArgumentException e) {
            tuple.getElements().stream().forEach(te -> {
                System.err.println(te.getAlias() + "(" + te.getJavaType() + ")=" + tuple.get(te));
            });
            throw e;
        }
    }
    
}
