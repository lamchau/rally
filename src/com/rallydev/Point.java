package com.rallydev;

/**
 * Point class used in conjunction with {@code Direction} for relative navigation across 2D space.
 */
public class Point {
  private final int startX;
  private final int startY;
  private int x;
  private int y;

  public Point() {
    this(0, 0);
  }

  /**
   * Constructor for a point.
   *
   * @param x the starting x coordinate
   * @param y the starting y coordinate
   */
  public Point(int x, int y) {
    this.x = x;
    this.startX = x;

    this.y = y;
    this.startY = y;
  }

  /**
   * Copy constructor for creating a new point from an existing <code>Point</code>.
   *
   * @param p the point to copy
   */
  public Point(Point p) {
    if (p == null) {
      throw new IllegalArgumentException("Point cannot be null");
    }

    this.x = p.x;
    this.startX = this.x;

    this.y = p.y;
    this.startY = this.y;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }

    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }

    Point other = (Point) obj;
    return x == other.x && y == other.y;
  }

  /**
   * Gets the x coordinate.
   *
   * @return the x coordinate
   */
  public int getX() {
    return x;
  }

  /**
   * Gets the y coordinate.
   *
   * @return the y coordinate
   */
  public int getY() {
    return y;
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + x;
    result = 31 * result + y;
    return result;
  }

  /**
   * Moves the point.
   *
   * @param directions the direction(s) to move
   * @return this point
   */
  public Point move(Direction... directions) {
    if (directions == null) {
      return this;
    }

    for (Direction d : directions) {
      if (d != null) {
        switch (d) {
          case RIGHT:
            y++;
            break;

          case DOWN:
            x++;
            break;

          case LEFT:
            y--;
            break;

          case UP:
            x--;
            break;
        }
      }
    }
    return this;
  }

  /**
   * Resets this point to its starting position.
   *
   * @return this point
   */
  public Point reset() {
    return set(startX, startY);
  }

  /**
   * Sets the coordinates.
   *
   * @param x the x coordinate
   * @param y the y coordinate
   * @returns this point
   */
  public Point set(int x, int y) {
    return setX(x).setY(y);
  }

  /**
   * Sets the x coordinate.
   *
   * @returns this point
   */
  public Point setX(int x) {
    this.x = x;
    return this;
  }

  /**
   * Gets the x coordinate.
   *
   * @return this point
   */
  public Point setY(int y) {
    this.y = y;
    return this;
  }

  @Override
  public String toString() {
    return "Point(x=" + this.x + ",y=" + this.y + ")";
  }
}