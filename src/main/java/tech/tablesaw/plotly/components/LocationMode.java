package tech.tablesaw.plotly.components;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Determines the set of locations used to match entries in `locations` to regions on the map.
 * Values "ISO-3", "USA-states", "country names" correspond to features on the base map
 * and value "geojson-id" corresponds to features from a custom GeoJSON linked to the `geojson` attribute.
 */
public enum LocationMode {
    ISO_3("ISO-3"),
    USA_STATES("USA-states"),
    COUNTRY_NAMES("country names"),
    GEOJSON_ID("geojson-id");

    private final String value;

    LocationMode(String value) {
        this.value = value;
    }

    @JsonValue
    @Override
    public String toString() {
        return value;
    }
}
