package com.paritosh.splitwise.controller;

import com.paritosh.splitwise.constants.ApplicationConstants;
import com.paritosh.splitwise.dto.BalanceDto;
import com.paritosh.splitwise.enums.SplitTypeEnum;
import com.paritosh.splitwise.service.BalanceService;
import com.paritosh.splitwise.service.ExpenseService;
import com.paritosh.splitwise.service.UserService;
import static com.paritosh.splitwise.constants.ApplicationConstants.ADD_USER;
import static com.paritosh.splitwise.constants.ApplicationConstants.PRE;
import static com.paritosh.splitwise.constants.ApplicationConstants.EXPENSE;
import static com.paritosh.splitwise.constants.ApplicationConstants.SHOW;
import static com.paritosh.splitwise.constants.ApplicationConstants.HELP;

import java.util.ArrayList;
import java.util.List;

public class ExpenseController {
    private final UserService userService;
    private final ExpenseService expenseService;
    private final BalanceService balanceService;

    public ExpenseController(
            UserService userService,
            ExpenseService expenseService,
            BalanceService balanceService
    ) {
        this.balanceService = balanceService;
        this.expenseService = expenseService;
        this.userService = userService;
    }

    public void handleCommand(String userCommand) {
        String[] splittedCommands = userCommand.split(" ");

        if (ADD_USER.equalsIgnoreCase(splittedCommands[0])) {
            handleAddUser(splittedCommands);
        } else if (EXPENSE.equalsIgnoreCase(splittedCommands[0])) {
            handleExpense(splittedCommands);
        } else if (SHOW.equalsIgnoreCase(splittedCommands[0])) {
            handleShowExpenses(splittedCommands);
        } else if (HELP.equalsIgnoreCase(splittedCommands[0])) {
            handleHelp();
        } else {
            System.out.println(PRE + "please enter a valid command");
        }
    }

    // ADD_USER <userId> <userName>
    private void handleAddUser(String[] tokens) {
        String userId = tokens[1];
        String userName = tokens[2];

        String msg = userService.addUser(userId, userName);

        System.out.println(PRE + msg);
    }

    // EXPENSE <paidBy> <amount> <numParticipants> <participant1> <participant2> ... <splitType>
    // EXPENSE <paidBy> <amount> <numParticipants> <participant1> <participant2> ... EXACT <share1> <share2> ...
    private void handleExpense(String[] tokens) {
        String paidBy = tokens[1];
        double amount = Double.parseDouble(tokens[2]);
        int numParticipants = Integer.parseInt(tokens[3]);

        List<String> userIds = new ArrayList<>();

        for (int i = 4; i < (4+numParticipants); i++) {
            userIds.add(tokens[i]);
        }

        SplitTypeEnum splitTypeEnum = SplitTypeEnum.valueOf(tokens[4 + numParticipants].toUpperCase());

        List<Double> values = new ArrayList<>();

        for (int i = 5 + numParticipants; i < tokens.length; i++) {
            values.add(Double.parseDouble(tokens[i]));
        }

        String msg = expenseService.addExpense(paidBy, amount, userIds, splitTypeEnum, values);

        System.out.println(PRE + msg);
    }

    // SHOW
    // SHOW <userId>
    private void handleShowExpenses(String[] tokens) {
        int size = tokens.length;

        List<BalanceDto> expenses;

        if (size == 1) {
            expenses = balanceService.getAllBalances();
        } else {
            String userId = tokens[1];
            expenses = balanceService.getBalancesOfAUser(userId);
        }
        printExpenses(expenses);
    }

    private void handleHelp() {
        System.out.println("use below commands...");
        System.out.println("-----------------------------------------------------------");
        System.out.println(ApplicationConstants.ADD_USER_COMMAND);
        System.out.println(ApplicationConstants.ADD_EXPENSE_COMMAND1);
        System.out.println(ApplicationConstants.ADD_EXPENSE_COMMAND2);
        System.out.println(ApplicationConstants.SHOW_EXPENSES_COMMAND1);
        System.out.println(ApplicationConstants.SHOW_EXPENSES_COMMAND2);
        System.out.println("-----------------------------------------------------------");
    }

    private void printExpenses(List<BalanceDto> balanceDtos) {
        for (BalanceDto b: balanceDtos) {
            System.out.println(PRE + b.getFrom() + " owes " + b.getTo() + ": " + b.getAmount());
        }
    }
}
