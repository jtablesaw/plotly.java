# plotly.java
Plotly.java is a third-party Java wrapper for the [Plotly JavaScript open source graphing library](https://plotly.com/javascript/), one of the finest graphing libraries available.  plotly.java lets you generate a wide range of JavaScript/HTML charts.  

Plotly.java was developed as part of the [Tablesaw Java dataframe library](https://github.com/jtablesaw/tablesaw), beginning in 2018, as a tool for exploratory data analysis. Dependencies on Tablesaw have  been removed to make it easier to incorporate visualizations into a broad range of Java applications, but it remains a key part of the overall data wrangling solution. 

## Plots created with plotly.java


| ![Tornadoes](https://jtablesaw.github.io/tablesaw/userguide/images/eda/box1.png) | ![Tornadoes](https://jtablesaw.github.io/tablesaw/userguide/images/eda/scatter_2_Yaxes.png) | ![Tornadoes](https://jtablesaw.github.io/tablesaw/userguide/images/tornado.scatter.png) |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| ![Tornadoes](https://jtablesaw.github.io/tablesaw/userguide/images/eda/bush_time_series2.png) | ![Tornadoes](https://jtablesaw.github.io/tablesaw/userguide/images/eda/hist_overlay.png) | ![Tornadoes](https://jtablesaw.github.io/tablesaw/userguide/images/eda/histogram2.png) |
| ![Tornadoes](https://jtablesaw.github.io/tablesaw/userguide/images/eda/histogram2d.png) | ![Tornadoes](https://jtablesaw.github.io/tablesaw/userguide/images/eda/pie.png) | ![Tornadoes](https://jtablesaw.github.io/tablesaw/userguide/images/eda/wine_bubble_3d.png) |
| ![](https://jtablesaw.github.io/tablesaw/userguide/images/eda/wine_bubble_with_groups.png) | ![](https://jtablesaw.github.io/tablesaw/userguide/images/eda/robberies_area.png) | ![](https://jtablesaw.github.io/tablesaw/userguide/images/ml/regression/wins%20by%20year.png) |
| ![Tornadoes](https://jtablesaw.github.io/tablesaw/userguide/images/eda/bush_heatmap1.png) | ![Tornadoes](https://jtablesaw.github.io/tablesaw/userguide/images/eda/tornado_bar_groups.png) | ![Tornadoes](https://jtablesaw.github.io/tablesaw/userguide/images/eda/ohlc1.png) |

## An Example:

Here's a simple example. Two data arrays are used to create an area plot. 

```java
package tech.tablesaw.examples;

import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.traces.ScatterTrace;

public class AreaPlotExample {

  /** Creates a simple area plot with an index (observation number) x axis */
  public static void main(String[] args) throws Exception {

    String title = "Boston Robberies: 1966-1967";
    double[] observation = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0};
    double[] count = {41.0, 39.0, 50.0, 40.0, 43.0, 38.0, 44.0, 35.0, 39.0, 35.0, 29.0, 49.0};

    Layout layout = Layout.builder(title, "Month", "Robberies").build();
    ScatterTrace trace =
            ScatterTrace.builder(observation, count)
                    .mode(ScatterTrace.Mode.LINE)
                    .fill(ScatterTrace.Fill.TO_NEXT_Y)
                    .fillColor("#b987fb")
                    .build();
    Plot.show(new Figure(layout, trace));
  }
}
```

As you can see, plotly.java has a builder API, that lets you configure each plot exactly as you want, while retaining Java's type safety. 

## How plots are rendered:

Since Plotly is a JavaScript library, rendering is ultimately handled by a browser. There are three basic ways:

- Send the plot to a browser from a Web app.
- Render the plot in your desktop's default browser from your IDE
- Render the plot in a Jupyter Notebook

## To get started:

Add tablesaw-plotly-java to your project. You can find the version number for the latest release in the [release notes](https://github.com/jtablesaw/tablesaw/releases):


```xml
<dependency>
    <groupId>tech.tablesaw</groupId>
    <artifactId>tablesaw-plotly-java</artifactId>
    <version>VERSION_NUMBER_GOES_HERE</version>
</dependency>
```

## Documentation:

The best source of documentation is the Plotly.js library documentation as everything in plotly.java exists solely to generate and render plotly.js code. 
