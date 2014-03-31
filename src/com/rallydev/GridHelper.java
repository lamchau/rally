package com.rallydev;

/**
 * Helper class for with integer arrays.
 */
enum GridHelper {
  ;

  private static final int MIN_GRID_SIZE = 3;
  private static final int SINGLE_ELEMENT_GRID_SIZE = 1;
  private static final String NEWLINE = System.getProperty("line.separator");

  /**
   * Determines the optimal n by n grid dimensions for a given target while center-aligning the
   * starting point (i.e. n will always be odd).
   *
   * @param target the target value
   * @return the calculated grid size
   */
  public static int determineGridSize(int target) {
    if (target < 0) {
      throw new IllegalArgumentException("Target value must be greater than or equal to 0");
    }

    // special case for when we only render a target value of 0
    if (target == 0) {
      return SINGLE_ELEMENT_GRID_SIZE;
    }

    // adjust size to compensate for imperfect squares
    int size = Math.max((int) Math.ceil(Math.sqrt(target + 1)), MIN_GRID_SIZE);
    if (size % 2 == 0) {
      size++;
    }
    return size;
  }

  /**
   * Initializes a given 2D array with values other than 0.
   *
   * @param grid the grid to initialize
   * @param defaultValue the default value
   */
  public static void initialize(int[][] grid, int defaultValue) {
    if (grid == null) {
      return;
    }
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        grid[i][j] = defaultValue;
      }
    }
  }

  /**
   * Renders a 2D integer array as a string.
   *
   * @param grid the grid to render
   * @param cellRenderer the renderer for each cell
   * @return string representation of a grid
   */
  public static String render(int[][] grid, IntegerCellRenderer cellRenderer) {
    if (grid == null) {
      return "";
    }
    final StringBuilder sb = new StringBuilder();
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (j > 0) {
          sb.append(" ");
        }
        int value = grid[i][j];
        sb.append(cellRenderer == null ? value : cellRenderer.render(value));
      }
      sb.append(NEWLINE);
    }
    return sb.toString();
  }
}
