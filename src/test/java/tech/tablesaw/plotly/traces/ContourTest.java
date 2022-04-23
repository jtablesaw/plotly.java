package tech.tablesaw.plotly.traces;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Marker;

@Disabled
public class ContourTest {

  private final Object[] x = {-9, -6, -5, -3, -1};
  private final Object[] y = {0, 1, 4, 5, 7};
  private final double[][] z = {
    {10, 10.625, 12.5, 15.625, 20},
    {5.625, 6.25, 8.125, 11.25, 15.625},
    {2.5, 3.125, 5.0, 8.125, 12.5},
    {0.625, 1.25, 3.125, 6.25, 10.625},
    {0, 0.625, 2.5, 5.625, 10}
  };

  @Disabled
  @Test
  public void testAsJavascript() {
    ContourTrace trace = ContourTrace.builder(x, y, z).build();
    System.out.println(trace.asJavascript(1));
  }

  @Disabled
  @Test
  public void testContourTrace() {
    ContourTrace trace = ContourTrace.builder(x, y, z).colorScale(Marker.Palette.GREENS).build();
    Figure figure = new Figure(trace);
    Plot.show(figure);
  }
}
