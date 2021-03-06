package be.pxl.smartict.sparkfun;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FragmentAdapter vpAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
        }

        // init viewpager for weekdays
        vpAdapter = new FragmentAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.vp_fragment_container);
        viewPager.setAdapter(vpAdapter);
    }

    private static class FragmentAdapter extends FragmentStatePagerAdapter {
        private List<Fragment> frags = new ArrayList<Fragment>();

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
            frags.add(new TextFragment());
            frags.add(new ColorFragment());
            frags.add(new GridFragment());
            frags.add(new PaintFragment());
        }

        @Override
        public Fragment getItem(int pos) {
            return frags.get(pos);
        }

        @Override
        public int getCount() {
            return frags.size();
        }

        @Override
        public int getItemPosition(Object object) {
            final int pos = frags.indexOf(object);
            if (pos >= 0) {
                return pos;
            } else {
                return POSITION_NONE;
            }
        }

        @Override
        public CharSequence getPageTitle(int pos) {
            final Fragment frag = frags.get(pos);
            if (frag != null) {
                return frag.getClass().getSimpleName();
            } else {
                return "Nothing";
            }
        }
    }
}
