package techretreat.jgzuke.geocaching.FoundPage;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

public class FoundDataInteractor {

    public interface FoundDataReciever {
        void getFoundCaches(Caches.Cache[] caches);
    }

    private String userId;
    private Context context;
    private FoundDataReciever reciever;

    public FoundDataInteractor(String userId, Context context, FoundDataReciever reciever) {
        this.userId = userId;
        this.context = context;
        this.reciever = reciever;
    }

    public void getFoundCaches() {
        try {
            InputStream is = context.getAssets().open("cachesFound.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");

            Gson gson = new Gson();
            Caches response = gson.fromJson(json, Caches.class);
            reciever.getFoundCaches(response.caches);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
