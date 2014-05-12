package com.augeo.dotopia.navigation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import com.augeo.dotopia.R;
import com.augeo.dotopia.models.SlidingMenuItemModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by krasimir.karamazov on 4/30/2014.
 */
public class SlidingMenuModel {

    private static SlidingMenuModel sInstance;
    private Map<SlidingMenuItemModel, List<SlidingMenuItemModel>> listDataChild;
    private Context mApplicationContext;
    public SlidingMenuModel(Context context){
        mApplicationContext = context.getApplicationContext();
        prepareData();
    }

    private void prepareData(){
        listDataChild = new LinkedHashMap<SlidingMenuItemModel, List<SlidingMenuItemModel>>();
        Bitmap bmp = ((BitmapDrawable)mApplicationContext.getResources().getDrawable(R.drawable.ic_launcher)).getBitmap();
        Bitmap pic = ((BitmapDrawable)mApplicationContext.getResources().getDrawable(R.drawable.pic)).getBitmap();
        //User menu
        SlidingMenuItemModel userModel = new SlidingMenuItemModel(pic, null, true);
        List<SlidingMenuItemModel> userMenu = new ArrayList<SlidingMenuItemModel>();

        userMenu.add(new SlidingMenuItemModel(null, mApplicationContext.getString(R.string.account), false));
        userMenu.add(new SlidingMenuItemModel(null, mApplicationContext.getString(R.string.community), false));
        userMenu.add(new SlidingMenuItemModel(null, mApplicationContext.getString(R.string.history), false));
        userMenu.add(new SlidingMenuItemModel(null, mApplicationContext.getString(R.string.favorites), false));
        userMenu.add(new SlidingMenuItemModel(null, mApplicationContext.getString(R.string.goals), false));
        listDataChild.put(userModel, userMenu);

        SlidingMenuItemModel giveModel = new SlidingMenuItemModel(((BitmapDrawable)mApplicationContext.getResources().getDrawable(R.drawable.ic_give)).getBitmap(), mApplicationContext.getString(R.string.give), false);
        listDataChild.put(giveModel, new ArrayList<SlidingMenuItemModel>());

        SlidingMenuItemModel giftModel = new SlidingMenuItemModel(((BitmapDrawable)mApplicationContext.getResources().getDrawable(R.drawable.ic_gift)).getBitmap(), mApplicationContext.getString(R.string.gift), false);
        listDataChild.put(giftModel, new ArrayList<SlidingMenuItemModel>());

        SlidingMenuItemModel redeemModel = new SlidingMenuItemModel(((BitmapDrawable)mApplicationContext.getResources().getDrawable(R.drawable.ic_redeem)).getBitmap(), mApplicationContext.getString(R.string.redeem), false);
        listDataChild.put(redeemModel, new ArrayList<SlidingMenuItemModel>());

        SlidingMenuItemModel inviteModel = new SlidingMenuItemModel(((BitmapDrawable)mApplicationContext.getResources().getDrawable(R.drawable.ic_invite)).getBitmap(), mApplicationContext.getString(R.string.invite), false);
        listDataChild.put(inviteModel, new ArrayList<SlidingMenuItemModel>());

        SlidingMenuItemModel buyModel = new SlidingMenuItemModel(((BitmapDrawable)mApplicationContext.getResources().getDrawable(R.drawable.ic_buy)).getBitmap(), mApplicationContext.getString(R.string.buy), false);
        listDataChild.put(buyModel, new ArrayList<SlidingMenuItemModel>());

        SlidingMenuItemModel learnModel = new SlidingMenuItemModel(((BitmapDrawable)mApplicationContext.getResources().getDrawable(R.drawable.ic_learn)).getBitmap(), mApplicationContext.getString(R.string.learn), false);
        listDataChild.put(learnModel, new ArrayList<SlidingMenuItemModel>());

        SlidingMenuItemModel settingsModel = new SlidingMenuItemModel(((BitmapDrawable)mApplicationContext.getResources().getDrawable(R.drawable.ic_settings)).getBitmap(), mApplicationContext.getString(R.string.settings), false);
        List<SlidingMenuItemModel> settingsMenu = new ArrayList<SlidingMenuItemModel>();
        settingsMenu.add(new SlidingMenuItemModel(null, mApplicationContext.getString(R.string.account_settings), false));
        settingsMenu.add(new SlidingMenuItemModel(null, mApplicationContext.getString(R.string.app_settings), false));
        settingsMenu.add(new SlidingMenuItemModel(null, mApplicationContext.getString(R.string.about), false));
        settingsMenu.add(new SlidingMenuItemModel(null, mApplicationContext.getString(R.string.faq), false));
        settingsMenu.add(new SlidingMenuItemModel(null, mApplicationContext.getString(R.string.privacy_policy), false));
        settingsMenu.add(new SlidingMenuItemModel(null, mApplicationContext.getString(R.string.terms_of_service), false));
        settingsMenu.add(new SlidingMenuItemModel(null, mApplicationContext.getString(R.string.logout), false));
        listDataChild.put(settingsModel, settingsMenu);

        SlidingMenuItemModel homeModel = new SlidingMenuItemModel(((BitmapDrawable)mApplicationContext.getResources().getDrawable(R.drawable.ic_home)).getBitmap(), mApplicationContext.getString(R.string.home), false);
        listDataChild.put(homeModel, new ArrayList<SlidingMenuItemModel>());

    }

    public void setApplicationContext(Context context) {
        mApplicationContext = context.getApplicationContext();
    }

    public Map<SlidingMenuItemModel, List<SlidingMenuItemModel>> getData() {
        return listDataChild;
    }


}
