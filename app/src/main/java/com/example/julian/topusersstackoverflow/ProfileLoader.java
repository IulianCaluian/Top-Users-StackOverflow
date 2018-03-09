package com.example.julian.topusersstackoverflow;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

/**
 * Created by Julian on 09.03.2018.
 */

public class ProfileLoader extends AsyncTaskLoader<List<Profile>> {
    private String mUrl;

    public ProfileLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
    @Override
    public List<Profile> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<Profile> profiles = Utils.fetchProfilesData(mUrl);
        return profiles;
    }



}
