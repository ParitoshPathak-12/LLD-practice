package com.paritosh.splitwise.service;

import com.paritosh.splitwise.enums.SplitTypeEnum;
import com.paritosh.splitwise.factory.SplitStrategyFactory;
import com.paritosh.splitwise.model.Split;
import com.paritosh.splitwise.strategy.SplitStrategy;

import java.util.List;

public class ExpenseService {

    private final BalanceService balanceService;
    private final SplitStrategyFactory factory;


    public ExpenseService(BalanceService balanceService, SplitStrategyFactory factory) {
        this.balanceService = balanceService;
        this.factory = factory;
    }

    public String addExpense(
            String paidBy,
            double amount,
            List<String> users,
            SplitTypeEnum splitTypeEnum,
            List<Double> values
    ) {
        SplitStrategy strategy = factory.getStrategy(splitTypeEnum);
        List<Split> splits = strategy.createSplits(amount, users, values);
        balanceService.updateBalances(paidBy, splits);
        return "expense added successfully";
    }

}
