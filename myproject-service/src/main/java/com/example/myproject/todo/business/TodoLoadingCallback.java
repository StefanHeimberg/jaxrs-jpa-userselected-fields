package com.example.myproject.todo.business;

import java.time.LocalDateTime;

/**
 *
 * @author Stefan Heimberg <kontakt@stefanheimberg.ch>
 */
public interface TodoLoadingCallback {

    void setId(Long id);

    void setSummary(String summary);

    void setDescription(String description);

    void setInsertTimestamp(LocalDateTime insertTimestamp);

    void setUpdateTimestamp(LocalDateTime updateTimestamp);

}
