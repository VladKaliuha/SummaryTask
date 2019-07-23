package ua.nure.kaliuha.SummaryTask4.db.entity;

import java.io.ObjectStreamClass;

public class Answer extends Entity {
    private static final long serialVersionUID = ObjectStreamClass
            .lookup(Answer.class)
            .getSerialVersionUID();

    private String text;
    private boolean isRight;
    private int questionId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "text='" + text + '\'' +
                ", isRight=" + isRight +
                ", questionId=" + questionId +
                '}';
    }
}
