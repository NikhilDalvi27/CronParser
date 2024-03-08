package com.parser;

public class Driver{
    public static void main(String[] args) {
        //   */15 0 4-15 * 1-5 /usr/bin/find
        String result =  new CronExpressionParser(args[0]).toString();
        System.out.println(result);
    }
}