package com.paritosh.splitwise.factory;

import com.paritosh.splitwise.enums.SplitTypeEnum;
import com.paritosh.splitwise.strategy.SplitStrategy;
import com.paritosh.splitwise.strategy.impl.EqualSplitStrategy;
import com.paritosh.splitwise.strategy.impl.ExactSplitStrategy;

/*
This approach is good because in future if we want
to add more split strategies (like percent, etc.)
we just need to add one more case here, and create
the strategy, without modifying service class.
*/

public class SplitStrategyFactory {
    public SplitStrategy getStrategy(SplitTypeEnum splitType) {
        return switch (splitType) {
            case EQUAL -> new EqualSplitStrategy();
            case EXACT -> new ExactSplitStrategy();
        };
    }
}
