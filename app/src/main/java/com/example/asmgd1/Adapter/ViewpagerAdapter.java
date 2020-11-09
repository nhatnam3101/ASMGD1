package com.example.asmgd1.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.asmgd1.CourseFragment;
import com.example.asmgd1.MapsFragment;
import com.example.asmgd1.NewsFragment;
import com.example.asmgd1.SocialFragment;

public class ViewpagerAdapter extends FragmentStatePagerAdapter {

    public ViewpagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                CourseFragment f2 = new CourseFragment();
                return f2;
            case 1:
                NewsFragment f4 = new NewsFragment();
                return f4;
            case 2:
                SocialFragment f5 = new SocialFragment();
                return f5;
            case 3:
                MapsFragment f3 = new MapsFragment();
                return f3;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
