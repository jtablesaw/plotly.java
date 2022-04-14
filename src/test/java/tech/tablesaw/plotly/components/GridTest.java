package tech.tablesaw.plotly.components;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GridTest {

  @Test
  public void asJSON() {
    Grid x =
        Grid.builder()
            .rows(10)
            .columns(5)
            .rowOrder(Grid.RowOrder.BOTTOM_TO_TOP)
            .pattern(Grid.Pattern.INDEPENDENT)
            .build();

    String asJavascript = x.asJSON();
    assertTrue(asJavascript.contains("rows"));
    assertTrue(asJavascript.contains("columns"));
    assertTrue(asJavascript.contains("roworder"));
    assertTrue(asJavascript.contains("pattern"));
  }
}
