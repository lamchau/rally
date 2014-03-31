package com.rallydev;

/**
 * Class that generates a numeric spiral (starting from the center and moving in a clock-wise
 * direction).
 *
 * Example (input: 20)
 *
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
//    -  -  -  -  -  -  -
//    - 20 21 22 23 24 25
//    - 19  6  7  8  9 26
//    - 18  5  0  1 10 27
//    - 17  4  3  2 11  -
//    - 16 15 14 13 12  -
//    -  -  -  -  -  -  -
    System.out.println(new NumericSpiral(27).render());
  }

  private final int[][] grid;
  private final IntegerCellRenderer cellRenderer;
  private final int target;
  private String display;

  /**
   * Constructor for a <code>NumericSpiral</code>. Default starting direction
   * {@code Direction#RIGHT} -- moving clockwise.
   *
   * @param target the target integer
   */
  public NumericSpiral(int target) {
    this(target, Direction.RIGHT, true);
  }

  /**
   * Constructor for a <code>NumericSpiral</code>
   *
   * @param target the target integer
   * @param startDirection the starting direction
   * @param isClockwise <code>true</code> if the spiral moves clockwise, <code>false</code> if the
   *          spiral should move counter clockwise
   */
  public NumericSpiral(int target, Direction startDirection, boolean isClockwise) {
    if (startDirection == null) {
      throw new IllegalArgumentException("Start direction cannot be null");
    }
    final int size = GridHelper.determineGridSize(target);

    this.grid = new int[size][size];
    this.cellRenderer = new CellRenderer(String.valueOf(target).length());
    this.target = target;

    GridHelper.initialize(this.grid, EMPTY_CELL_VALUE);
    populate(startDirection, isClockwise);
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

  /**
   * Populates the grid with values.
   *
   * @param startDirection the starting direction
   * @param isClockwise <code>true</code> if the values should be populated clockwise,
   *          <code>false</code> if the values should be populated counter clockwise
   */
  private void populate(final Direction startDirection, boolean isClockwise) {
    final int center = (int) Math.floor(grid.length / 2);
    // direction
    final Point p = new Point(center, center);

    // track drawing width/height steps
    int edge = 1;
    int position = 0;

    Direction direction = startDirection;
    for (int i = 0; i <= target; i++) {
      assert p.getX() < grid.length;
      assert p.getY() < grid.length;

      grid[p.getX()][p.getY()] = i;

      if (position < edge) {
        position++;
      } else {
        // reset 'pen'
        position = 1;

        // corners at which the layers 'grow'
        if (increaseEdge(startDirection, direction)) {
          edge++;
        }

        // update direction
        direction = isClockwise ? direction.next() : direction.previous();
      }

      // update point for next 'stroke'
      p.move(direction);
    }
  }

  /**
   * Determines whether or not the edge should increase.
   *
   * @param startDirection the starting direction
   * @param currentDirection the current direction
   * @return <code>true</code> if the edge size should increase, <code>false</code> otherwise
   */
  private boolean increaseEdge(Direction startDirection, Direction currentDirection) {
    if (startDirection == Direction.LEFT || startDirection == Direction.RIGHT) {
      return currentDirection == Direction.DOWN || currentDirection == Direction.UP;
    } else {
      return currentDirection == Direction.LEFT || currentDirection == Direction.RIGHT;
    }
  }
}