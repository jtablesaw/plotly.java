package tech.tablesaw.plotly.traces;

import org.junit.jupiter.api.Test;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.components.*;
import tech.tablesaw.plotly.traces.ScatterGeo.TextPosition;

import static tech.tablesaw.plotly.traces.ScatterGeo.TextPosition.*;

public class ScatterGeoTest {

    @Test
    public void showMap() {

        String[] text = {"Montreal", "Toronto", "Vancouver", "Calgary", "Edmonton",
                "Ottawa", "Halifax", "Victoria", "Winnepeg", "Regina"};

        TextPosition[] textPosition = {
                TOP_RIGHT, TOP_LEFT, TOP_CENTER, BOTTOM_RIGHT, TOP_RIGHT,
                TOP_LEFT, BOTTOM_RIGHT, BOTTOM_LEFT, TOP_RIGHT, TOP_RIGHT};

        double[] lon = {-73.57, -79.24, -123.06, -114.1, -113.28, -75.43, -63.57, -123.21, -97.13, -104.6};

        double[] lat = {45.5, 43.4, 49.13, 51.1, 53.34, 45.24, 44.64, 48.25, 49.89, 50.45};

        String[] color = {"#bebada", "#fdb462", "#fb8072", "#d9d9d9", "#bc80bd", "#b3de69", "#8dd3c7", "#80b1d3", "#fccde5", "#ffffb3"};

        Marker marker = Marker.builder()
                .size(7)
                .color(color)
                .line(Line.builder().width(1).build())
                .build();

        ScatterGeo trace =
                ScatterGeo.builder(lat, lon)
                        .mode(ScatterTrace.Mode.TEXT_AND_MARKERS)
                        .marker(marker)
                        .textPosition(textPosition)
                        .text(text)
                        .build();

        Layout layout = Layout.builder()
                .title("Canadian cities")
                .titleFont(Font.builder()
                        .family(Font.Family.SANS_SERIF)
                        .size(16)
                        .build())
                .geo(Geo.builder()
                        .scope("north america")
                        .showLakes(true)
                        .showLand(true)
                        .showRivers(true)
                        .lakeColor("#fff")
                        .landColor("#EAEAAE")
                        .riverColor("#fff")
                        .countryColor("#d3d3d3")
                        .subUnitColor("#d3d3d3")
                        .countryWidth(1.5)
                        .resolution(Geo.Resolution.FIFTY)
                        .lonAxis(Axis.builder()
                                .range("-130", "-55")
                                .build())
                        .latAxis(Axis.builder()
                                .range("40", "70")
                                .build())
                        .build())
                .build();
        Figure figure = new Figure(layout, trace);
        Plot.show(figure, "target");
    }
}
