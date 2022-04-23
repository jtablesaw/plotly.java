package tech.tablesaw.plotly.traces;

import java.io.File;
import java.nio.file.Paths;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.components.Figure;

@Disabled
public class PieTest {

  private final Object[] x = {"sheep", "cows", "fish", "tree sloths"};
  private final double[] y = {1, 4, 9, 16};

  @Disabled
  @Test
  public void testAsJSON() {
    PieTrace trace = PieTrace.builder(x, y).build();
    System.out.println(trace.asJavascript(1));
  }

  @Disabled
  @Test
  public void show() {
    PieTrace trace = PieTrace.builder(x, y).build();
    Figure figure = new Figure(trace);
    File outputFile = Paths.get("testoutput/output.html").toFile();
    Plot.show(figure, "target", outputFile);
  }
}
