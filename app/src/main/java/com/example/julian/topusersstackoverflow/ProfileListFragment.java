package com.example.julian.topusersstackoverflow;

import android.content.Loader;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.os.AsyncTask;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Julian on 09.03.2018.
 */

public class ProfileListFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Profile>>{
    private RecyclerView mProfileRecyclerView;
    private ProfileAdapter mProfileAdapter;
    private ProgressBar mProgressBar;
    private static final String USGS_REQUEST_URL ="https://api.stackexchange.com/2.2/users?order=desc&sort=reputation&site=stackoverflow";
    private static final int PROFILE_LOADER_ID = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_list,container,false);
        mProfileRecyclerView = view.findViewById(R.id.profile_list_recycler_view);
        mProgressBar = view.findViewById(R.id.profile_list_progress_bar);
        mProfileRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mProfileAdapter = new ProfileAdapter(null);
        updateUI();

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(PROFILE_LOADER_ID,null,this);

        return view;
    }

    private void updateUI(){
        mProfileRecyclerView.setAdapter(mProfileAdapter);
    }

    @Override
    public android.support.v4.content.Loader<List<Profile>> onCreateLoader(int id, Bundle args) {
        return new ProfileLoader(getContext(),USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<List<Profile>> loader, List<Profile> profiles) {
        mProfileAdapter.clear();
        if (profiles != null && !profiles.isEmpty()) {
            mProfileAdapter.addAll(profiles);
        }
        mProgressBar.setVisibility(View.INVISIBLE);
        updateUI();
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<List<Profile>> loader) {
        mProfileAdapter.clear();
    }

    private class ProfileHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mProfileNameTextView;
        private ImageView mProfileImageView;
        private Profile mProfile;

        public ProfileHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            mProfileNameTextView = itemView.findViewById(R.id.list_item_profile_name);
            mProfileImageView = itemView.findViewById(R.id.list_item_profile_image);
        }

        public void bindProfile(Profile profile){
            mProfile = profile;
            mProfileNameTextView.setText(profile.getName());
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(getContext()).load(mProfile.getImageUrl())
                    .apply(requestOptions).into(mProfileImageView);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(),mProfile.getName(),Toast.LENGTH_SHORT).show();
        }
    }

    private class ProfileAdapter extends RecyclerView.Adapter<ProfileHolder>{
        private List<Profile> mProfiles;

        public ProfileAdapter(List<Profile> profiles) {mProfiles = profiles;}

        public void clear(){mProfiles = null;}

        public void addAll(List<Profile> profiles){
            if(mProfiles!=null && !mProfiles.isEmpty()){
                mProfiles.addAll(profiles);
            } else {
                mProfiles = profiles;
            }
        }

        @Override
        public ProfileHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_profile,parent,false);
            return new ProfileHolder(view);
        }

        @Override
        public void onBindViewHolder(ProfileHolder holder, int position) {
            Profile profile = mProfiles.get(position);
            holder.bindProfile(profile);
        }

        @Override
        public int getItemCount() {
            if(mProfiles!=null){
                return mProfiles.size();
            }else {
                return 0;
            }
        }
    }
}
