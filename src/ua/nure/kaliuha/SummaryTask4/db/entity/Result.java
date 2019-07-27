package ua.nure.kaliuha.SummaryTask4.db.entity;

import java.io.ObjectStreamClass;

public class Result extends Entity {
    private static final long serialVersionUID = ObjectStreamClass
            .lookup(Result.class)
            .getSerialVersionUID();

    private long userId;
    private long testId;
    private int result;
    private String date;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    private String testName;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public long getTestId() {
        return testId;
    }

    public void setTestId(long testId) {
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
