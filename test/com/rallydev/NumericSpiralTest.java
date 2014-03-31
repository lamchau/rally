package com.rallydev;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

public class NumericSpiralTest {

  @Test
  public void testIsNotSame() {
    NumericSpiral n1 = new NumericSpiral(20);
    NumericSpiral n2 = new NumericSpiral(20);
    assertNotSame(n1, n2);
  }

  @Test
  public void testEquals() {
    NumericSpiral n1 = new NumericSpiral(20);
    NumericSpiral n2 = new NumericSpiral(20);
    assertEquals(n1, n2);
  }

  @Test
  public void testIsEquals() {
    NumericSpiral n1 = new NumericSpiral(20);
    NumericSpiral n2 = new NumericSpiral(21);

    // TODO: investigate assertThat
    assertFalse(n1.equals(n2));
  }

  @Test
  public void testHashCode() throws Exception {
    NumericSpiral n1 = new NumericSpiral(20);
    NumericSpiral n2 = new NumericSpiral(20);
    assertEquals(n1.hashCode(), n2.hashCode());
  }
}
