import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Stations {

    List<Feature> features;

    public class Feature{
        Property properties;
        Geometry geometry;
    }

    public class Property{
        String name;
        String line;
        String objectid;

        public String[] parseLines()
        {
            return line.split("-");
        }

        public List<String> getConnectingStations() throws IOException {
            String[] thisStationLines = this.parseLines();
            List<String> connectionStations =  new ArrayList<>();
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/SubwayLines.json"));
            SubwayLines lines = gson.fromJson(reader, SubwayLines.class);
            lines.addToList();
            for(String train : thisStationLines)
            {
                for (Train each : lines.allTrains)
                {
                    if (each.name == train)
                    {
                        addStations(connectionStations, each.stations);
                        break;
                    }
                }
            }
            return connectionStations;
        }

        public void addStations(List<String> stations, List<String> train)
        {
            for (int i = 0; i < train.size(); i++)
            {
                if(train.get(i) == this.objectid)
                {
                    if (i != 0) {
                        stations.add(train.get(i-1));
                    }
                    if(i != train.size()-1)
                    {
                        stations.add(train.get(i+1));
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
