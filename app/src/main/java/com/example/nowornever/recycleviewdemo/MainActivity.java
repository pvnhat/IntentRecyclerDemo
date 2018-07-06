package com.example.nowornever.recycleviewdemo;

import android.app.FragmentManager;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ViewPager viewpg_menu ;
    TabLayout tablayout_menu;
    private NetworkChangeReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewpg_menu = findViewById(R.id.viewpg_menu);
        tablayout_menu = findViewById(R.id.tablayout_menu);

        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        PagerAdapter adapter = new PagerAdapter(manager);
        viewpg_menu.setAdapter(adapter);
        tablayout_menu.setupWithViewPager(viewpg_menu);
        viewpg_menu.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout_menu));
        tablayout_menu.setTabsFromPagerAdapter(adapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        initBroadcast();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
    }

    private void initBroadcast() {
        receiver = new NetworkChangeReceiver();
        IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(receiver, filter);
    }
}
