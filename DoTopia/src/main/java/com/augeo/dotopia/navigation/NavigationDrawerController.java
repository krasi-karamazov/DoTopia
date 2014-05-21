package com.augeo.dotopia.navigation;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import com.augeo.dotopia.R;
import com.augeo.dotopia.adapters.MenuExpandableAdapter;
import com.augeo.dotopia.navigation.events.NavigationEvent;
import com.augeo.dotopia.ui.fragments.GiveFragment;
import com.augeo.dotopia.util.BusProvider;

/**
 * Created by krasimir.karamazov on 5/20/2014.
 */
public class NavigationDrawerController {
    private static NavigationDrawerController sInstance;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ExpandableListView mDrawerListView;
    private SlidingMenuModel mModel;
    private MenuExpandableAdapter listAdapter;

    public static synchronized NavigationDrawerController getInstance() {
        if(sInstance == null) {
            sInstance = new NavigationDrawerController();
        }

        return sInstance;
    }

    public ActionBarDrawerToggle getDrawerToggle(){
        return mDrawerToggle;
    }

    public DrawerLayout getDrawerLayout(){
        return mDrawerLayout;
    }

    public void attach(Context context) {
        ViewGroup view = (ViewGroup)((Activity)context).getWindow().getDecorView();
        View content = view.getChildAt(0);
        mDrawerListView = (ExpandableListView)content.findViewById(R.id.menu_list);
        mDrawerLayout = (DrawerLayout)content.findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, Gravity.LEFT);


        mModel = new SlidingMenuModel(context);
        listAdapter = new MenuExpandableAdapter(context, mModel.getData());
        mDrawerListView.setAdapter(listAdapter);

        mDrawerToggle = getDrawerListener(context);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerListView.setOnChildClickListener(getOnChildClickListener());
        mDrawerListView.setOnGroupClickListener(getOnGroupClickListener());
    }

    public void toggleDrawer(){
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else{
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    private ActionBarDrawerToggle getDrawerListener(Context context) {
        return new ActionBarDrawerToggle((ActionBarActivity)context, mDrawerLayout, R.drawable.ic_drawer, R.string.open_drawer, R.string.close_drawer){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
    }

    private ExpandableListView.OnChildClickListener getOnChildClickListener() {
        return new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                switch(groupPosition){
                    case 0:
                        switch(childPosition){
                            case 0:
                                Log.d("DoTopia", "Account");
                                break;
                            case 1:
                                Log.d("DoTopia", "Community");
                                break;
                            case 2:
                                Log.d("DoTopia", "History");
                                break;
                            case 3:
                                Log.d("DoTopia", "Favorites");
                                break;
                            case 4:
                                Log.d("DoTopia", "Goals");
                                break;
                        }
                        break;
                    case 7:
                        switch(childPosition){
                            case 0:
                                Log.d("DoTopia", "Account Settings");
                                break;
                            case 1:
                                Log.d("DoTopia", "App Settings");
                                break;
                            case 2:
                                Log.d("DoTopia", "About");
                                break;
                            case 3:
                                Log.d("DoTopia", "FAQ");
                                break;
                            case 4:
                                Log.d("DoTopia", "Privacy Policy");
                                break;
                            case 5:
                                Log.d("DoTopia", "Terms Of Service");
                                break;
                            case 6:
                                Log.d("DoTopia", "Logout");
                                break;
                        }
                        break;
                }
                return true;
            }
        };
    }

    private ExpandableListView.OnGroupClickListener getOnGroupClickListener() {
        return new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long l) {
                switch(groupPosition){
                    case 1:
                        BusProvider.getInstance().post(new NavigationEvent(GiveFragment.getInstance(null)));
                        break;
                    case 2:
                        Log.d("DoTopia", "Gift");
                        break;
                    case 3:
                        Log.d("DoTopia", "Redeem");
                        break;
                    case 4:
                        Log.d("DoTopia", "Invite");
                        break;
                    case 5:
                        Log.d("DoTopia", "Buy");
                        break;
                    case 6:
                        Log.d("DoTopia", "Learn");
                        break;
                    case 8:
                        Log.d("DoTopia", "Home");
                        break;
                    default:
                        return false;
                }
                return true;
            }
        };
    }
}
