package com.parser.operators;

import com.parser.TimeUnit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Number extends Operator{

    Number(TimeUnit timeUnit) {
        super(timeUnit);
    }
    @Override
    public List<Integer> generateValues() {
        Integer res = Integer.parseInt(timeUnit.getExpression());
        if(res< timeUnit.getMin() || res>timeUnit.getMax()){
            throw new RuntimeException(res+" value is out of bounds of the interval [ "+ timeUnit.getMin()+", "+ timeUnit.getMax()+"]");
        }
        return new ArrayList<>(Arrays.asList(res));
    }
}
