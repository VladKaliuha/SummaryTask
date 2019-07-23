package ua.nure.kaliuha.SummaryTask4.db;

import ua.nure.kaliuha.SummaryTask4.db.entity.Question;

public enum AnswerType {

    oneTrue, manyTrue, yourAnswer;
    public static AnswerType getAnswerType(Question question) {
        int typeId = question.getAnswerTypeId();
        return AnswerType.values()[typeId-1];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
