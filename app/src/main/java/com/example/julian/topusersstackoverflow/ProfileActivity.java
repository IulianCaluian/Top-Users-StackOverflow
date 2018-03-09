package com.example.julian.topusersstackoverflow;

import android.support.v4.app.Fragment;

/**
 * Created by Julian on 09.03.2018.
 */

public class ProfileActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new ProfileFragment();
    }
}
