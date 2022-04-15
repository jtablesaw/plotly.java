package tech.tablesaw.plotly.components;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public class Config extends Component {

  public static enum ModeBarDisplay {
    ALWAYS("true"),
    NEVER("false"),
    ON_HOVER("on-hover"); // This is the default

    private final String value;

    ModeBarDisplay(String value) {
      this.value = value;
    }

    @JsonValue
    @Override
    public String toString() {
      return value;
    }
  }

  private final ModeBarDisplay displayModeBar;
  private final Boolean responsive;
  private final Boolean displayLogo;

  private Config(Builder builder) {
    this.displayModeBar = builder.displayModeBar;
    this.responsive = builder.responsive;
    this.displayLogo = builder.displayLogo;
  }

  public static Builder builder() {
    return new Builder();
  }

  public String asJavascript() {
    return "var config = " + asJSON();
  }

  @Override
  protected Map<String, Object> getJSONContext() {
    return getContext();
  }

  protected Map<String, Object> getContext() {
    Map<String, Object> context = new HashMap<>();

    // handle modebar display. ON_HOVER is the default, so we do nothing
    if (displayModeBar == ModeBarDisplay.NEVER) {
      context.put("displayModeBar", false);
    } else if (displayModeBar == ModeBarDisplay.ALWAYS) {
      context.put("displayModeBar", true);
    }

    context.put("responsive", responsive);
    context.put("displaylogo", displayLogo);
    return context;
  }

  public static class Builder {

    ModeBarDisplay displayModeBar = ModeBarDisplay.ON_HOVER;
    Boolean responsive = Boolean.TRUE;
    Boolean displayLogo = Boolean.FALSE;

    private Builder() {}

    public Builder setModeBarDisplay(ModeBarDisplay modeBarDisplay) {
      this.displayModeBar = modeBarDisplay;
      return this;
    }

    public Builder responsive(boolean responsive) {
      this.responsive = responsive;
      return this;
    }

    public Builder displayLogo(boolean displayLogo) {
      this.displayLogo = displayLogo;
      return this;
    }

    public Config build() {
      return new Config(this);
    }
  }
}
