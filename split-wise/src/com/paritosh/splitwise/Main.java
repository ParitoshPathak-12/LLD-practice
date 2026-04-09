package com.paritosh.splitwise;

import com.paritosh.splitwise.controller.ExpenseController;
import com.paritosh.splitwise.factory.SplitStrategyFactory;
import com.paritosh.splitwise.repository.BalanceRepository;
import com.paritosh.splitwise.repository.UserRepository;
import com.paritosh.splitwise.service.BalanceService;
import com.paritosh.splitwise.service.ExpenseService;
import com.paritosh.splitwise.service.UserService;

import java.util.Scanner;

public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        SplitStrategyFactory factory = new SplitStrategyFactory();
        UserRepository userRepository = new UserRepository();
        BalanceRepository balanceRepository = new BalanceRepository();
        BalanceService balanceService = new BalanceService(balanceRepository);
        UserService userService = new UserService(userRepository);
        ExpenseService expenseService = new ExpenseService(balanceService, factory);
        ExpenseController expenseController = new ExpenseController(userService, expenseService, balanceService);

        System.out.println("splitwise application started, start interacting using commands, type help to see commands");

        while (true) {
            String userCommand = scanner.nextLine();
            if (userCommand.equalsIgnoreCase("exit")) {
                System.out.println("closing the application");
                System.exit(1);
            }
            expenseController.handleCommand(userCommand);
        }
    }
}
