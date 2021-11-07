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
        Path shortestPath = null;

        // create priority queue
        PriorityQueue<Station> stationsToCheck = new PriorityQueue<>(new PathComparator());
        Iterator<Station> iterator = stationsToCheck.iterator();
        for (Station station : this.features)
        {
            station.checked = false;

            // starting station
            if (station.properties.objectid == startingStation.properties.objectid)
            {
                station.shortestPath = 0;
                station.stationsOnPath = new ArrayList<>();
                station.stationsOnPath.add(station);
                stationsToCheck.add(startingStation);
            }

            // other stations
            else
            {
                station.shortestPath = Integer.MAX_VALUE;
                station.stationsOnPath = new ArrayList<>();
                stationsToCheck.add(station);
            }
        }

        // begin searching
        while (stationsToCheck.peek() != null)
        {
            Station currentStation = stationsToCheck.poll();
            currentStation.properties.getConnectingStations(this);
            currentStation.checked = true;


            // check if is ending station
            if (currentStation.equals(endingStation))
            {
                shortestPath = new Path(currentStation.shortestPath, currentStation.stationsOnPath);
                break;
            }

            // add values to connecting stations on queue
            for (Station nextStation : currentStation.properties.connectingStations)
            {
                if (nextStation.checked)
                {
                    continue;
                }


                //check if path is shorter
                else
                {
                    for (Station stationInQueue : stationsToCheck)
                    {
                        if (stationInQueue.equals(nextStation))
                        {
                            if (stationInQueue.shortestPath > currentStation.shortestPath + 1)
                            {
                                stationsToCheck.remove(stationInQueue);
                                nextStation.shortestPath = currentStation.shortestPath + 1;
                                nextStation.stationsOnPath = new ArrayList<>();
                                for (Station eachStation : currentStation.stationsOnPath)
                                {
                                    nextStation.stationsOnPath.add(eachStation);
                                }
                                nextStation.stationsOnPath.add(nextStation);
                                stationsToCheck.add(nextStation);
                            }
                            break;
                        }
                    }
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
        int shortestPath;
        List<Station> stationsOnPath;


        @Override
        public boolean equals(Object o) {
            if (o == null || !(o instanceof Station))
            {
                return false;
            }
            Station otherStation = (Station) o;
            if(this.properties.objectid == otherStation.properties.objectid)
            {
                return true;
            }
            else return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(properties.objectid);
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
                        Station previousStation = stations.findStation(trainStops.get(i-1));
                        if (!this.connectingStations.contains(previousStation))
                        {
                            this.connectingStations.add(previousStation);
                        }
                    }
                    if(i != trainStops.size()-1)
                    {
                        Station nextStation = stations.findStation(trainStops.get(i + 1));
                        if (!this.connectingStations.contains(nextStation))
                        {
                            this.connectingStations.add(nextStation);
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
