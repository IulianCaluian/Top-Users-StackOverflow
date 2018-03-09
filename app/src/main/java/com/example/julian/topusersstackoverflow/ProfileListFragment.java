package com.example.julian.topusersstackoverflow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Julian on 09.03.2018.
 */

public class ProfileListFragment extends Fragment {
    private RecyclerView mProfileRecyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_list,container,false);
        mProfileRecyclerView = view.findViewById(R.id.profile_list_recycler_view);
        mProfileRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        return view;
    }
}
