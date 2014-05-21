package com.augeo.dotopia.ui.activities;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import com.augeo.dotopia.R;
import com.augeo.dotopia.models.DeviceClientObject;
import com.augeo.dotopia.navigation.events.NavigationEvent;
import com.augeo.dotopia.models.AuthenticationToken;
import com.augeo.dotopia.networking.RestServiceCreator;
import com.augeo.dotopia.ui.fragments.LoginFragment;
import com.augeo.dotopia.ui.fragments.ProgressFragment;
import com.augeo.dotopia.util.BusProvider;
import com.augeo.dotopia.util.Constants;
import com.augeo.dotopia.util.DoTopiaLog;
import com.squareup.otto.Subscribe;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by krasimir.karamazov on 4/30/2014.
 */
public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final SharedPreferences prefs = getSharedPreferences(Constants.SHARED_PREFS_NAME, MODE_PRIVATE);


        if(!prefs.contains(DeviceClientObject.CLIENT_ID_PREFS_KEY)){
            DoTopiaLog.d("need to register");
            onNavigationEvent(new NavigationEvent(ProgressFragment.getInstance(null)));
            getSupportActionBar().hide();
            registerDevice(prefs);
        }else{
            DoTopiaLog.d("Already registered - login");
            Bundle args = null;
            if(prefs.contains(Constants.SHARED_PREFS_REGISTERED_MAIL_KEY)){
                args = new Bundle();
                args.putString(Constants.SHARED_PREFS_REGISTERED_MAIL_KEY, prefs.getString(Constants.SHARED_PREFS_REGISTERED_MAIL_KEY, ""));
            }
            onNavigationEvent(new NavigationEvent(LoginFragment.getInstance(args)));
        }
    }

    private void registerDevice(final SharedPreferences prefs) {
        String deviceID = Settings.Secure.getString(this.getContentResolver(),Settings.Secure.ANDROID_ID);
        String deviceName = Build.MANUFACTURER + " " + Build.MODEL;
        RestServiceCreator.createLoginRegisterService().registerDevice(deviceID, deviceName, "android", new Callback<DeviceClientObject>() {
            @Override
            public void success(DeviceClientObject deviceClientObject, Response response) {
                DoTopiaLog.d("Device registered successfully");
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(DeviceClientObject.CLIENT_ID_PREFS_KEY, deviceClientObject.getClientId());
                editor.putString(DeviceClientObject.CLIENT_SECRET_PREFS_KEY, deviceClientObject.getClientSecret());
                editor.commit();
                getSupportActionBar().show();
                onNavigationEvent(new NavigationEvent(LoginFragment.getInstance(null)));
            }

            @Override
            public void failure(RetrofitError error) {
                DoTopiaLog.d(error.getMessage());
            }
        });
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
