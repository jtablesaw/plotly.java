package tech.tablesaw.plotly.traces;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.Preconditions;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import tech.tablesaw.plotly.components.*;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.util.Map;

import static tech.tablesaw.plotly.Utils.dataAsString;

/**
 * A `contour` trace is an object with the key `"type"` equal to `"contour"` (i.e. `{"type": "contour"}`)
 * and any of the keys listed below.
 *
 * The data from which contour lines are computed is set in `z`. Data in `z` must be a 2D array of numbers.
 * Say that `z` has N rows and M columns, then by default,
 * these N rows correspond to N y coordinates (set in `y` or auto-generated)
 * and the M columns correspond to M x coordinates (set in `x` or auto-generated).
 * By setting `transpose` to "true", the above behavior is flipped.
 */
public class ContourTrace extends AbstractTrace {

  public enum Calendar {
    CHINESE,
    COPTIC,
    DISCWORLD,
    ETHIOPIAN,
    GREGORIAN,
    HEBREW,
    ISLAMIC,
    JALALI,
    JULIAN,
    MAYAN,
    NANAKSHAHI,
    NEPALI,
    PERSIAN,
    TAIWAN,
    THAI,
    UMMALQURA;

    @JsonValue
    @Override
    public String toString() {
      return this.name().toLowerCase();
    }
  }
  /**
   * subplotid
   *
   * Sets a reference to a shared color axis.
   * References to these shared color axes are "coloraxis", "coloraxis2", "coloraxis3", etc.
   * Settings for these shared color axes are set in the layout, under `layout.coloraxis`, `layout.coloraxis2`, etc.
   * Note that multiple color scales can be linked to the same color axis.
   */
  private final String colorAxis;

  private final Object[] x;
  private final Object[] y;
  private final double[][] z;
  private final String type;
  private final Marker.Palette colorScale;
  private final boolean autoColorScale;
  private final boolean autoContour;
  private final boolean connectGaps;
  private final boolean showScale;
  private final boolean reverseScale;
  private final Calendar xCalendar;
  private final Calendar yCalendar;
  private final boolean transpose;
  private final boolean hoverOnGaps;
  private final Contours contours;

  public ContourTrace(ContourBuilder builder) {
    super(builder);
    this.x = builder.x;
    this.y = builder.y;
    this.z = builder.z;
    this.autoColorScale = builder.autoColorScale;
    this.colorScale = builder.colorScale;
    this.autoContour = builder.autoContour;
    this.connectGaps = builder.connectGaps;
    this.reverseScale = builder.reverseScale;
    this.showScale = builder.showScale;

    this.transpose = builder.transpose;
    this.hoverOnGaps = builder.hoverOnGaps;

    this.contours = builder.contours;
    this.type = builder.getType();

    this.xCalendar = builder.xCalendar;
    this.yCalendar = builder.yCalendar;

    this.colorAxis = builder.colorAxis;
  }

