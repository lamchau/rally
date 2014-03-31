package com.rallydev;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

public class PointTest {
  private class SubclassPoint extends Point {
  }

  private Point point;

  @Before
  public void setUp() throws Exception {
    point = new Point(3, 3);
  }

  @Test
  public void testHashCode() {
    int expected = point.hashCode();
    int actual = new Point(3, 3).hashCode();
    assertEquals(expected, actual);
  }

  @Test
  public void testChaining() {
    assertSame(point, point.move(Direction.UP));
  }

  @Test
  public void testEqualsObject() {
    assertEquals(point, new Point(3, 3));
  }

  @Test
  public void testEqualsSubclass() {
    Point other = new SubclassPoint();
    assertFalse(point.equals(other));
  }

  @Test
  public void testStationary() {
    point.move();
    assertEquals(3, point.getX());
    assertEquals(3, point.getY());
  }

  @Test
  public void testMove() {
    point.move(Direction.UP, Direction.RIGHT, Direction.RIGHT);
    assertEquals(2, point.getX());
    assertEquals(5, point.getY());
  }

  @Test
  public void testReset() {
    point.move(Direction.RIGHT, Direction.RIGHT, Direction.RIGHT).reset();
    assertEquals(3, point.getX());
    assertEquals(3, point.getY());
  }

  @Test
  public void testResetByMove() {
    point.move(Direction.RIGHT, Direction.DOWN, Direction.LEFT, Direction.UP);
    assertEquals(3, point.getX());
    assertEquals(3, point.getY());
  }

  @Test
  public void testSet() {
    point.set(-2, 13);
    assertEquals(-2, point.getX());
    assertEquals(13, point.getY());
  }
}
