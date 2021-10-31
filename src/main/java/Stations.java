import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Stations {

    List<Station> features;

    public class Station{
        Property properties;
        Geometry geometry;
    }

    public class Property{
        String name;
        String line;
        String[] parsedLines;
        Integer objectid;
        SubwayLines lines;
        List<Integer> connectingStations;

        public void parseLines()
        {
            parsedLines = line.split("-");
        }

        public void getConnectingStations() throws IOException {
            this.parseLines();
            String[] thisStationLines = parsedLines;
            if (lines == null) {
                Gson gson = new Gson();
                Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/SubwayLines.json"));
                lines = gson.fromJson(reader, SubwayLines.class);
            }
            lines.addToList();
            if(connectingStations == null)
            {
                connectingStations = new ArrayList<>();
            }
            for(String train : thisStationLines)
            {
                for (Train each : lines.allTrains)
                {
                    if (each.name.equals(train))
                    {
                        addStations(each.stations);
                        break;
                    }
                }
            }
        }

        private void addStations(List<Integer> trainStops)
        {
            for (int i = 0; i < trainStops.size(); i++)
            {
                if(trainStops.get(i).equals(this.objectid))
                {
                    if (i != 0) {
                        if (!this.connectingStations.contains(trainStops.get(i-1)))
                        {
                            this.connectingStations.add(trainStops.get(i-1));
                        }
                    }
                    if(i != trainStops.size()-1)
                    {
                        if (!this.connectingStations.contains(trainStops.get(i+1))) {
                            this.connectingStations.add(trainStops.get(i + 1));
                        }
                    }
                    break;
                }
            }
        }
    }

    public class Geometry{
        double[] coordinates;

        public double getx()
        {
            return coordinates[0];
        }

        public double gety()
        {
            return coordinates[1];
        }
    }




}
