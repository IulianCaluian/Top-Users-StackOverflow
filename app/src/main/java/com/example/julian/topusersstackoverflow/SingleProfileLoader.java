package com.example.julian.topusersstackoverflow;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

/**
 * Created by Julian on 09.03.2018.
 */

public class SingleProfileLoader extends AsyncTaskLoader<Profile> {
    private String mUrl;
    private int mRank;

    public SingleProfileLoader(Context context, String url,int rank) {
        super(context);
        mUrl = url;
        mRank = rank;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
    @Override
    public Profile loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        // Perform the network request, parse the response, and extract profile
        Profile profile = Utils.fetchProfileData(mUrl,mRank);
        return profile;
    }



}
