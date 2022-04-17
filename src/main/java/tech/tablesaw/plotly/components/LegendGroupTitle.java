package tech.tablesaw.plotly.components;

import java.util.HashMap;
import java.util.Map;

public class LegendGroupTitle extends Component {

    private final Font font;
    private final String text;

    private LegendGroupTitle(LegendGroupTitleBuilder builder) {
        this.font = builder.font;
        this.text = builder.text;
    }

    @Override
    protected Map<String, Object> getJSONContext() {
        Map<String, Object> context = new HashMap<>();
        if (font != null) {
            context.put("font", font);
        }
        context.put("text", text);
        return context;
    }

    public static LegendGroupTitleBuilder builder() {
        return new LegendGroupTitleBuilder();
    }

    static class LegendGroupTitleBuilder {

        private Font font;
        private String text = "";

        public LegendGroupTitleBuilder font(Font font) {
            this.font = font;
            return this;
        }

        public LegendGroupTitleBuilder text(String text) {
            this.text = text;
            return this;
        }

        public LegendGroupTitle build() {
            return new LegendGroupTitle(this);
        }

    }
}
