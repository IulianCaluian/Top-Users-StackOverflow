package com.example.julian.topusersstackoverflow;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by Julian on 09.03.2018.
 */

public class ProfileActivity extends SingleFragmentActivity {

    public static final String EXTRA_CRIME_RANK =
            "com.example.julian.topusersstackoverflow.profile_number";

    public static Intent newIntent(Context packageContext, int profileRank) {
        Intent intent = new Intent(packageContext, ProfileActivity.class);
        intent.putExtra(EXTRA_CRIME_RANK, profileRank);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new ProfileFragment();
    }
}
