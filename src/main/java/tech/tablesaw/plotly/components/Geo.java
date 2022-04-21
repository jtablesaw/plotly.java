package tech.tablesaw.plotly.components;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public class Geo extends Component {

    public enum Resolution {
        FIFTY("50"),
        ONE_HUNDRED_TEN("110");

        private final String value;

        Resolution(String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return value;
        }

    }

    private final String scope;
    private final Resolution resolution; // TODO: Double or Int

    private final boolean showRivers;
    private final boolean showLakes;
    private final boolean showLand;

    private final String riverColor;
    private final String lakeColor;
    private final String landColor;
    private final String countryColor;
    private final String subUnitColor;
    private final Axis lonAxis;
    private final Axis latAxis;

    private final double countryWidth;

    Geo(GeoBuilder builder) {
        this.scope = builder.scope;
        this.resolution = builder.resolution;
        this.showRivers = builder.showRivers;
        this.showLakes = builder.showLakes;
        this.showLand = builder.showLand;
        this.riverColor = builder.riverColor;
        this.lakeColor = builder.lakeColor;
        this.landColor = builder.landColor;
        this.countryColor = builder.countryColor;
        this.subUnitColor = builder.subUnitColor;
        this.countryWidth = builder.countryWidth;
        this.lonAxis = builder.lonAxis;
        this.latAxis = builder.latAxis;
    }

    @Override
    protected Map<String, Object> getJSONContext() {
        Map<String, Object> context = new HashMap<>();
        if (scope != null) context.put("scope", scope);
        if (showRivers) context.put("showrivers", showRivers);
        if (showRivers) context.put("showlakes", showLakes);
        if (showRivers) context.put("showland", showLand);
        if (showRivers) context.put("showrivers", showRivers);
        if (countryColor != null) context.put("countrycolor", countryColor);
        if (riverColor != null) context.put("rivercolor", riverColor);
        if (lakeColor != null) context.put("lakecolor", lakeColor);
        if (landColor != null) context.put("landcolor", landColor);
        if (resolution != null) context.put("resolution", resolution);
        if (lonAxis != null) context.put("lonaxis", lonAxis);
        if (latAxis != null) context.put("lataxis", latAxis);
        if (countryWidth > 0) context.put("countrywidth", countryWidth);

        return context;
    }

    public static GeoBuilder builder() {
        return new GeoBuilder();
    }

    public static class GeoBuilder {

        public String scope;
        public Resolution resolution = Resolution.ONE_HUNDRED_TEN;
        public boolean showRivers;
        public boolean showLakes;
        public boolean showLand;
        public String riverColor;
        public String lakeColor;
        public String landColor;
        public String countryColor;
        public String subUnitColor;
        public double countryWidth;
        public Axis lonAxis;
        public Axis latAxis;

        public GeoBuilder scope(String scope) {
            this.scope = scope;
            return this;
        }

        public GeoBuilder resolution(Resolution resolution) {
            this.resolution = resolution;
            return this;
        }

        public GeoBuilder showRivers(boolean showRivers) {
            this.showRivers = showRivers;
            return this;
        }

        public GeoBuilder showLakes(boolean showLakes) {
            this.showLakes = showLakes;
            return this;
        }

        public GeoBuilder showLand(boolean showLand) {
            this.showLand = showLand;
            return this;
        }

        public GeoBuilder riverColor(String riverColor) {
            this.riverColor = riverColor;
            return this;
        }

        public GeoBuilder lakeColor(String lakeColor) {
            this.lakeColor = lakeColor;
            return this;
        }

        public GeoBuilder landColor(String landColor) {
            this.landColor = landColor;
            return this;
        }

        public GeoBuilder countryColor(String countryColor) {
            this.countryColor = countryColor;
            return this;
        }

        public GeoBuilder subUnitColor(String subUnitColor) {
            this.subUnitColor = subUnitColor;
            return this;
        }

        public GeoBuilder countryWidth(double contryWidth) {
            this.countryWidth = contryWidth;
            return this;
        }

        public GeoBuilder lonAxis(Axis lonAxis) {
            this.lonAxis = lonAxis;
            return this;
        }

        public GeoBuilder latAxis(Axis latAxis) {
            this.latAxis = latAxis;
            return this;
        }

        public Geo build() {
            return new Geo(this);
        }
    }
}
