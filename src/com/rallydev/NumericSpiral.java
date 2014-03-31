package com.rallydev;

/**
 * Class that generates a numeric spiral (starting from the center and moving in a clock-wise
 * direction).
 *
 * Example (input: 20)
 * <pre>
 * 20  -  -  -  -
 * 19  6  7  8  9
 * 18  5  0  1 10
 * 17  4  3  2 11
 * 16 15 14 13 12
 * </pre>
 */
public class NumericSpiral {
  /**
   * Creates the cell renderer for each cell value.
   */
  private static class CellRenderer extends IntegerCellRenderer {
    private final String numberFormat;

    /**
     * Constructor for {@code CellRenderer}.
     *
     * @param paddingSize the left padding size
     */
    public CellRenderer(int paddingSize) {
      this.numberFormat = padLeftFormat(paddingSize);
    }

    @Override
    public String render(int value) {
      return String.format(numberFormat,
          value == EMPTY_CELL_VALUE ? EMPTY_CELL_RENDERER : value);
    }
  }

  protected static final int EMPTY_CELL_VALUE = -1;
  protected static final String EMPTY_CELL_RENDERER = "-";

  public static void main(String[] args) {
    for (int i = 50; i < 51; i++) {
//      System.out.println("i: " + i);
      System.out.println(new NumericSpiral(i).render());
//      System.out.println();
    }
  }

  private final int[][] grid;
  private final IntegerCellRenderer cellRenderer;
  private final int target;
  private String display;

  public NumericSpiral(int target) {
    final int size = GridHelper.determineGridSize(target);

    this.grid = new int[size][size];
    this.cellRenderer = new CellRenderer(String.valueOf(target).length());
    this.target = target;

    GridHelper.initialize(this.grid, EMPTY_CELL_VALUE);
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
    int result = 17;
    result = 31 * result + this.target;
    return result;
  }

  /**
   * Renders the numeric spiral.
   *
   * @return the rendered grid
   */
  public String render() {
    // lazily render
    if (this.display == null) {
      this.display = GridHelper.render(this.grid, this.cellRenderer);
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