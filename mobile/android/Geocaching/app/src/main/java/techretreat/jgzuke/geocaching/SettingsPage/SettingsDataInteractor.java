package techretreat.jgzuke.geocaching.SettingsPage;

import android.content.Context;

public class SettingsDataInteractor {

    public interface DataReciever {
    }

    private String userId;
    private Context context;
    private DataReciever reciever;

    public SettingsDataInteractor(String userId, Context context, DataReciever reciever) {
        this.userId = userId;
        this.context = context;
        this.reciever = reciever;
    }
}