package com.parser;

public enum TimeUnitType {
    MINUTE(0,59),
    HOUR(0,59),
    DAY(1,31),
    MONTH(1,12),
    WEEK(0,6);
    public final int min;
    public final int max;

     TimeUnitType(int min,int max){
        this.min = min;
        this.max = max;
    }
}
