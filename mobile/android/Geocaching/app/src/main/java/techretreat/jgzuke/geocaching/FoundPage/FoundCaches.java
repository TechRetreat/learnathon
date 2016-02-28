package techretreat.jgzuke.geocaching.FoundPage;

public class FoundCaches {
    public Cache[] caches;

    public static class Cache {
        public String name;
        public String id;
        public int difficulty;
        public long found;
    }
}
