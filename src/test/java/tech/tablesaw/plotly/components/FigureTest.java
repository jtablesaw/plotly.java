package tech.tablesaw.plotly.components;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import tech.tablesaw.plotly.traces.ScatterTrace;
import tech.tablesaw.plotly.traces.Trace;

class FigureTest {

  private static final String LINE_END = System.lineSeparator();
  private final String divName = "target";

  private final double[] x = {1, 2, 3, 4, 5};
  private final double[] y = {1, 4, 9, 16, 25};

  @Test
  void asJSON() {

    Trace trace = ScatterTrace.builder(x, y).build();
    Figure figure = new Figure(trace);

    assertEquals(
"    <script>" + LINE_END +
        "        var target_target = document.getElementById('target');" + LINE_END +
        "        var layout = {" + LINE_END +
        "" + LINE_END +
        "" + LINE_END +
        "};" + LINE_END +
        "var config = {" + LINE_END +
        "  displaylogo : false," + LINE_END +
        "  responsive : true" + LINE_END +
        "}" + LINE_END +
        "var trace0 =" + LINE_END +
        "{" + LINE_END +
        "x: [1.0, 2.0, 3.0, 4.0, 5.0]," + LINE_END +
        "y: [1.0, 4.0, 9.0, 16.0, 25.0]," + LINE_END +
        "mode: 'markers'," + LINE_END +
        "xaxis: 'x'," + LINE_END +
        "yaxis: 'y'," + LINE_END +
        "type: 'scatter'," + LINE_END +
        "name: ''," + LINE_END +
        "};" + LINE_END +
        "" + LINE_END +
        "        var data = [ trace0];" + LINE_END +
        "Plotly.newPlot(target_target, data, layout, config);            </script>" + LINE_END,
        figure.asJavascript(divName));
  }

  @Test
  void asJavascript2() {

    Trace trace = ScatterTrace.builder(x, y).build();

    Layout layout =
        Layout.builder()
            .title("A test title")
            .xAxis(
                Axis.builder()
                    .title("x Axis 1")
                    .visible(true)
                    .type(Axis.Type.DEFAULT)
                    .titleFont(
                        Font.builder().family(Font.Family.ARIAL).size(8).color("red").build())
                    .build())
            .margin(Margin.builder().top(200).left(200).build())
            .showLegend(true)
            .build();

    Figure figure = new Figure(layout, trace);
    String expected = "    <script>" + LINE_END +
            "        var target_target = document.getElementById('target');" + LINE_END +
            "        var layout = {" + LINE_END +
            "    title: 'A test title'," + LINE_END +
            "    height: 450," + LINE_END +
            "    width: 700," + LINE_END +
            "    showlegend: true," + LINE_END +
            "    margin: {" + LINE_END +
            "  autoexpand : true," + LINE_END +
            "  b : 80," + LINE_END +
            "  l : 200," + LINE_END +
            "  pad : 0," + LINE_END +
            "  r : 80," + LINE_END +
            "  t : 200LINE_END" +
            "}," + LINE_END +
            "    xaxis: {" + LINE_END +
            "  fixedrange : true," + LINE_END +
            "  rangemode : \"normal\"," + LINE_END +
            "  title : \"x Axis 1\"," + LINE_END +
            "  titlefont : {" + LINE_END +
            "    fontFamily : \"arial\"," + LINE_END +
            "    size : 8," + LINE_END +
            "    color : \"red\"" + LINE_END +
            "  }" + LINE_END +
            "}," + LINE_END +
            "" + LINE_END +
            "" + LINE_END +
            "};" + LINE_END +
            "" + LINE_END +
            "var layout = {" + LINE_END +
            "" + LINE_END +
            "" + LINE_END +
            "};" + LINE_END +
            "var config = {" + LINE_END +
            "  displaylogo : false," + LINE_END +
            "  responsive : true" + LINE_END +
            "}"+
            "var trace0 =" + LINE_END +
            "{" + LINE_END +
            "x: [1.0, 2.0, 3.0, 4.0, 5.0]," + LINE_END +
            "y: [1.0, 4.0, 9.0, 16.0, 25.0]," + LINE_END +
            "mode: 'markers'," + LINE_END +
            "xaxis: 'x'," + LINE_END +
            "yaxis: 'y'," + LINE_END +
            "type: 'scatter'," + LINE_END +
            "name: ''," + LINE_END +
            "};" + LINE_END +
            "" + LINE_END +
            "        var data = [ trace0];" + LINE_END +
            "Plotly.newPlot(target_target, data, layout);            </script>" + LINE_END;
    String actual = figure.asJavascript(divName);
    assertNotEquals(expected, actual);
  }

  @Test
  void builder() {
    String title = "A test title";
    Layout layout = Layout.builder().title(title).showLegend(true).build();

    Trace trace = ScatterTrace.builder(x, y).build();

    Figure figure = Figure.builder().layout(layout).addTraces(trace).build();

    assertEquals(layout, figure.getLayout());

    figure.asJavascript(divName); // force the context to get created
    assertTrue(String.valueOf(figure.getContext().get("figure")).contains(title));
  }
}
