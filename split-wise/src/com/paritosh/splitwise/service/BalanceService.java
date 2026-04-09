package com.paritosh.splitwise.service;

import com.paritosh.splitwise.dto.BalanceDto;
import com.paritosh.splitwise.model.Split;
import com.paritosh.splitwise.repository.BalanceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BalanceService {
    private final BalanceRepository balanceRepository;

    public BalanceService(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    public void updateBalances(String paidBy, List<Split> splits) {
        balanceRepository.updateBalances(paidBy, splits);
    }

    public List<BalanceDto> getAllBalances() {
        Map<String, Map<String, Double>> expenses = balanceRepository.getAllBalances();

        return createBalanceList(expenses);
    }

    public List<BalanceDto> getBalancesOfAUser(String userId) {
        Map<String, Map<String, Double>> expenses = balanceRepository.getAllBalances();

        return createBalanceList(expenses);
    }

    private List<BalanceDto> createBalanceList(Map<String, Map<String, Double>> expenses) {
        List<BalanceDto> balances = new ArrayList<>();

        for (String userId: expenses.keySet()) {
            Map<String, Double> userBalances = expenses.get(userId);
            for (String userId2: userBalances.keySet()) {
                double amt = userBalances.get(userId2);
                BalanceDto balanceDto = new BalanceDto(userId, userId2, amt);
                balances.add(balanceDto);
            }
        }

        return balances;
    }
}
