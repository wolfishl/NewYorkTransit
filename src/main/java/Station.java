import java.util.List;

public class Station {

    List<Feature> features;

    public class Feature{
        Property properties;
        Geometry geometry;
    }

    public class Property{
        String name;
        String line;
    }

    public class Geometry{
        double[] coordinates;
    }

    

}
