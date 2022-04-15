/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tech.tablesaw.plotly.traces;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.components.*;
import tech.tablesaw.plotly.traces.ScatterTrace;

@Disabled
public class BubbleTest {

  private final double[] x = {1, 2, 3, 4, 5, 6};
  private final double[] y = {0, 1, 6, 14, 25, 39};
  private final double[] size = {10.0, 33.0, 21.0, 40.0, 28.0, 16.0};

  @Test
  public void testAsJSON() {
    ScatterTrace trace =
        ScatterTrace.builder(x, y).marker(Marker.builder().size(size).build()).build();
    System.out.println(trace.asJavascript(1));
  }

  @Test
  public void showScatter() {
    ScatterTrace trace =
        ScatterTrace.builder(x, y)
            .mode(ScatterTrace.Mode.MARKERS)
            .marker(
                Marker.builder()
                    .size(size)
                    .showScale(true)
                    .color(y)
                    .colorScale(Marker.Palette.GREENS)
                    .symbol(Symbol.DIAMOND_TALL)
                    .build())
            .build();

    Plot.show(Figure.builder(trace).build());
  }

  @Test
  public void showScatter2() {
    ScatterTrace trace =
        ScatterTrace.builder(x, y)
            .mode(ScatterTrace.Mode.MARKERS)
            .marker(
                Marker.builder()
                    .size(size)
                    .showScale(true)
                    .color(y)
                    .colorScale(Marker.Palette.BLUE_RED)
                    .symbol(Symbol.DIAMOND_TALL)
                    .build())
            .build();
    Layout layout = Layout.builder()
            .title("An example")
            .xAxis(Axis.builder().title("My X axis").build())
            .yAxis(Axis.builder().title("My Y axis").build())
            .build();
    Plot.show(Figure.builder(trace).layout(layout).build());
  }
}
