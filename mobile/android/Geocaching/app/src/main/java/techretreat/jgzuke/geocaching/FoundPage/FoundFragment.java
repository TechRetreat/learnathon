package techretreat.jgzuke.geocaching.FoundPage;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import techretreat.jgzuke.geocaching.R;

public class FoundFragment extends Fragment {

    private static final String KEY_USER_ID = "user_id";

    private String userId;
    private RecyclerView cachesRecycerView;
    private CacheAdapter cachesRecycerViewAdapter;
    private List<Caches.Cache> foundCaches;

    public static FoundFragment newInstance(String userId) {
        Bundle args = new Bundle();
        FoundFragment fragment = new FoundFragment();
        args.putString(KEY_USER_ID, userId);
        fragment.setArguments(args);
        return fragment;
    }

    public FoundFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        userId = getArguments().getString(KEY_USER_ID);
        View rootView = inflater.inflate(R.layout.fragment_found, container, false);

        cachesRecycerView = (RecyclerView) rootView.findViewById(R.id.found_caches_recycler_view);
        setUpRecyclerView();

        return rootView;
    }

    public void setUpRecyclerView() {
        cachesRecycerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        cachesRecycerViewAdapter = new CacheAdapter();
        cachesRecycerView.setAdapter(cachesRecycerViewAdapter);
    }

    public void setFoundCaches(Caches.Cache[] caches) {
        foundCaches = Arrays.asList(caches);
        if(cachesRecycerViewAdapter != null) {
            cachesRecycerViewAdapter.notifyDataSetChanged();
        }
    }

    private class CacheHolder extends RecyclerView.ViewHolder {
        private final TextView difficultyTextView;

        public CacheHolder(View itemView) {
            super(itemView);
            difficultyTextView = (TextView) itemView.findViewById(R.id.found_item_difficulty);
        }

        public void bindCache(Caches.Cache cache) {
            difficultyTextView.setText(Integer.toString(cache.difficulty));
        }
    }

    private class CacheAdapter extends RecyclerView.Adapter<CacheHolder> {
        @Override
        public CacheHolder onCreateViewHolder(ViewGroup parent, int pos) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_found_cache, parent, false);
            return new CacheHolder(view);
        }

        @Override
        public void onBindViewHolder(CacheHolder holder, int pos) {
            Caches.Cache cache = foundCaches.get(pos);
            holder.bindCache(cache);
        }

        @Override
        public int getItemCount() {
            if(foundCaches == null) {
                return 0;
            }
            return foundCaches.size();
        }
    }
}
