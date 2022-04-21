package tech.tablesaw.plotly.components;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UncheckedIOException;

import static org.junit.jupiter.api.Assertions.*;
import static tech.tablesaw.plotly.JsonMapper.JSON_MAPPER;

class LineTest {

    @Test
    void getJSONContext() {
        Line line = Line.builder().build();
        //System.out.println(line);

        Marker marker = Marker.builder().line(Line.builder().build()).build();
        System.out.println(marker);

        StringWriter w = new StringWriter();
        try {
            JSON_MAPPER.writeValue(w, line.getJSONContext());
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
        System.out.println(w);
    }
}