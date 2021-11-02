import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Stations {

    List<Station> features;

    public Station findStation(int id)
    {
        for (Station station: features)
        {
            if ((int)station.properties.objectid == id)
            {
                return station;
            }
        }
        return null;
    }

    public class Station{

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Station station = (Station) o;
            return Objects.equals(properties, station.properties) && Objects.equals(geometry, station.geometry);
        }

        @Override
        public int hashCode() {
            return Objects.hash(properties, geometry);
        }

        Property properties;
        Geometry geometry;

    }

    public class Property{
        String name;
        String line;
        String[] parsedLines;
        int objectid;
        SubwayLines lines;
        List<Station> connectingStations;

        public void parseLines()
        {
            parsedLines = line.split("-");
        }

        public void getConnectingStations(Stations stations) throws IOException {
            this.parseLines();
            String[] thisStationLines = parsedLines;
            if (lines == null) {
                Gson gson = new Gson();
                Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/SubwayLines.json"));
                lines = gson.fromJson(reader, SubwayLines.class);
            }
            lines.addToList();
            if(this.connectingStations == null)
            {
                this.connectingStations = new ArrayList<>();
            }
            for(String train : thisStationLines)
            {
                for (Train each : lines.allTrains)
                {
                    if (each.name.equals(train))
                    {
                        addStations(each.stations, stations);
                        break;
                    }
                }
            }
        }

        private void addStations(List<Integer> trainStops, Stations stations)
        {
            for (int i = 0; i < trainStops.size(); i++)
            {
                if(trainStops.get(i).equals(this.objectid))
                {
                    if (i != 0) {
                        if (!this.connectingStations.contains(trainStops.get(i-1)))
                        {
                            this.connectingStations.add(stations.findStation(trainStops.get(i-1)));
                        }
                    }
                    if(i != trainStops.size()-1)
                    {
                        if (!this.connectingStations.contains(trainStops.get(i+1)))
                        {
                            this.connectingStations.add(stations.findStation(trainStops.get(i + 1)));
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
