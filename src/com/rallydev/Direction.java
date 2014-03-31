package com.rallydev;

/**
 * Relative clockwise movement direction, starting with right as the priority direction.
 */
public enum Direction {
  RIGHT, DOWN, LEFT, UP;

  // cache directions
  private static final Direction[] VALUES = values();
  public static final int LENGTH = VALUES.length;

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

  /**
   * Gets the next <code>Direction</code>. Allows for chaining.
   *
   * @return the next direction
   */
  public Direction next() {
    return getDirection(ordinal() + 1);
  }

  /**
   * Gets the previous <code>Direction</code>. Allows for chaining.
   *
   * @return the previous direction
   */
  public Direction previous() {
    return getDirection(ordinal() - 1);
  }
}
