package tech.tablesaw.plotly.traces;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Paths;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import tech.tablesaw.plotly.Plot;

@Disabled
class PlotTest {

  @Test
  void show() {
    String html = "<html><body><h1>Hello World<h1></body></html>";
    File file = Paths.get("testoutput", "myfile.html").toFile();
    Plot.show(html, file);
  }
}
