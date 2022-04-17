package tech.tablesaw.plotly.traces;

import static tech.tablesaw.plotly.Utils.dataAsString;

import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import tech.tablesaw.plotly.components.Marker;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.util.Map;

public class ViolinTrace extends AbstractTrace {

  private final Object[] x;
  private final double[] y;
  private final boolean showBoxPlot;
  private final boolean showMeanLine;
  private final Marker marker;

  private ViolinTrace(ViolinBuilder builder) {
    super(builder);
    this.x = builder.x;
    this.y = builder.y;
    this.showMeanLine = builder.showMeanLine;
    this.showBoxPlot = builder.showBoxPlot;
    this.marker = builder.marker;
  }

  public static ViolinBuilder builder(Object[] x, double[] y) {
    return new ViolinBuilder(x, y);
  }

  public static ViolinBuilder builder(double[] x, double[] y) {
    Double[] xObjs = new Double[x.length];
    for (int i = 0; i < x.length; i++) {
      xObjs[i] = x[i];
    }
    return new ViolinBuilder(xObjs, y);
  }

  @Override
  public String asJavascript(int i) {
    Writer writer = new StringWriter();
    PebbleTemplate compiledTemplate;

    try {
      compiledTemplate = engine.getTemplate("trace_template.html");
      compiledTemplate.evaluate(writer, getContext(i));
    } catch (PebbleException e) {
      throw new IllegalStateException(e);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
    return writer.toString();
  }

  private Map<String, Object> getContext(int i) {

    Map<String, Object> context = super.getContext();
    context.put("variableName", "trace" + i);
    context.put("y", dataAsString(y));
    context.put("x", dataAsString(x));
    if (showBoxPlot) {
      context.put("box", "{visible: true}");
    }
    if (showMeanLine) {
      context.put("meanLine", "{visible: true}");
    }
    if (marker != null) {
      context.put("marker", marker);
    }
    return context;
  }

  public static class ViolinBuilder extends TraceBuilder {

    private static final String type = "violin";
    private final Object[] x;
    private final double[] y;
    private boolean showBoxPlot;
    private boolean showMeanLine;
    private Marker marker;

    ViolinBuilder(Object[] x, double[] y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public ViolinBuilder name(String name) {
      super.name(name);
      return this;
    }

    public ViolinBuilder boxPlot(boolean show) {
      this.showBoxPlot = show;
      return this;
    }

    public ViolinBuilder meanLine(boolean show) {
      this.showMeanLine = show;
      return this;
    }

    public ViolinBuilder marker(Marker marker) {
      this.marker = marker;
      return this;
    }

    public ViolinTrace build() {
      return new ViolinTrace(this);
    }

    @Override
    public ViolinBuilder xAxis(String xAxis) {
      super.xAxis(xAxis);
      return this;
    }

    @Override
    public ViolinBuilder yAxis(String yAxis) {
      super.yAxis(yAxis);
      return this;
    }

    @Override
    protected String getType() {
      return type;
    }
  }
}
