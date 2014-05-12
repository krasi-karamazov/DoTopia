package com.augeo.dotopia.ui.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import com.augeo.dotopia.R;
import com.augeo.dotopia.navigation.SlidingMenuController;

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