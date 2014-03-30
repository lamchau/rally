package com.rallydev;

import java.util.ListIterator;

/**
 * Relative clockwise movement direction, starting with right as the priority direction.
 */
public enum Direction {
  RIGHT, DOWN, LEFT, UP;

  /**
   * Convenience class for traversing relative directions forwards/backwards.
   */
  static final class DirectionIterator implements ListIterator<Direction> {
    private final Direction[] values = values();
    private int currentIndex = Direction.UP.ordinal();

    @Override
    public void add(Direction e) {
      throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasNext() {
      return true;
    }

    @Override
    public boolean hasPrevious() {
      return true;
    }

    @Override
    public Direction next() {
      this.currentIndex = nextIndex();
      return values[this.currentIndex];
    }

    @Override
    public int nextIndex() {
      return getIndex(currentIndex + 1);
    }

    @Override
    public Direction previous() {
      this.currentIndex = previousIndex();
      return values[this.currentIndex];
    }

    @Override
    public int previousIndex() {
      return getIndex(currentIndex - 1);
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }

    @Override
    public void set(Direction e) {
      if (e == null) {
        throw new IllegalArgumentException("Direction cannot be null");
      }
      this.currentIndex = getIndex(e.ordinal());
    }

    /**
     * Alias for {@code Direction#getDirection(int)}.
     *
     * @param index the relative index
     * @return the absolute ordinal index
     */
    private int getIndex(int index) {
      return getDirection(index).ordinal();
    }
  }

  // cache directions
  private static final Direction[] VALUES = values();
  public static final int SIZE = VALUES.length;

  /**
   * Create a circular (continuous) <code>Direction</code> iterator/generator.
   *
   * @return a direction iterator
   */
  public static ListIterator<Direction> createIterator() {
    return new DirectionIterator();
  }

  /**
   * Convenience method for getting a relative <code>Direction</code>. <br />
   * Note: This method also accepts negative values which roll backwards or forwards respectively.
   *
   * @param index the index value
   * @return the direction
   */
  public static Direction getDirection(int index) {
    int length = VALUES.length;
    int normalized = ((index % length) + length) % length;
    return VALUES[normalized];
  }
}
