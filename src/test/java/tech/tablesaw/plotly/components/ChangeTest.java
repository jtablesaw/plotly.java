package tech.tablesaw.plotly.components;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import tech.tablesaw.plotly.change.ChangeLine;
import tech.tablesaw.plotly.change.Increasing;

public class ChangeTest {

  @Test
  public void testJavascript() {

    Increasing increasing =
        Increasing.builder()
            .changeLine(ChangeLine.builder().width(3).color("blue").build())
            .fillColor("444")
            .build();

    assertTrue(increasing.asJSON().contains("line"));
    assertTrue(increasing.asJSON().contains("color"));
    assertTrue(increasing.asJSON().contains("width"));
    assertTrue(increasing.asJSON().contains("fillcolor"));
  }

  @Test
  public void testJavascript2() {

    ChangeLine line = ChangeLine.builder().width(4).color("444").build();

    assertTrue(line.asJSON().contains("color"));
    assertTrue(line.asJSON().contains("width"));
  }
}
