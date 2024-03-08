package com.parser.operators;

import com.parser.TimeUnit;

import java.util.List;

public abstract class Operator{

    protected static final String ANY = "*";
    protected static final String SET = ",";
    protected static final String STEP = "/";
    protected static final String RANGE = "-";
    protected static final String NUMBER = "$";
    TimeUnit timeUnit;



    Operator(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public abstract List<Integer> generateValues();

    public static Operator getConcreteOperator(TimeUnit timeUnit){
        String operator = null;
        if (timeUnit.getExpression().equals(ANY)) operator = ANY;
        if (timeUnit.getExpression().matches(".*,.*")) operator = SET;
        if (timeUnit.getExpression().matches("[0-9]+-[0-9]+")) operator = RANGE;
        if (timeUnit.getExpression().matches(".*\\/.*")) operator = STEP;
        if (timeUnit.getExpression().matches("^[0-9]+$")) operator = NUMBER;

        if (operator == null) throw new RuntimeException("Invalid segment expression : " + timeUnit.getExpression());

        return OperatorFactory.getConcreteOperator(operator,timeUnit);
    }

    static class OperatorFactory {
        public static Operator getConcreteOperator(String operator, TimeUnit timeUnit) {
            switch (operator) {
                case ANY:
                    return new Any(timeUnit);
                case NUMBER:
                    return new Number(timeUnit);
                case RANGE:
                    return new Range(timeUnit);
                case STEP:
                    return new Step(timeUnit);
                case SET:
                    return new Set(timeUnit);
                default:
                    return null;
            }
        }
    }

}
