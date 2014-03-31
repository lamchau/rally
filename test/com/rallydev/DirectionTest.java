package com.rallydev;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DirectionTest {

  @Test
  public void testGetDirectionBackward() {
    Direction direction = Direction.getDirection(-1);
    assertEquals(direction, Direction.UP);
  }

  @Test
  public void testGetDirectionForward() {
    Direction direction = Direction.getDirection(1);
    assertEquals(direction, Direction.DOWN);
  }

  @Test
  public void testGetDirectionForwardRollover() {
    Direction direction = Direction.getDirection(Direction.SIZE);
    assertEquals(direction, Direction.RIGHT);
  }

}
