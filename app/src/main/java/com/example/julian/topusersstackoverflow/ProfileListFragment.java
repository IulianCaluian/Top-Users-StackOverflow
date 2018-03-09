package com.example.julian.topusersstackoverflow;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.AsyncTask;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Julian on 09.03.2018.
 */

public class ProfileListFragment extends Fragment{
    private RecyclerView mProfileRecyclerView;
    private ProfileAdapter mProfileAdapter;
    private static final String USGS_REQUEST_URL ="https://api.stackexchange.com/2.2/users?order=desc&sort=reputation&site=stackoverflow";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_list,container,false);
        mProfileRecyclerView = view.findViewById(R.id.profile_list_recycler_view);
        mProfileRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        MyAsyncTask task = new MyAsyncTask();
        task.execute(USGS_REQUEST_URL);

        return view;
    }

    private void updateUI(){
        ProfileLib profileLib = ProfileLib.get();
        List<Profile> profiles = profileLib.getProfiles();

        mProfileAdapter = new ProfileAdapter(profiles);
        mProfileRecyclerView.setAdapter(mProfileAdapter);
    }

    private void updateUI(List<Profile> profiles){
        mProfileAdapter = new ProfileAdapter(profiles);
        mProfileRecyclerView.setAdapter(mProfileAdapter);
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
            new DownloadImageTask(mProfileImageView).execute(mProfile.getImageUrl());
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(),mProfile.getName(),Toast.LENGTH_SHORT).show();
        }
    }

    private class ProfileAdapter extends RecyclerView.Adapter<ProfileHolder>{
        private List<Profile> mProfiles;

        public ProfileAdapter(List<Profile> profiles) {mProfiles = profiles;}

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
            return mProfiles.size();
        }
    }

    class MyAsyncTask extends AsyncTask<String,Void,List<Profile>>{
        @Override
        protected List<Profile> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<Profile> result = Utils.fetchProfilesData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(List<Profile> profiles) {
            if(profiles == null) {
                return;
            }
            updateUI(profiles);
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
