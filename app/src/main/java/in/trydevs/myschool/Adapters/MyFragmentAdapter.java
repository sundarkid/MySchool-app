package in.trydevs.myschool.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import in.trydevs.myschool.Fragments.FragmentChatRoom;
import in.trydevs.myschool.Fragments.FragmentClub;
import in.trydevs.myschool.Fragments.FragmentGlobalPost;
import in.trydevs.myschool.Fragments.FragmentImages;
import in.trydevs.myschool.R;
import in.trydevs.myschool.extras.MyApplication;


/**
 * Created by Sundareswaran on 25-08-2015.
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {
    String tabNames[];

    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);
        tabNames = MyApplication.getAppContext().getResources().getStringArray(R.array.tabs_main_activity_check);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabNames[position];
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentGlobalPost();
                break;
           /* case 1:
                fragment = new FragmentClub();
                break;
                */
            case 1:
                fragment = new FragmentImages();
                break;
            case 2:
                fragment = new FragmentChatRoom();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return tabNames.length;
    }
}
