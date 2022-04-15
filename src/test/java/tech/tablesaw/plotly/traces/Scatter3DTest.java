package tech.tablesaw.plotly.traces;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.components.Marker;
import tech.tablesaw.plotly.components.Symbol;
import tech.tablesaw.plotly.traces.Scatter3DTrace;

public class Scatter3DTest {

    @Test
    @Disabled
    void show() {
        final double[] x1 = {13, 14, 15, 16, 17, 18};
        final double[] y1 = {0, 1, 6, 14, 25, 39};
        final double[] z1 = {4, -21, 23, 0, -11, 17};
        Scatter3DTrace trace = Scatter3DTrace.builder(x1, y1, z1)
                .marker(Marker.builder()
                        .color("#33b3a6")
                        .symbol(Symbol.CIRCLE)
                        .size(20)
                        .opacity(.7)
                        .build())
                .build();
        Layout layout = Layout.builder()
                .title("3D Interactive Plot Example").build();
        Figure figure = Figure.builder()
                .layout(layout)
                .addTraces(trace)
                .build();
        Plot.show(figure);
    }
}
