package com.framgia.mavel;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayTablayout();
    }

    private void displayTablayout() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        createAdapter(fragmentAdapter);
        viewPager.setAdapter(fragmentAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void createAdapter(FragmentAdapter adapter) {
        adapter.addFragment(new CharactersFragment(),
                getString(R.string.text_character_fragment));
        adapter.addFragment(new FavoriteFragment(),
                getString(R.string.text_favorite_fragment));
    }
}
