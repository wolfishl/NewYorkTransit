import java.util.Comparator;

public class PathComparator implements Comparator<Stations.Station> {

    @Override
    public int compare(Stations.Station station1, Stations.Station station2) {
        if (station1.shortestPath == station2.shortestPath)
        {
            return 0;
        }
        else if (station1.shortestPath > station2.shortestPath)
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }
}
