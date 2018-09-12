package com.example.myproject.todo.business;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 *
 * @author Stefan Heimberg <kontakt@stefanheimberg.ch>
 */
@Entity
@Table(name = "T_TODO")
public class Todo implements Serializable {

    @Id
    @Column(name = "ID", updatable = false)
    private Long id;

    @Basic(optional = false)
    @Column(name = "SUMMARY", nullable = false)
    private String summary;

    @Basic(optional = false)
    @Column(name = "DESCRIPTION", nullable = true)
    private String description;

    @Basic(optional = false)
    @Column(name = "INSERTTIMESTAMP", nullable = false, updatable = false)
    private LocalDateTime insertTimestamp;

    @Column(name = "UPDATETIMESTAMP", insertable = false)
    private LocalDateTime updateTimestamp;

    @Version
    @Column(name = "VERSION")
    private Integer version;

    @PrePersist
    protected void prePersist() {
        insertTimestamp = LocalDateTime.now();
    }

    @PreUpdate
    protected void preUpdate() {
        updateTimestamp = LocalDateTime.now();
    }

}
