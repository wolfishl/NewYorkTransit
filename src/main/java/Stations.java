import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Stations {

    List<Station> features;
    

    public Path findShortestPath(Station startingStation, Station endingStation) throws IOException
    {
        Path shortestPath = new Path(Integer.MAX_VALUE);
        startingStation.shortestPath = new Path(startingStation);

        // create priority queue
        PriorityQueue<Station> stationsToCheck = new PriorityQueue<Station>(new PathComparator());
        stationsToCheck.add(startingStation);

        // begin searching
        while (stationsToCheck.peek() != null)
        {
            Station currentStation = stationsToCheck.poll();
            currentStation.properties.getConnectingStations(this);
            currentStation.checked = true;

            // check if is ending station
            if (currentStation.properties.objectid == endingStation.properties.objectid)
            {
                shortestPath = currentStation.shortestPath;
                break;
            }

            // add connecting stations to queue
            for (Station nextStation : currentStation.properties.connectingStations)
            {
                if (nextStation.checked != null)
                {
                    continue;
                }

                //check if already has path
                if (nextStation.shortestPath != null)
                {
                    //is on queue
                    Path potentialPath = currentStation.shortestPath.addStationToNewPath(nextStation);
                    if (potentialPath.length < nextStation.shortestPath.length)
                    {
                        nextStation.shortestPath = potentialPath;
                    }
                }
                else
                {
                    nextStation.shortestPath = currentStation.shortestPath.addStationToNewPath(nextStation);
                    stationsToCheck.add(nextStation);
                }


            }
        }
        return shortestPath;
    }

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
        Property properties;
        Geometry geometry;
        Boolean checked;
        Path shortestPath;

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


    }

    public class Property{
        String name;
        String line;
        String[] parsedLines;
        Integer objectid;
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
