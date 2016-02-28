package techretreat.jgzuke.geocaching.FoundPage;

public class Caches {
    public Cache[] caches;

    public static class Cache {
        public int difficulty;
        public long found;
        public Location location;
    }

    public static class Location {
        public double latitude;
        public double longitude;
    }
}
