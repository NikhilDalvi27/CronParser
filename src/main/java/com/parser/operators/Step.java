package com.parser.operators;

import com.parser.TimeUnit;

import java.util.ArrayList;
import java.util.List;

public class Step extends Operator{

    Step(TimeUnit timeUnit) {
        super(timeUnit);
    }

    @Override
    public List<Integer> generateValues() {
        String[]steps = timeUnit.getExpression().split("/");
        if(steps.length!=2){
            throw new RuntimeException("Invalid arguments for step function in "+timeUnit.getExpression());
        }
        Integer step = Integer.parseInt(steps[1]);
        if(step< timeUnit.getMin() || step>timeUnit.getMax()){
            throw new RuntimeException(step+" value is out of bounds of the interval [ "+ timeUnit.getMin()+", "+ timeUnit.getMax()+"]");
        }


        int start = -1;
        if(steps[0].equals("*")){
            start = timeUnit.getMin();
        }else{
            start = Integer.parseInt(steps[0]);
        }

        if (start < timeUnit.getMin() || start > timeUnit.getMax()) {
            throw new RuntimeException(start + " value is out of bounds of the interval [ " + timeUnit.getMin() + ", " + timeUnit.getMax() + "]");
        }

        List<Integer>res = new ArrayList<>();
        for (int i = start; i <= timeUnit.getMax(); i=i+step) {
            res.add(i);
        }
        return res;
    }
}