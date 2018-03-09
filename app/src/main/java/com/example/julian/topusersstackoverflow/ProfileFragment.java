package com.example.julian.topusersstackoverflow;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Julian on 09.03.2018.
 */

public class ProfileFragment extends Fragment {
    private Profile mProfile;
    private ImageView mImageView;
    private TextView mNameTextView;
    private TextView mAddressTextView;
    private TextView mGoldMedalsTextView;
    private TextView mSilverMedalsTextView;
    private TextView mBronzeMedalsTextView;


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
        return view;
    }
}
