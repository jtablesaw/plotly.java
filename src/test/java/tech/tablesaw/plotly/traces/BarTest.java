package tech.tablesaw.plotly.traces;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Marker;
import tech.tablesaw.plotly.traces.BarTrace;

@Disabled
public class BarTest {

  private final Object[] x = {"sheep", "cows", "fish", "tree sloths"};
  private final double[] y = {1, 4, 9, 16};

  @Test
  public void testasJSON() {
    BarTrace trace = BarTrace.builder(x, y).build();
    System.out.println(trace.asJavascript(1));
  }

  @Test
  public void show(){
    BarTrace trace = BarTrace.builder(x, y).build();
    Figure figure = new Figure(trace);
    Plot.show(figure,"target");
  }

  @Test
  public void showHorizontal() {
    BarTrace trace = BarTrace.builder(x, y)
            .orientation(BarTrace.Orientation.HORIZONTAL)
            .marker(Marker.builder()
                    .color("#bedd86").build())
            .build();
    Figure figure = new Figure(trace);
    Plot.show(figure, "target");
  }
}
