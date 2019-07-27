package ua.nure.kaliuha.SummaryTask4.db.entity;

import java.io.ObjectStreamClass;

public class Answer extends Entity {
    private static final long serialVersionUID = ObjectStreamClass
            .lookup(Answer.class)
            .getSerialVersionUID();

    private String text;
    private boolean correct;
    private long questionId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getCorrect() {
        return correct;
    }

    public void setCorrect(boolean right) {
        correct = right;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "text='" + text + '\'' +
                ", isRight=" + correct +
                ", questionId=" + questionId +
                '}';
    }
}
