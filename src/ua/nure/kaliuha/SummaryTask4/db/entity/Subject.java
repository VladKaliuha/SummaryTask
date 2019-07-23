package ua.nure.kaliuha.SummaryTask4.db.entity;

import java.io.ObjectStreamClass;

public class Subject extends Entity {
    private static final long serialVersionUID = ObjectStreamClass
            .lookup(Subject.class)
            .getSerialVersionUID();

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                '}';
    }
}
