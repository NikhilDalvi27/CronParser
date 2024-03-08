package com.parser.operators;

import com.parser.TimeUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Range extends Operator{

    Range(TimeUnit timeUnit) {
        super(timeUnit);
    }
    @Override
    public List<Integer> generateValues() {
        List<Integer> range = List.of(timeUnit.getExpression().split("-")).stream().map(Integer::valueOf).collect(Collectors.toList());
        if(range.size()!=2){
            throw new RuntimeException("Range Arguments not correctly supplied");
        }

        int start = range.get(0);
        int end = range.get(1);

        List<Integer> res = new ArrayList<>();
        if (start < end && start >= timeUnit.getMin() && end <= timeUnit.getMax()) {
            for (int i = start; i <= end; i++) {
                res.add(i);
            }
        } else {
            throw new RuntimeException("Supply range argument between ["+timeUnit.getMin()+","+timeUnit.getMax()+"] in correct order");
        }
        return res;
    }
}
