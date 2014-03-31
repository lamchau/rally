package com.rallydev;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class IntegerCellRendererTest {

  private IntegerCellRenderer renderer;

  @Before
  public void setUp() throws Exception {
    renderer = new IntegerCellRenderer();
  }

  @Test
  public void testAlignRight() {
    String format = IntegerCellRenderer.padLeftFormat(5);
    assertEquals("    5", String.format(format, 5));
  }

  @Test
  public void testRender() {
    assertEquals("10", renderer.render(10));
  }

}
