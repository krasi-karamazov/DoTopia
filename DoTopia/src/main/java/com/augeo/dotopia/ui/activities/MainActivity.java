package com.augeo.dotopia.ui.activities;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.augeo.dotopia.R;
import com.augeo.dotopia.navigation.NavigationDrawerController;
import com.augeo.dotopia.navigation.SlidingMenuController;
import com.augeo.dotopia.navigation.events.NavigationEvent;
import com.augeo.dotopia.ui.fragments.HomeFragment;
import com.augeo.dotopia.util.BusProvider;
import com.squareup.otto.Subscribe;

public class MainActivity extends ActionBarActivity {

    private NavigationDrawerController mController;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mController = NavigationDrawerController.getInstance();
        mController.attach(this);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(getSupportFragmentManager().getBackStackEntryCount() == 0) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fl_fragment_container, HomeFragment.getInstance(null), HomeFragment.TAG);
            ft.commit();
        }
    }

    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mController.getDrawerToggle().syncState();
    }

    /**
     * Shows the soft keyboard
     */
    public void showSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, 0);
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
        if (mController.getDrawerToggle().onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}