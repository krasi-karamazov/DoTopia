package com.augeo.dotopia.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import com.augeo.dotopia.R;
import com.augeo.dotopia.navigation.events.NavigationEvent;
import com.augeo.dotopia.ui.fragments.LoginFragment;
import com.augeo.dotopia.util.BusProvider;
import com.squareup.otto.Subscribe;

/**
 * Created by krasimir.karamazov on 4/30/2014.
 */
public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        onNavigationEvent(new NavigationEvent(LoginFragment.getInstance(null)));
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
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.container, event.getFragment(), event.getFragment().getBackstackTag());
        transaction.addToBackStack(event.getFragment().getBackstackTag());
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 1){
            super.onBackPressed();
        }else{
            finish();
        }

    }
}
