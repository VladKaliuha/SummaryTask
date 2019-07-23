package ua.nure.kaliuha.SummaryTask4.db;

import ua.nure.kaliuha.SummaryTask4.db.entity.User;

public enum Role {
    ADMIN, STUDENT;

    public static Role getRole(User user) {
        int roleId = user.getRoleId();
        return Role.values()[roleId];
    }

    public String getName() {
        return name().toLowerCase();
    }

}
