package com.augeo.dotopia.navigation;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import com.augeo.dotopia.R;
import com.augeo.dotopia.adapters.MenuExpandableAdapter;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by krasimir.karamazov on 4/30/2014.
 */
public class SlidingMenuController {
    private static SlidingMenuController sInstance;
    private SlidingMenuModel mModel;
    private MenuExpandableAdapter listAdapter;
    private ExpandableListView expListView;
    private SlidingMenu mMenu;


    private SlidingMenuController() {

    }

    public void attach(Activity activity) {
        mModel = new SlidingMenuModel(activity);
        LayoutInflater inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.layout_menu_list, null, false);
        expListView = (ExpandableListView)v.findViewById(R.id.menu_list);
        listAdapter = new MenuExpandableAdapter(activity, mModel.getData());
        expListView.setAdapter(listAdapter);

        mMenu = new SlidingMenu(activity);
        mMenu.setMode(SlidingMenu.LEFT);
        mMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        mMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        mMenu.setShadowWidthRes(R.dimen.shadow_width);
        mMenu.setFadeDegree(0.35f);
        mMenu.setShadowDrawable(R.drawable.shadow);
        mMenu.attachToActivity(activity, SlidingMenu.SLIDING_WINDOW);
        mMenu.setMenu(v);
        expListView.setOnChildClickListener(getOnChildClickListener());
        expListView.setOnGroupClickListener(getOnGroupClickListener());
    }

    public void toggleMenu() {
        mMenu.toggle(true);
    }

    public static SlidingMenuController getInstance() {
        if(sInstance == null) {
            sInstance = new SlidingMenuController();
        }

        return sInstance;
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
                        Log.d("DoTopia", "Give");
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
