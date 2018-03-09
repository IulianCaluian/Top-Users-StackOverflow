package com.example.julian.topusersstackoverflow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by Julian on 09.03.2018.
 */

public class ProfileListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new ProfileListFragment();
    }
}
