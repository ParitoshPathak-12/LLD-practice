package com.paritosh.splitwise.strategy.impl;

import com.paritosh.splitwise.model.Split;
import com.paritosh.splitwise.strategy.SplitStrategy;

import java.util.ArrayList;
import java.util.List;

public class EqualSplitStrategy implements SplitStrategy {

    /*
    Since it is equal split strategy implementation, the values list will be empty.
    The amount will be equally distributed to each user: amount / size(users)
    */
    @Override
    public List<Split> createSplits(double amount, List<String> users, List<Double> values) {
        int usersCount = users.size();
        double amountPerUser = amount / (double) usersCount;

        List<Split> splits = new ArrayList<>();

        for (String user: users) {
            Split split = new Split(user, amountPerUser);
            splits.add(split);
        }

        return splits;
    }
}
