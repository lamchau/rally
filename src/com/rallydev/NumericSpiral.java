package com.rallydev;

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
  // expose at package level for testing
  static final int MIN_GRID_SIZE = 3;
  static final int EMPTY_CELL_VALUE = -1;
  static final String EMPTY_CELL_RENDERER = "-";
  private static final String NEWLINE = System.getProperty("line.separator");

  public static void main(String[] args) {
    // for (int i = 1; i < 101; i+= 10) {
    System.out.println(new NumericSpiral(110).render());
    // System.out.println();
    // }
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

    initialize(this.grid, EMPTY_CELL_VALUE);
    populate();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }

    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }

    NumericSpiral other = (NumericSpiral) obj;
    return this.target == other.target;
  }

  @Override
  public int hashCode() {
    return this.target;
  }

  public String render() {
    if (this.display == null) {
      final StringBuilder sb = new StringBuilder();
      for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[i].length; j++) {
          if (j > 0) {
            sb.append(" ");
          }
          int value = grid[i][j];
          sb.append(String.format(cellRenderer,
              value == EMPTY_CELL_VALUE ? EMPTY_CELL_RENDERER : value));
        }
        sb.append(NEWLINE);
      }
      this.display = sb.toString();
    }
    return this.display;
  }

  private void populate() {
    final int center = (int) Math.floor(this.grid.length / 2);
    int x = center;
    int y = center + 1;
    int[] position = {1, 1, 2, 2};

    int layer = 0;

    grid[center][center] = 0;

    for (int counter = 1; counter < this.target; counter++) {
      Direction direction = Direction.RIGHT;
      int steps = position[direction.ordinal()];
      for (int i = 0; i < steps; i++) {
        grid[x - layer][y + i - layer] = counter++;
      }

      direction = Direction.DOWN;
      steps = position[direction.ordinal()];
      for (int i = 0; i < steps; i++) {
        grid[y + i - layer][x + layer + 1] = counter++;
      }

      direction = Direction.LEFT;
      steps = position[direction.ordinal()];
      for (int i = 0; i < steps; i++) {
        grid[x + layer + 1][y - i + layer - 1] = counter++;
      }

      direction = Direction.UP;
      steps = position[direction.ordinal()];
      for (int i = 0; i < steps; i++) {
        grid[y - i + layer - 1][x - layer - 1] = counter++;
      }

      for (int i = 0; i < position.length; i++) {
        position[i] += 2;
      }
      layer++;
    }
  }
}