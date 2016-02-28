package techretreat.jgzuke.geocaching.SettingsPage;

import android.content.Context;

public class SettingsDataInteractor {

    public interface DataReceiver {
    }

    private String userId;
    private Context context;
    private DataReceiver reciever;

    public SettingsDataInteractor(String userId, Context context, DataReceiver reciever) {
        this.userId = userId;
        this.context = context;
        this.reciever = reciever;
    }
}