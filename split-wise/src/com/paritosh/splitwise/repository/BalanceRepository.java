package com.paritosh.splitwise.repository;

import com.paritosh.splitwise.model.Split;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BalanceRepository {
    Map<String, Map<String, Double>> expenses = new HashMap<>();

    public void updateBalances(String paidBy, List<Split> splits) {

        for (Split split: splits) {
            String userId = split.getUserId();
            double amount = split.getAmount();

            // userId has to pay paidBy

            if (userId.equalsIgnoreCase(paidBy)) continue;

            double amountToBePaid = 0;

            if (expenses.containsKey(paidBy)) {
                Map<String, Double> paidByOwesTo = expenses.get(paidBy);
                if (paidByOwesTo.containsKey(userId)) {
                    double remAmount = paidByOwesTo.get(userId);
                    if (remAmount >= amount) {
                        remAmount = remAmount - amount;
                        paidByOwesTo.put(userId, remAmount);
                    } else {
                        amountToBePaid = amount - remAmount;
                        paidByOwesTo.remove(userId);
                    }
                } else {
                    amountToBePaid = amount;
                }
            } else {
                amountToBePaid = amount;
            }

            if (amountToBePaid > 0) {
                if (!expenses.containsKey(userId)) {
                    expenses.put(userId, new HashMap<>());
                }
                Map<String, Double> userIdOwesTo = expenses.get(userId);
                userIdOwesTo.put(paidBy, amountToBePaid);
            }
        }
    }

    public Map<String, Map<String, Double>> getAllBalances() {
        return expenses;
    }

    public Map<String, Map<String, Double>> getBalancesOfAUser(String userId) {
        Map<String, Map<String, Double>> userExpenses = new HashMap<>();
        for (String key: expenses.keySet()) {
            Map<String, Double> owesTo = expenses.get(key);
            if (key.equalsIgnoreCase(userId)) {
                userExpenses.put(key, owesTo);
            } else {
                Map<String, Double> record = new HashMap<>();
                for (String key2: owesTo.keySet()) {
                    if (key2.equalsIgnoreCase(userId)) {
                        record.put(key2, owesTo.get(key2));
                    }
                }
                userExpenses.put(key, record);
            }
        }
        return userExpenses;
    }

}
