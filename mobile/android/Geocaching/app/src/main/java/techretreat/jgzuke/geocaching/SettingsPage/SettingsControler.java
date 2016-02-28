package techretreat.jgzuke.geocaching.SettingsPage;

import android.support.v4.app.Fragment;

public class SettingsControler {

    private String userId;

    public SettingsControler(String userId) {
        this.userId = userId;
    }

    public Fragment createFragment() {
        return SettingsFragment.newInstance(userId);
    }
}
