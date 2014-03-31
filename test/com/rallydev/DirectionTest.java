package com.rallydev;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DirectionTest {

  @Test
  public void testGetDirectionBackward() {
    Direction direction = Direction.getDirection(-1);
    assertEquals(Direction.UP, direction);
  }

  @Test
  public void testGetDirectionForward() {
    Direction direction = Direction.getDirection(1);
    assertEquals(Direction.DOWN, direction);
  }

  @Test
  public void testGetDirectionForwardRollover() {
    Direction direction = Direction.getDirection(Direction.LENGTH);
    assertEquals(Direction.RIGHT, direction);
  }

}
