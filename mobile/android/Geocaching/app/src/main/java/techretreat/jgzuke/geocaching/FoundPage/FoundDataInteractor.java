package techretreat.jgzuke.geocaching.FoundPage;

import android.content.Context;

import techretreat.jgzuke.geocaching.DataUtilities;

public class FoundDataInteractor {

    public interface DataReciever {
        void getFoundCaches(FoundCaches.Cache[] caches);
    }

    private String userId;
    private Context context;
    private DataReciever reciever;

    public FoundDataInteractor(String userId, Context context, DataReciever reciever) {
        this.userId = userId;
        this.context = context;
        this.reciever = reciever;
    }

    public void getFoundCaches() {
        FoundCaches response = new DataUtilities<FoundCaches>().getResponse(context, FoundCaches.class);
        reciever.getFoundCaches(response.caches);
    }
}
