package com.parser;

import com.parser.operators.Operator;

import java.util.List;

public class TimeUnit {
    Integer min, max;
    String expression;
    TimeUnitType timeUnitType;
    List<Integer> values;

    public TimeUnit(String expression,TimeUnitType timeUnitType) {
        this.expression = expression;
        this.timeUnitType = timeUnitType;
        this.min = timeUnitType.min;
        this.max  = timeUnitType.max;
    }

    public Integer getMin(){
        return min;
    }

    public Integer getMax(){
        return max;
    }
    public String getExpression(){
        return this.expression;
    }

    public TimeUnitType getTimeUnitType(){
        return  this.timeUnitType;
    }

    List<Integer> parse() {
        this.values = Operator.getConcreteOperator(this).generateValues();
        return this.values;
    }
}