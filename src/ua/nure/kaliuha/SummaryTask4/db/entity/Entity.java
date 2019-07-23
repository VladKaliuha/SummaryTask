package ua.nure.kaliuha.SummaryTask4.db.entity;

import java.io.ObjectStreamClass;
import java.io.Serializable;

public class Entity implements Serializable {

    private static final long serialVersionUID = ObjectStreamClass
            .lookup(Entity.class)
            .getSerialVersionUID();

    private Long id;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
