package com.parser.operators;

import com.parser.TimeUnit;

import java.util.ArrayList;
import java.util.List;

public class Any extends Operator{
    Any(TimeUnit timeUnit) {
        super(timeUnit);
    }
    @Override
    public List<Integer> generateValues() {
        List<Integer>res = new ArrayList<>();
        for(int i=timeUnit.getMin();i<=timeUnit.getMax();i++){
            res.add(i);
        }
        return res;
    }
}