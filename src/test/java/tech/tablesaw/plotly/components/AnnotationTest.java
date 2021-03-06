package tech.tablesaw.plotly.components;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
public class AnnotationTest {
  @Test
  public void asJSON() {
    Annotation annotation =
        Annotation.builder()
            .xref("paper")
            .yref("paper")
            .x(0)
            .y(0)
            .yanchor(Annotation.Yanchor.BOTTOM)
            .bordercolor("#c7c7c7")
            .font(Font.builder().build())
            .text("X asis label")
            .showarrow(false)
            .build();
    System.out.println(annotation.asJSON());
  }

  @Test
  public void asJavascript2() {
    Annotation annotation =
        Annotation.builder()
            .xref("x")
            .yref("y")
            .x(0)
            .y(0)
            .yanchor(Annotation.Yanchor.BOTTOM)
            .bordercolor("#c7c7c7")
            .font(Font.builder().family(Font.Family.SANS_SERIF).size(16).color("#ffffff").build())
            .text("max=5")
            .align(Annotation.Align.CENTER)
            .arrowhead(2)
            .arrowsize(1)
            .arrowwidth(2)
            .ax(20)
            .ay(-30)
            .font(Font.builder().color("rgba(0,0,0,0)").build())
            .showarrow(true)
            .build();
    System.out.println(annotation.asJSON());
  }
}
