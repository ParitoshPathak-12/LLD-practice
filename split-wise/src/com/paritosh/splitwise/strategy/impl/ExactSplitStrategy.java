package com.paritosh.splitwise.strategy.impl;

import com.paritosh.splitwise.model.Split;
import com.paritosh.splitwise.strategy.SplitStrategy;

import java.util.ArrayList;
import java.util.List;

public class ExactSplitStrategy implements SplitStrategy {

    /*
    Since, it is exact split implementation,
    the size of users and values lists will be same.
    And users_i will be associated with values_i
    */
    @Override
    public List<Split> createSplits(double amount, List<String> users, List<Double> values) {
        int count = users.size(); /* users.size() will be equal to values.size() */

        List<Split> splits = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Split split = new Split(users.get(i), values.get(i));
            splits.add(split);
        }

        return splits;
    }
}
