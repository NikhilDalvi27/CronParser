package com.parser.operators;

import com.parser.TimeUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

class Set extends Operator{

    Set(TimeUnit timeUnit) {
        super(timeUnit);
    }

    @Override
    public List<Integer> generateValues() {
        String []expressions = timeUnit.getExpression().split(",");
        TreeSet<Integer> set = new TreeSet<>();
        for(String expression : expressions) {
            List<Integer>values = Operator.getConcreteOperator(new TimeUnit(expression,timeUnit.getTimeUnitType()) {
                @Override
                public Integer getMin() {
                    return timeUnit.getMin();
                }

                @Override
                public Integer getMax() {
                    return timeUnit.getMax();
                }

                @Override
                public String getExpression() {
                    return expression;
                }
            }).generateValues();
            set.addAll(values);
        }
        return new ArrayList<>(set);
    }
}