  @Override
  public String asJavascript(int i) {
    Writer writer = new StringWriter();
    PebbleTemplate compiledTemplate;

    try {
      compiledTemplate = engine.getTemplate("trace_template.html");
      compiledTemplate.evaluate(writer, getContext());
    } catch (PebbleException e) {
      throw new IllegalStateException(e);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
    return writer.toString();
  }

  @Override
  protected Map<String, Object> getContext() {

    Map<String, Object> context = super.getContext();
    context.put("variableName", "trace0");
    context.put("x", dataAsString(x));
    context.put("y", dataAsString(y));
    context.put("z", dataAsString(z));
    context.put("type", type);

    return context;
  }

  public static ContourTrace.ContourBuilder builder(Object[] x, Object[] y, double[][] z) {
    return new ContourTrace.ContourBuilder(x, y, z);
  }

  public static class ContourBuilder extends TraceBuilder {

    private static final String type = "contour";
    private final Object[] x;
    private final Object[] y;
    private final double[][] z;
    public Contours contours;
    private String uiRevision = "layout.uirevision";
    private ColorBar colorBar;
    private boolean autoColorScale = false;
    private Marker.Palette colorScale;
    private boolean autoContour = true;
    public boolean connectGaps;

    private boolean reverseScale = false;
    private boolean showScale = true;
    private int nContours = 15;
    private String fillColor;
    private HoverLabel hoverLabel;
    private boolean hoverOnGaps = true;
    private boolean transpose;

    private Calendar xCalendar = Calendar.GREGORIAN;
    private Calendar yCalendar = Calendar.GREGORIAN;

    private String colorAxis;

    ContourBuilder(Object[] x, Object[] y, double[][] z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }

    public ContourBuilder counters(Contours contours) {
      this.contours = contours;
      return this;
    }

    public ContourBuilder colorScale(Marker.Palette colorScale) {
      this.colorScale = colorScale;
      return this;
    }

    /**
     * number or categorical coordinate string
     *
     * Controls persistence of some user-driven changes to the trace: `constraintrange` in `parcoords` traces,
     * as well as some `editable: true` modifications such as `name` and `colorbar.title`.
     * Defaults to `layout.uirevision`.
     * Note that other user-driven trace attribute changes are controlled by `layout` attributes:
     * `trace.visible` is controlled by `layout.legend.uirevision`,
     * `selectedpoints` is controlled by `layout.selectionrevision`, and `
     * colorbar.(x|y)` (accessible with `config: {editable: true}`) is controlled by `layout.editrevision`.
     *
     * Trace changes are tracked by `uid`, which only falls back on trace index if no `uid` is provided.
     * So if your app can add/remove traces before the end of the `data` array,
     * such that the same trace has a different index, you can still preserve user-driven changes
     * if you give each trace a `uid` that stays with it as it moves.
     */
    public ContourBuilder uiRevision(String uiRevision) {
      this.uiRevision = uiRevision;
      return this;
    }

    public ContourBuilder colorAxis(String colorAxis) {
      this.colorAxis = colorAxis;
      return this;
    }

    /**
     * Sets the calendar system to use with the x data
     * default = gregorian
     */
    public ContourBuilder xCalendar(Calendar calendar) {
      this.xCalendar = calendar;
      return this;
    }

    /**
     * Sets the calendar system to use with the y data
     * default = gregorian
     */
    public ContourBuilder yCalendar(Calendar calendar) {
      this.yCalendar = calendar;
      return this;
    }

    /**
     * Transposes the z data.
     */
    public ContourBuilder transpose(boolean transpose) {
      this.transpose = transpose;
      return this;
    }

    /**
     * Determines whether or not gaps (i.e. {nan} or missing values) in the `z` data are filled in.
     * It is defaulted to true if `z` is a one dimensional array otherwise it is defaulted to false.
     */
    public ContourBuilder connectGaps(boolean connectGaps) {
      this.connectGaps = connectGaps;
      return this;
    }

    /**
     * Sets the maximum number of contour levels.
     * The actual number of contours will be chosen automatically to be less than or equal to the value of
     * `ncontours`. Has an effect only if `autocontour` is "true" or if `contours.size` is missing.
     */
    public ContourBuilder nContours(int nContours) {
      Preconditions.checkArgument(nContours > 0);
      this.nContours = nContours;
      return this;
    }

    /**
     * default = true
     * Determines whether or not gaps (i.e. {nan} or missing values) in the `z` data
     * have hover labels associated with them.
     */
    public ContourBuilder hoverOnGaps(boolean hoverOnGaps) {
      this.hoverOnGaps = hoverOnGaps;
      return this;
    }


    @Override
    public ContourTrace.ContourBuilder xAxis(String xAxis) {
      super.xAxis(xAxis);
      return this;
    }

    @Override
    public ContourTrace.ContourBuilder yAxis(String yAxis) {
      super.yAxis(yAxis);
      return this;
    }

    /**
     * default = true
     * Sets a reference to a shared color axis.
     * References to these shared color axes are "coloraxis", "coloraxis2", "coloraxis3", etc.
     * Settings for these shared color axes are set in the layout, under `layout.coloraxis`, `layout.coloraxis2`, etc.
     * Note that multiple color scales can be linked to the same color axis.
     **/
    public ContourTrace.ContourBuilder autoContour(boolean autoContour) {
      this.autoContour = autoContour;
      return this;
    }

    public ContourTrace.ContourBuilder reverseScale(boolean reverseScale) {
      this.reverseScale = reverseScale;
      return this;
    }

    public ContourTrace.ContourBuilder showScale(boolean showScale) {
      this.showScale = showScale;
      return this;
    }

    public ContourTrace.ContourBuilder autoColorScale(boolean autoColorScale) {
      this.autoColorScale = autoColorScale;
      return this;
    }

    public ContourTrace build() {
      return new ContourTrace(this);
    }

    @Override
    protected String getType() {
      return type;
    }
  }
}
