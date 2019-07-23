package ua.nure.kaliuha.SummaryTask4.db.entity;

import ua.nure.kaliuha.SummaryTask4.db.DBManager;
import ua.nure.kaliuha.SummaryTask4.exeption.DBException;

import java.io.ObjectStreamClass;

public class Test extends Entity {

    private static final long serialVersionUID = ObjectStreamClass
            .lookup(Test.class)
            .getSerialVersionUID();

    private String name;
    private int subjectId;
    private int complexity;
    private int size;
    private int time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubject_id(int subject_id) {
        this.subjectId = subject_id;
    }

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize() {
        try {
            this.size = DBManager.getInstance().findQuestionByTestId(this.getId()).size();
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Test{" +
                "name='" + name + '\'' +
                ", subjectId=" + subjectId +
                ", complexity=" + complexity +
                ", size=" + size +
                ", time=" + time +
                '}';
    }
}
