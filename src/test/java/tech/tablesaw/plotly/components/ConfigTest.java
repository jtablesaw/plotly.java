package tech.tablesaw.plotly.components;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tech.tablesaw.plotly.components.Config.ModeBarDisplay.ALWAYS;
import static tech.tablesaw.plotly.components.Config.ModeBarDisplay.NEVER;

import org.junit.jupiter.api.Test;

public class ConfigTest {

  @Test
  public void testJavascript() {
    {
      Config config = Config.builder().build();
      assertTrue(config.asJavascript().startsWith("var config"));
    }
    {
      Config config = Config.builder().setModeBarDisplay(ALWAYS).build();
      assertTrue(config.asJSON().contains("displayModeBar : true"));
    }
    {
      Config config = Config.builder().setModeBarDisplay(NEVER).build();
      System.out.println(config.asJavascript());
      assertTrue(config.asJSON().contains("displayModeBar : false"));
    }
    {
      Config config = Config.builder().build();
      assertFalse(config.asJSON().contains("displayModeBar"));
    }
    {
      Config config = Config.builder().responsive(true).build();
      assertTrue(config.asJSON().contains("responsive : true"));
    }
    {
      Config config = Config.builder().responsive(false).build();
      assertTrue(config.asJSON().contains("responsive : false"));
    }
    {
      Config config = Config.builder().build();
      assertTrue(config.asJSON().contains("responsive"));
    }
    {
      Config config = Config.builder().displayLogo(true).build();
      assertTrue(config.asJSON().contains("displaylogo : true"));
    }
    {
      Config config = Config.builder().displayLogo(false).build();
      assertTrue(config.asJSON().contains("displaylogo : false"));
    }
    {
      Config config = Config.builder().build();
      assertTrue(config.asJSON().contains("displaylogo"));
    }
  }
}
