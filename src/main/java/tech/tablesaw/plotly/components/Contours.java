package tech.tablesaw.plotly.components;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public class Contours extends Component {

    /**
     * Determines the coloring method showing the contour values.
     * If "fill", coloring is done evenly between each contour level
     * If "heatmap", a heatmap gradient coloring is applied between each contour level.
     * If "lines", coloring is done on the contour lines.
     * If "none", no coloring is applied on this trace.
     */
    public enum Coloring {
        FILL("fill"),
        HEATMAP("heatmap"),
        LINES("lines"),
        NONE("none");

        Coloring(String value) {
            this.value = value;
        }

        private final String value;

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }
    }

    /**
     * Default: "="
     * Sets the constraint operation. "=" keeps regions equal to `value`
     * "<" and "<=" keep regions less than `value` ">" and ">=" keep regions greater than `value`
     * "[]", "()", "[)", and "(]" keep regions inside `value[0]` to `value[1]`
     * "][", ")(", "](", ")[" keep regions outside `value[0]` to value[1]` Open vs. closed intervals make no difference
     * to constraint display, but all versions are allowed for consistency with filter transforms.
     */
    public enum Operations {
        EQUAL("="),
        LESS_THAN("<"),
        GREATER_THAN(">"),
        LESS_THAN_OR_EQUAL("<="),
        GREATER_THAN_OR_EQUAL(">="),

        CLOSED("[]"),
        OPEN("()"),
        CLOSED_OPEN("[)"),
        OPEN_CLOSED("(]"),
        OUTSIDE_CLOSED("]["),
        OUTSIDE_OPEN(")("),
        OUTSIDE_CLOSED_OPEN("]("),
        OUTSIDE_OPEN_CLOSED(")[");

        private final String value;

        Operations(String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return value;
        }
    }

    private final Operations operations;
    private final Coloring coloring;
    private final double number;
    private final Font labelFont;
    private final String labelFormat;
    private final boolean showLabels;
    private final boolean showLines;
    private final double size;

    public Contours(ContoursBuilder builder) {
        this.coloring = builder.coloring;
        this.number = builder.number;
        this.labelFont = builder.labelFont;
        this.labelFormat = builder.labelFormat;
        this.showLabels = builder.showLabels;
        this.showLines = builder.showLines;
        this.size = builder.size;
        this.operations = builder.operations;
    }

    @Override
    protected Map<String, Object> getJSONContext() {
        Map<String, Object> context = new HashMap<>();

        return context;
    }


    static ContoursBuilder builder() {
        return new ContoursBuilder();
    }

    static class ContoursBuilder {

        public boolean showLabels;
        public boolean showLines;
        public double size;
        public Operations operations = Operations.EQUAL;
        private Coloring coloring;
        private double number;
        private Font labelFont;
        private String labelFormat = "";

        public ContoursBuilder coloring(Coloring coloring) {
            this.coloring = coloring;
            return this;

        }

        public ContoursBuilder number(double number) {
            this.number = number;
            return this;
        }

        public ContoursBuilder labelFont(Font font) {
            this.labelFont = font;
            return this;
        }

        public ContoursBuilder labelFormat(String format) {
            this.labelFormat = format;
            return this;
        }

        private Contours build() {
            return new Contours(this);
        }
    }
}
