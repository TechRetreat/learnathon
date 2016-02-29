package techretreat.jgzuke.geocaching.MapPage;

import java.util.Map;

public class MapCaches {
    public Map<String, Cache> caches;

    public static class Cache {
        public String name;
        public String id;
        public int difficulty;
        public Location location;
    }

    public static class Location {
        public double latitude;
        public double longitude;
    }
}
