package ru.stqa.pft.sandbox;

import org.junit.Assert;
import org.junit.Test;
import ru.stqa.pft.sangbox.Equation;

public class EquationTests {

    @Test
    public void test0() {
        Equation e = new Equation(1,1,1);
        Assert.assertEquals(e.rootNumber(), 0);
    }

    @Test
    public void test1() {
        Equation e = new Equation(1,2,1);
        Assert.assertEquals(e.rootNumber(), 1);
    }

    @Test
    public void test2() {
        Equation e = new Equation(1,5,4);
        Assert.assertEquals(e.rootNumber(), 2);
    }
}
