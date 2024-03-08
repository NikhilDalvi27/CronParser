package com.parser;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CronExpressionParserTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();


    @Test
    public void testSimpleExpression(){
        String expression = "*/15 0 4-15 * 1-5 /usr/bin/find";
        String expectedResult =
                "minute        0 15 30 45\n" +
                "hour          0\n" +
                "day of month  4 5 6 7 8 9 10 11 12 13 14 15\n" +
                "month         1 2 3 4 5 6 7 8 9 10 11 12\n" +
                "day of week   1 2 3 4 5\n" +
                "command       /usr/bin/find";

        String actualResult =  new CronExpressionParser(expression).toString();
        Assert.assertEquals(expectedResult,actualResult);
    }

    @Test
    public void testComplexExpressionWithNestedList(){
        String expression = "4/10 0 3-5,8-15 * 1-5 /usr/bin/find";
        String expectedResult =
                        "minute        4 14 24 34 44 54\n" +
                        "hour          0\n" +
                        "day of month  3 4 5 8 9 10 11 12 13 14 15\n" +
                        "month         1 2 3 4 5 6 7 8 9 10 11 12\n" +
                        "day of week   1 2 3 4 5\n" +
                        "command       /usr/bin/find";

        String actualResult =  new CronExpressionParser(expression).toString();
        Assert.assertEquals(expectedResult,actualResult);
    }

    @Test
    public void testInvalidInput(){
        String expression = "4/10 100 3-5,8-15 * 1-5 /usr/bin/find";
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("100 value is out of bounds of the interval [ 0, 59]");
        String actualResult =  new CronExpressionParser(expression).toString();

    }

}
