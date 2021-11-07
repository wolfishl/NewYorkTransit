import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Path{
    List<Stations.Station> stationsOnPath;
    int length;

   public Path(int length, List<Stations.Station> stationsOnPath)
   {
       this.length = length;
       this.stationsOnPath = stationsOnPath;
   }




}
