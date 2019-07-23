package ua.nure.kaliuha.SummaryTask4.db.entity;

import java.io.ObjectStreamClass;

public class Result extends Entity {
    private static final long serialVersionUID = ObjectStreamClass
            .lookup(Result.class)
            .getSerialVersionUID();

    private int userId;
    private int testId;
    private int result;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "userId=" + userId +
                ", testId=" + testId +
                ", result=" + result +
                '}';
    }
}
