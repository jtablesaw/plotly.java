package tech.tablesaw.plotly.components;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MarkerTest {

  @Test
  public void asJSON() {
    Marker x = Marker.builder().size(12.0).symbol(Symbol.DIAMOND_TALL).color("#c68486").build();

    assertTrue(x.asJSON().contains("color"));
    assertTrue(x.asJSON().contains("symbol"));
    assertTrue(x.asJSON().contains("size"));
  }
}
