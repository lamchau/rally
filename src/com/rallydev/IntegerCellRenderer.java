package com.rallydev;

/**
 * Generic cell renderer for <code>int</code> cell(s).
 */
public class IntegerCellRenderer {
  /**
   * Creates the left-padded number renderer (right aligned). For use with {@code String.format}.
   *
   * @param paddingSize the left-padding size
   * @return the integer string formatter
   */
  public static String padLeftFormat(int paddingSize) {
    return "%1$" + paddingSize + "s";
  }

  /**
   * Renderer for integer values.
   *
   * @param value the integer to render
   * @return the integer as a string
   */
  public String render(int value) {
    return String.valueOf(value);
  }
}