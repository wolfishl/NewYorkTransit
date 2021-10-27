import com.google.gson.Gson;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StationsTest {

    @Test
    public void checkJson() throws IOException
    {
        //given
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/SubwayStations.json"));

        //when
        Stations station = gson.fromJson(reader, Stations.class);

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

    @Test
    public void checkConnectingStations() throws IOException {
        //given
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/SubwayStations.json"));
        Stations stations = gson.fromJson(reader, Stations.class);

        //when
        List<String> connectingStations = stations.features.get(0).properties.getConnectingStations();

        //then
        assertEquals("Astor Pl", stations.features.get(0).properties.name);
        assertTrue(connectingStations.contains("457"));
        assertTrue(connectingStations.contains("105"));
    }

}