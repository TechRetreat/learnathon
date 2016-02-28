package techretreat.jgzuke.geocaching.SettingsPage;

import android.content.Context;
import android.support.v4.app.Fragment;

public class SettingsController implements SettingsDataInteractor.DataReceiver {

    private String userId;
    private Context context;
    private SettingsFragment settingsFragment;
    private SettingsDataInteractor settingsDataInteractor;

    public SettingsController(String userId, Context context) {
        this.userId = userId;

        settingsFragment = SettingsFragment.newInstance(userId);
        settingsDataInteractor = new SettingsDataInteractor(userId, context, this);
    }

    public Fragment getFragment() {
        return settingsFragment;
    }
}
