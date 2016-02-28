package techretreat.jgzuke.geocaching.FoundPage;

import android.content.Context;
import android.support.v4.app.Fragment;

public class FoundController implements FoundDataInteractor.DataReceiver {

    private String userId;
    private Context context;
    private FoundFragment foundFragment;
    private FoundDataInteractor foundDataInteractor;

    public FoundController(String userId, Context context) {
        this.userId = userId;

        foundFragment = FoundFragment.newInstance(userId);
        foundDataInteractor = new FoundDataInteractor(userId, context, this);

        foundDataInteractor.getFoundCaches();
    }

    public Fragment getFragment() {
        return foundFragment;
    }

    @Override
    public void getFoundCaches(FoundCaches.Cache[] caches) {
        foundFragment.setFoundCaches(caches);
    }
}
