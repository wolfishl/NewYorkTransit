import com.google.gson.Gson;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class SubwayLinesTest {

    @Test
    public void checkJson() throws IOException
    {
        //given
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/SubwayLines.json"));

        //when
        SubwayLines lines = gson.fromJson(reader, SubwayLines.class);
        lines.addToList();

        //then
        assertNotNull(lines);
        assertEquals((Integer)55, lines.A.get(0));
        assertEquals((Integer)247, lines.Z.get(0));
        assertEquals((Integer)29, lines.sixExpress.get(0));
        assertEquals((Integer)25, lines.seven.get(0));
    }

}