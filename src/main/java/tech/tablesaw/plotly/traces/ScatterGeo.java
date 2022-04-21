package tech.tablesaw.plotly.traces;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.Preconditions;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import tech.tablesaw.plotly.Utils;
import tech.tablesaw.plotly.components.LocationMode;
import tech.tablesaw.plotly.components.Marker;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Map;

public class ScatterGeo extends AbstractTrace {

    public enum TextPosition {
        TOP_RIGHT("top right"),
        TOP_CENTER("top center"),
        TOP_LEFT("top left"),
        BOTTOM_RIGHT("bottom right"),
        BOTTOM_CENTER("bottom center"),
        BOTTOM_LEFT("bottom left"),
        MIDDLE_RIGHT("middle right"),
        MIDDLE_CENTER("middle center"),
        MIDDLE_LEFT("middle left");

        final String value;

        TextPosition(String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return value;
        }
    }

    private final LocationMode locationMode;
    private final String geo;
    private final ScatterTrace.Mode mode;
    private final Marker marker;
    private final double[] lat;
    private final double[] lon;
    private final TextPosition[] textPosition;
    private final String[] text;

    private ScatterGeo(ScatterGeoBuilder builder) {
        super(builder);
        this.locationMode = builder.locationMode;
        this.geo = builder.geo;
        this.mode = builder.mode;
        this.marker = builder.marker;
        this.lat = builder.lat;
        this.lon = builder.lon;
        this.textPosition = builder.textPosition;
        this.text = builder.text;
    }

    @Override
    public String asJavascript(int i) {
        Writer writer = new StringWriter();
        PebbleTemplate compiledTemplate;

        try {
            compiledTemplate = engine.getTemplate("trace_template.html");
            Map<String, Object> context = getContext(i);
            compiledTemplate.evaluate(writer, context);
        } catch (PebbleException e) {
            throw new IllegalStateException(e);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return writer.toString();
    }

    public Map<String, Object> getContext(int i) {
        Map<String, Object> context = super.getContext();
        context.put("variableName","trace"+i);
        context.put("type", type);
        context.put("mode", mode);
        if (marker != null) {
            context.put("marker", marker);
        }
        context.put("lat", Arrays.toString(lat));
        context.put("lon", Arrays.toString(lon));
        if (locationMode != null) context.put("locationmode", locationMode);
        if (textPosition != null) {
            if (textPosition.length == 1) {
                context.put("textposition", textPosition[0]);
            } else {
                context.put("textposition", Arrays.toString(Utils.dataAsArrayofStrings(textPosition)));
            }
        }
        if (text != null) {
            if (text.length == 1) {
                context.put("text", text[0]);
            } else {
                context.put("text", Arrays.toString(Utils.dataAsArrayofStrings(text)));
            }
        }
        return context;
    }

    public static ScatterGeoBuilder builder(double[] lat, double[] lon) {
        return new ScatterGeoBuilder(lat, lon);
    }

    public static class ScatterGeoBuilder extends TraceBuilder {

        private final String type = "scattergeo";
        private ScatterTrace.Mode mode = ScatterTrace.Mode.MARKERS;
        private Marker marker;
        private String geo = "geo";
        private double[] lat;
        private double[] lon;
        private LocationMode locationMode = LocationMode.ISO_3;
        private TextPosition[] textPosition;
        private String[] text;

        public ScatterGeoBuilder(double[] lat, double[] lon) {
            this.lat = lat;
            this.lon = lon;
        }

        public ScatterGeoBuilder locationMode(LocationMode locationMode) {
            this.locationMode = locationMode;
            return this;
        }

        /**
         * Sets a reference between this trace's geospatial coordinates and a geographic map.
         * If "geo" (the default value), the geospatial coordinates refer to `layout.geo`.
         * If "geo2", the geospatial coordinates refer to `layout.geo2`, and so on.
         */
        public ScatterGeoBuilder geo(String geo) {
            this.geo = geo;
            return this;
        }

        public ScatterGeoBuilder mode(ScatterTrace.Mode mode) {
            this.mode = mode;
            return this;
        }

        public ScatterGeoBuilder marker(Marker marker) {
            this.marker = marker;
            return this;
        }

        public ScatterGeoBuilder textPosition(TextPosition position) {
            this.textPosition = new TextPosition[1];
            this.textPosition[0] = position;
            return this;
        }

        public ScatterGeoBuilder textPosition(TextPosition[] position) {
            Preconditions.checkArgument(position.length > 0);
            this.textPosition = position;
            return this;
        }

        public ScatterGeoBuilder text(String[] text) {
            Preconditions.checkArgument(text.length > 0);
            this.text = text;
            return this;
        }

        public ScatterGeoBuilder text(String text) {
            this.text = new String[1];
            this.text[0] = text;
            return this;
        }

        public ScatterGeo build() {
            return new ScatterGeo(this);
        }

        @Override
        protected String getType() {
            return type;
        }
    }
}
