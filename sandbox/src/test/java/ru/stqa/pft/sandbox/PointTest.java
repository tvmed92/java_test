package ru.stqa.pft.sandbox;

import org.junit.Test;
import ru.stqa.pft.sangbox.Point;

import static org.junit.Assert.assertEquals;

public class PointTest {

    @Test
    public void testDistance () {
        Point p = new Point(1, 1, 1, 1);
        assertEquals(0.0, p.distance(), 0.0);
    }
}
