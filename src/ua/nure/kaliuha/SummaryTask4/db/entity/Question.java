package ua.nure.kaliuha.SummaryTask4.db.entity;

import java.io.ObjectStreamClass;

/**
 * @author Vlad Kaliuha
 */

public class Question extends Entity {

    private static final long serialVersionUID = ObjectStreamClass
            .lookup(Question.class)
            .getSerialVersionUID();

    private int testId;
    private String text;
    private int answerTypeId;


    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAnswerTypeId() {
        return answerTypeId;
    }

    public void setAnswerTypeId(int answerTypeId) {
        this.answerTypeId = answerTypeId;
    }

    @Override
    public String toString() {
        return "Question{" +
                "testId=" + testId +
                ", text='" + text + '\'' +
                ", answerTypeId=" + answerTypeId +
                '}';
    }
}
