package ru.stqa.pft.sandbox;

import org.junit.Test;
import ru.stqa.pft.sangbox.Point;

import static org.junit.Assert.assertEquals;

public class PointTest {

    @Test
    public void testDistance () {
        Point p1 = new Point(0,0);
        Point p2 = new Point(0,0);
        assertEquals(0.0, p1.distance(p2), 0.0);
    }
}
