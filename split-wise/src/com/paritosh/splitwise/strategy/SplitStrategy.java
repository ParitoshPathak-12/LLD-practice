package com.paritosh.splitwise.strategy;

import com.paritosh.splitwise.model.Split;

import java.util.List;

public interface SplitStrategy {
    List<Split> createSplits(double amount, List<String> users, List<Double> values);
}
