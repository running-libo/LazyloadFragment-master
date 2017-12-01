package com.example.lazyloadfragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private String[] topics = new String[]{"推荐","热点","北京","视频","社会","图片"};
    private ViewPager viewPager;
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager.setOffscreenPageLimit(3);

        for(int i=0;i<topics.length;i++){
            tabLayout.addTab(tabLayout.newTab());
            fragments.add(new PageFragment());
        }
        viewPager.setAdapter(new FmPagerAdapter(fragments,getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        for (int j = 0; j < topics.length; j++) {
            tabLayout.getTabAt(j).setText(topics[j]);
        }
    }
}
