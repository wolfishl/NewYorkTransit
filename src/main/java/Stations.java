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
