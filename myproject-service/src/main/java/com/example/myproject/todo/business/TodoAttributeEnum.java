package com.example.myproject.todo.business;

/**
 *
 * @author Stefan Heimberg <kontakt@stefanheimberg.ch>
 */
public enum TodoAttributeEnum {

    ID("id"),
    SUMMARY("summary"),
    DESCRIPTION("description"),
    INSERTTIMESTAMP("inserttimestamp"),
    UPDATETIMESTAMP("updatetimestamp");

    public static TodoAttributeEnum valueOfId(final String id) {
        if (ID.getId().equals(id)) {
            return ID;
        } else if (SUMMARY.getId().equals(id)) {
            return SUMMARY;
        } else if (DESCRIPTION.getId().equals(id)) {
            return DESCRIPTION;
        } else if (INSERTTIMESTAMP.getId().equals(id)) {
            return INSERTTIMESTAMP;
        } else if (UPDATETIMESTAMP.getId().equals(id)) {
            return UPDATETIMESTAMP;
        } else {
            throw new IllegalArgumentException("todo attribute for id " + id + " not implemented");
        }
    }
    private String id;

    private TodoAttributeEnum(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
