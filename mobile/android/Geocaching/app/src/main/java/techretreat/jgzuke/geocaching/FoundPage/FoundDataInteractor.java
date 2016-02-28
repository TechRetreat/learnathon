package techretreat.jgzuke.geocaching.FoundPage;

import android.content.Context;

import techretreat.jgzuke.geocaching.DataUtilities;

public class FoundDataInteractor {

    public interface DataReceiver {
        void getFoundCaches(FoundCaches.Cache[] caches);
    }

    private String userId;
    private Context context;
    private DataReceiver reciever;

    public FoundDataInteractor(String userId, Context context, DataReceiver reciever) {
        this.userId = userId;
        this.context = context;
        this.reciever = reciever;
    }

    public void getFoundCaches() {
        FoundCaches response = new DataUtilities<FoundCaches>().getResponse(context, FoundCaches.class);
        reciever.getFoundCaches(response.caches);
    }
}
