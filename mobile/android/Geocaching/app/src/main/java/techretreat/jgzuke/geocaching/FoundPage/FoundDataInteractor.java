package techretreat.jgzuke.geocaching.FoundPage;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

import techretreat.jgzuke.geocaching.R;

public class FoundDataInteractor {

    public interface DataReciever {
        void getFoundCaches(Caches.Cache[] caches);
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
        try {
            InputStream is = context.getResources().openRawResource(R.raw.caches_found);
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
