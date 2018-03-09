package com.example.julian.topusersstackoverflow;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Julian on 09.03.2018.
 */

public class ProfileLib {
    private static ProfileLib sProfileLib;
    private List<Profile> mProfiles;

    public static ProfileLib get(){
        if(sProfileLib == null){
            sProfileLib = new ProfileLib();
        }
        return sProfileLib;
    }

    private ProfileLib(){
        mProfiles = new ArrayList<>();
        for(int i=0; i<10; i++) {
            Profile profile = new Profile();
            profile.setName("Profile placeholder "+ i);
            mProfiles.add(profile);
        }
    }

    public List<Profile> getProfiles() {
        return mProfiles;
    }

}
