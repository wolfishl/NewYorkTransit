import com.google.gson.Gson;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;


import static org.junit.Assert.assertEquals;

public class StationTest{

    @Test
    public void checkJson() throws IOException
    {
        //given
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/SubwayStations.json"));

        //when
        Station station = gson.fromJson(reader, Station.class);

        //then
        assertEquals("Astor Pl", station.features.get(0).properties.name);
        assertEquals("4-6-6 Express", station.features.get(0).properties.line);
        assertEquals(-73.99106999861966, station.features.get(0).geometry.getx(), 0.01);
        assertEquals(40.73005400028978, station.features.get(0).geometry.gety(), 0.01);
        String[] lines = station.features.get(0).properties.parseLines();
        assertEquals("4", lines[0]);
        assertEquals("6", lines[1]);
        assertEquals("6 Express", lines[2]);
        assertEquals("1", station.features.get(0).properties.objectid);

    }

}