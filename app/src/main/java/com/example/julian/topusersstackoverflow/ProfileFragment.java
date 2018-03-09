package com.example.julian.topusersstackoverflow;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by Julian on 09.03.2018.
 */

public class ProfileFragment extends Fragment implements LoaderManager.LoaderCallbacks<Profile>{
    private int mRank;
    private Profile mProfile;
    private ImageView mImageView;
    private TextView mNameTextView;
    private TextView mAddressTextView;
    private TextView mGoldMedalsTextView;
    private TextView mSilverMedalsTextView;
    private TextView mBronzeMedalsTextView;
    private static final String USGS_REQUEST_URL ="https://api.stackexchange.com/2.2/users?order=desc&sort=reputation&site=stackoverflow";
    private static final int PROFILE_LOADER_ID = 1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        mImageView = view.findViewById(R.id.profile_image);
        mNameTextView = view.findViewById(R.id.profile_name);
        mAddressTextView = view.findViewById(R.id.profile_address);
        mGoldMedalsTextView = view.findViewById(R.id.gold_medals);
        mSilverMedalsTextView = view.findViewById(R.id.silver_medals);
        mBronzeMedalsTextView = view.findViewById(R.id.bronze_medals);

        mRank = getActivity().getIntent().getIntExtra(ProfileActivity.EXTRA_CRIME_RANK,0);

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(PROFILE_LOADER_ID,null,this);

        return view;
    }

    @Override
    public Loader<Profile> onCreateLoader(int id, Bundle args) {
        return new SingleProfileLoader(getContext(),USGS_REQUEST_URL,mRank);
    }

    @Override
    public void onLoadFinished(Loader<Profile> loader, Profile profile) {
        if(profile != null){
            mProfile = profile;
            Glide.with(getContext()).load(mProfile.getImageUrl()).into(mImageView);
            mNameTextView.setText(mProfile.getName());
            mAddressTextView.setText(mProfile.getLocation());
            mGoldMedalsTextView.setText(Integer.toString(mProfile.getNrMedalsGold()) + " gold medals");
            mSilverMedalsTextView.setText(Integer.toString(mProfile.getNrMedalsSilver()) + " silver medals");
            mBronzeMedalsTextView.setText(Integer.toString(mProfile.getNrMedalsBronze()) + " bronze medals");
        }
    }

    @Override
    public void onLoaderReset(Loader<Profile> loader) {

    }
}
