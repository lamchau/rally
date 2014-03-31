package com.rallydev;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GridHelperTest {

  @Test(expected = IllegalArgumentException.class)
  public void testDetermineGridSizeNegative() {
    GridHelper.determineGridSize(-1);
  }

  @Test
  public void testDetermineGridSizeSingleElement() {
    int size = GridHelper.determineGridSize(0);
    assertEquals(1, size);
  }

  @Test
  public void testDetermineGridSizeSmall() {
    int size = GridHelper.determineGridSize(1);
    assertEquals(3, size);
  }

  @Test
  public void testDetermineGridSizeAlwaysOdd() {
    int size = GridHelper.determineGridSize(2);
    assertTrue(size % 2 != 0);
  }

  @Test
  public void testDetermineGridSize() {
    int size = GridHelper.determineGridSize(9);
    // must create 3 layers, (1x1, 3x3, 5x5)
    assertEquals(size, 5);
  }

  @Test
  public void testInitializeNull() {
    GridHelper.initialize(null, -1);
  }

  @Test
  public void testInitialize() {
    final int expectedSize = 7;
    int[][] array = new int[3][3];
    GridHelper.initialize(array, expectedSize);

    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array[i].length; j++) {
        assertEquals(expectedSize, array[i][j]);
      }
    }
  }

  @Test
  public void testRenderNull() {
    assertNotNull(GridHelper.render(null, new IntegerCellRenderer()));
  }

}
