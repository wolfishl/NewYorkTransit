import java.util.ArrayList;
import java.util.List;

public class Path {
    List<Stations.Station> stationsOnPath;
    int length;

    public Path(Stations.Station station)
    {
        stationsOnPath = new ArrayList<>();
        stationsOnPath.add(station);
        length = 0;
    }

    public Path(List<Stations.Station> oldPath)
    {
        stationsOnPath = oldPath;
        length = stationsOnPath.size();
    }

    public void addToPath(Stations.Station station)
    {
        stationsOnPath.add(station);
        length++;
    }

    public Path addStationToNewPath(Stations.Station station)
    {
        Path newPath = new Path(stationsOnPath);
        newPath.addToPath(station);
        return newPath;
    }

}
