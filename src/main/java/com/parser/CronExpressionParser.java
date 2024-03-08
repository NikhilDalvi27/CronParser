package com.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CronExpressionParser {
    String expression, command;
    HashMap<String, List<Integer>> timeUnitMap;


    private static final String MINUTE = "minute";
    private static final String HOUR = "hour";
    private static final String DAY = "day of month";
    private static final String MONTH = "month";
    private static final String WEEK = "day of week";





    CronExpressionParser(String expression){
        this.expression = expression;
        timeUnitMap = new HashMap<>();
        parse();
    }

    public CronExpressionParser parse(){

        String[] timeUnits = this.expression.split("\\s+");

        if (timeUnits.length < 6) {
            throw new RuntimeException("There must be 6 timeUnits in the expression");
        }

        int i = 0;
        this.timeUnitMap.put(MINUTE, new TimeUnit(timeUnits[i++],TimeUnitType.MINUTE).parse());
        this.timeUnitMap.put(HOUR, new TimeUnit(timeUnits[i++],TimeUnitType.HOUR).parse());
        this.timeUnitMap.put(DAY, new TimeUnit(timeUnits[i++],TimeUnitType.DAY).parse());
        this.timeUnitMap.put(MONTH, new TimeUnit(timeUnits[i++],TimeUnitType.MONTH).parse());
        this.timeUnitMap.put(WEEK, new TimeUnit(timeUnits[i++],TimeUnitType.WEEK).parse());
        this.command = extractCommand(timeUnits,i);
        return this;
    }

    private String extractCommand(String[] timeUnits, int i) {
        StringBuilder sb = new StringBuilder();
        while(i<timeUnits.length){
            sb.append(timeUnits[i]);
            i++;
        }
        return sb.toString();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        List<String> keys = new ArrayList<>(Arrays.asList(MINUTE,HOUR,DAY,MONTH,WEEK));
        for(String key: keys){
            sb.append(key);
            int spaces = 14-key.length();
            for(int i=0;i<spaces;i++){
                sb.append(" ");
            }
            sb.append(this.timeUnitMap.get(key).stream().map(val ->val.toString()).collect(Collectors.joining(" ")));
            sb.append("\n");
        }

        sb.append(String.format("command       %s", this.command));
        return sb.toString();
    }
}