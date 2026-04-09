package com.paritosh.splitwise.constants;

public final class ApplicationConstants {
    private ApplicationConstants() {

    }

    public static final String PRE = "> ";
    public static final String ADD_USER = "ADD_USER";
    public static final String EXPENSE = "EXPENSE";
    public static final String SHOW = "SHOW";
    public static final String HELP = "help";

    public static final String ADD_USER_COMMAND = "ADD_USER <userId> <userName>";
    public static final String ADD_EXPENSE_COMMAND1 = "EXPENSE <paidBy> <amount> <numParticipants> <participant1> <participant2> ... EQUAL";
    public static final String ADD_EXPENSE_COMMAND2 = "EXPENSE <paidBy> <amount> <numParticipants> <participant1> <participant2> ... EXACT <share1> <share2> ...";
    public static final String SHOW_EXPENSES_COMMAND1 = "SHOW";
    public static final String SHOW_EXPENSES_COMMAND2 = "SHOW <userId>";
}
