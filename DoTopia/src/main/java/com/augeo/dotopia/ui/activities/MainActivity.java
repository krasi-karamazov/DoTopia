package com.augeo.dotopia.ui.activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.augeo.dotopia.R;
import com.augeo.dotopia.navigation.SlidingMenuController;
import com.augeo.dotopia.navigation.events.NavigationEvent;
import com.augeo.dotopia.ui.fragments.HomeFragment;
import com.augeo.dotopia.util.BusProvider;
import com.squareup.otto.Subscribe;

public class MainActivity extends ActionBarActivity {

    private SlidingMenuController mController;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mController = SlidingMenuController.getInstance();
        mController.attach(this);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(getSupportFragmentManager().getBackStackEntryCount() == 0) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fl_fragment_container, HomeFragment.getInstance(null), HomeFragment.TAG);
            ft.commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    @Subscribe
    public void onNavigationEvent(NavigationEvent event) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment_container, event.getFragment(), event.getFragment().getBackstackTag());
        transaction.addToBackStack(event.getFragment().getBackstackTag());
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                mController.toggleMenu();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}