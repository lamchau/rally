package com.rallydev;

import java.util.ArrayList;
import java.util.List;

public class NumericSpiral {
  protected enum Direction {
    RIGHT, DOWN, LEFT, UP;

    // cache directions
    private static final Direction[] VALUES = values();

    public static Direction getDirection(int ordinal) {
      return VALUES[ordinal % VALUES.length];
    }
  }

  private static final int MIN_GRID_SIZE = 3;
  private static final int EMPTY_GRID_VALUE = -1;
  private static final String NEWLINE = System.getProperty("line.separator");

  public static void main(String[] args) {
    for (int i = 0; i < 1000; i++) {
      System.out.println(new NumericSpiral(i));
      System.out.println();
    }

  }

  static List<Integer> as(int[] array) {
    List<Integer> list = new ArrayList<Integer>(array.length);
    for (int i = 0; i < array.length; i++) {
      list.add(array[i]);
    }
    return list;
  }

  /**
   * Determines the optimal n by n grid dimensions for a given target while center-aligning the
   * starting point (i.e. n will always be odd).
   *
   * @param target the target value
   * @return the calculated grid size
   */
  static int determineGridSize(int target) {
    if (target < 0) {
      throw new IllegalArgumentException("Target value must be greater than or equal to 0");
    }

    // special case for when we only render a target value of 0
    if (target == 0) {
      return 1;
    }

    int size = Math.max((int) Math.ceil(Math.sqrt(target + 1)), MIN_GRID_SIZE);
    if (size % 2 == 0) {
      size++;
    }
    return size;
  }

  /**
   * Creates the left-padded number renderer. For use with {@code String.format}.
   *
   * @param grid the renderer for
   * @return the cell formatter
   */
  static String createCellRenderer(int maxValue) {
    int padding = String.valueOf(maxValue).length();
    return "%1$" + padding + "s";
  }

  static void initialize(int[][] grid, int defaultValue) {
    if (grid == null) {
      return;
    }

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        grid[i][j] = defaultValue;
      }
    }
  }

  private final int[][] grid;
  private final String cellRenderer;
  private final int target;

  private String display;

  public NumericSpiral(int target) {
    final int size = determineGridSize(target);

    this.grid = new int[size][size];
    this.cellRenderer = createCellRenderer(target);
    this.target = target;

    initialize(this.grid, EMPTY_GRID_VALUE);
    populate();
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (j > 0) {
          sb.append(" ");
        }
        int value = grid[i][j];
        sb.append(String.format(cellRenderer, value == EMPTY_GRID_VALUE ? "-" : value));

      }
      sb.append(NEWLINE);
    }
    return sb.toString();
  }

  private void populate() {
    final int center = (int) Math.floor(this.grid.length / 2);
    int x = center;
    int y = center + 1;
    int[] position = {1, 1, 2, 2};
    Direction direction;

    int counter = 1;
    int layer = 0;

    grid[center][center] = 0;
    while (counter <= this.target) {
      direction = Direction.RIGHT;
      int c = position[direction.ordinal()];
      for (int i = 0; i < c; i++) {
        grid[x - layer][y + i - layer] = counter++;
        if (counter > this.target) {
          return;
        }
      }

      direction = Direction.DOWN;
      c = position[direction.ordinal()];
      for (int i = 0; i < c; i++) {
        grid[y + i - layer][x + layer + 1] = counter++;
        if (counter > this.target) {
          return;
        }
      }

      direction = Direction.LEFT;
      c = position[direction.ordinal()];
      for (int i = 0; i < c; i++) {
        grid[x + layer + 1][y - i + layer - 1] = counter++;
        if (counter > this.target) {
          return;
        }
      }

      direction = Direction.UP;
      c = position[direction.ordinal()];
      for (int i = 0; i < c; i++) {
        grid[y - i + layer - 1][x - layer - 1] = counter++;
        if (counter > this.target) {
          return;
        }
      }

      for (int i = 0; i < position.length; i++) {
        position[i] += 2;
      }
      layer++;
    }
  }
}
