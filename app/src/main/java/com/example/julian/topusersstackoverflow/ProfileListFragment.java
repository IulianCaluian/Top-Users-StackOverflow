package com.example.julian.topusersstackoverflow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Julian on 09.03.2018.
 */

public class ProfileListFragment extends Fragment {
    private RecyclerView mProfileRecyclerView;
    private ProfileAdapter mProfileAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_list,container,false);
        mProfileRecyclerView = view.findViewById(R.id.profile_list_recycler_view);
        mProfileRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI(){
        ProfileLib profileLib = ProfileLib.get();
        List<Profile> profiles = profileLib.getProfiles();

        mProfileAdapter = new ProfileAdapter(profiles);
        mProfileRecyclerView.setAdapter(mProfileAdapter);
    }

    private class ProfileHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mProfileNameTextView;
        private Profile mProfile;

        public ProfileHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            mProfileNameTextView = itemView.findViewById(R.id.list_item_profile_name);
        }

        public void bindProfile(Profile profile){
            mProfile = profile;
            mProfileNameTextView.setText(profile.getName());
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
}
