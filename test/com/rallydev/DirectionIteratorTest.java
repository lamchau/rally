package com.rallydev;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ListIterator;

import org.junit.Before;
import org.junit.Test;

// TODO: remove
public class DirectionIteratorTest {

  private ListIterator<Direction> iterator;

  @Before
  public void setUp() throws Exception {
    iterator = Direction.createIterator();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testAdd() {
    iterator.add(Direction.DOWN);
  }

  @Test
  public void testHasNext() {
    for (int i = 0; i < (Direction.LENGTH * 2); i++) {
      iterator.next();
      assertTrue(iterator.hasNext());
    }
  }

  @Test
  public void testHasPrevious() {
    for (int i = 0; i < (Direction.LENGTH * 2); i++) {
      iterator.previous();
      assertTrue(iterator.hasPrevious());
    }
  }

  @Test
  public void testNext() {
    Direction[] expected = {
        Direction.RIGHT,
        Direction.DOWN,
        Direction.LEFT,
        Direction.UP,
        Direction.RIGHT
    };

    for (Direction d : expected) {
      assertEquals(iterator.next(), d);
    }
  }

  @Test
  public void testNextIndex() {
    int currentIndex = iterator.nextIndex();
    assertEquals(currentIndex, iterator.nextIndex());
  }

  @Test
  public void testPrevious() {
    Direction[] expected = {
        Direction.LEFT,
        Direction.UP,
        Direction.RIGHT,
        Direction.DOWN,
        Direction.LEFT,
    };

    for (int i = expected.length - 1; i >= 0; --i) {
      assertEquals(iterator.previous(), expected[i]);
    }
  }

  @Test
  public void testPreviousIndex() {
    int currentIndex = iterator.previousIndex();
    assertEquals(currentIndex, iterator.previousIndex());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testRemove() {
    iterator.remove();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetBad() {
    iterator.set(null);
  }

  @Test
  public void testSet() {
    iterator.set(Direction.DOWN);
    assertEquals(iterator.next(), Direction.LEFT);
    assertEquals(iterator.nextIndex(), Direction.UP.ordinal());
  }
}
