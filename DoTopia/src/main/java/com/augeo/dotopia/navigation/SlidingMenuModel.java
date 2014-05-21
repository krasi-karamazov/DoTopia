package com.augeo.dotopia.navigation;

import android.content.Context;
import com.augeo.dotopia.R;
import com.augeo.dotopia.models.SlidingMenuItemModel;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by krasimir.karamazov on 4/30/2014.
 */
public class SlidingMenuModel {

    private Map<SlidingMenuItemModel, List<SlidingMenuItemModel>> listDataChild;
    private Context mApplicationContext;
    public SlidingMenuModel(Context context){
        mApplicationContext = context.getApplicationContext();
        prepareData();
    }

    private void prepareData(){
        listDataChild = new LinkedHashMap<SlidingMenuItemModel, List<SlidingMenuItemModel>>();
        //User menu
        SlidingMenuItemModel userModel = new SlidingMenuItemModel(SlidingMenuItemModel.NO_ICON, null, true);
        List<SlidingMenuItemModel> userMenu = new ArrayList<SlidingMenuItemModel>();

        userMenu.add(new SlidingMenuItemModel(SlidingMenuItemModel.NO_ICON, mApplicationContext.getString(R.string.account), false));
        userMenu.add(new SlidingMenuItemModel(SlidingMenuItemModel.NO_ICON, mApplicationContext.getString(R.string.community), false));
        userMenu.add(new SlidingMenuItemModel(SlidingMenuItemModel.NO_ICON, mApplicationContext.getString(R.string.history), false));
        userMenu.add(new SlidingMenuItemModel(SlidingMenuItemModel.NO_ICON, mApplicationContext.getString(R.string.favorites), false));
        userMenu.add(new SlidingMenuItemModel(SlidingMenuItemModel.NO_ICON, mApplicationContext.getString(R.string.goals), false));
        listDataChild.put(userModel, userMenu);

        SlidingMenuItemModel giveModel = new SlidingMenuItemModel(R.drawable.ic_give, mApplicationContext.getString(R.string.give), false);
        listDataChild.put(giveModel, new ArrayList<SlidingMenuItemModel>());

        SlidingMenuItemModel giftModel = new SlidingMenuItemModel(R.drawable.ic_gift, mApplicationContext.getString(R.string.gift), false);
        listDataChild.put(giftModel, new ArrayList<SlidingMenuItemModel>());

        SlidingMenuItemModel redeemModel = new SlidingMenuItemModel(R.drawable.ic_redeem, mApplicationContext.getString(R.string.redeem), false);
        listDataChild.put(redeemModel, new ArrayList<SlidingMenuItemModel>());

        SlidingMenuItemModel inviteModel = new SlidingMenuItemModel(R.drawable.ic_invite, mApplicationContext.getString(R.string.invite), false);
        listDataChild.put(inviteModel, new ArrayList<SlidingMenuItemModel>());

        SlidingMenuItemModel buyModel = new SlidingMenuItemModel(R.drawable.ic_buy, mApplicationContext.getString(R.string.buy), false);
        listDataChild.put(buyModel, new ArrayList<SlidingMenuItemModel>());

        SlidingMenuItemModel learnModel = new SlidingMenuItemModel(R.drawable.ic_learn, mApplicationContext.getString(R.string.learn), false);
        listDataChild.put(learnModel, new ArrayList<SlidingMenuItemModel>());

        SlidingMenuItemModel settingsModel = new SlidingMenuItemModel(R.drawable.ic_settings, mApplicationContext.getString(R.string.settings), false);
        List<SlidingMenuItemModel> settingsMenu = new ArrayList<SlidingMenuItemModel>();
        settingsMenu.add(new SlidingMenuItemModel(SlidingMenuItemModel.NO_ICON, mApplicationContext.getString(R.string.account_settings), false));
        settingsMenu.add(new SlidingMenuItemModel(SlidingMenuItemModel.NO_ICON, mApplicationContext.getString(R.string.app_settings), false));
        settingsMenu.add(new SlidingMenuItemModel(SlidingMenuItemModel.NO_ICON, mApplicationContext.getString(R.string.about), false));
        settingsMenu.add(new SlidingMenuItemModel(SlidingMenuItemModel.NO_ICON, mApplicationContext.getString(R.string.faq), false));
        settingsMenu.add(new SlidingMenuItemModel(SlidingMenuItemModel.NO_ICON, mApplicationContext.getString(R.string.privacy_policy), false));
        settingsMenu.add(new SlidingMenuItemModel(SlidingMenuItemModel.NO_ICON, mApplicationContext.getString(R.string.terms_of_service), false));
        settingsMenu.add(new SlidingMenuItemModel(SlidingMenuItemModel.NO_ICON, mApplicationContext.getString(R.string.logout), false));
        listDataChild.put(settingsModel, settingsMenu);

        SlidingMenuItemModel homeModel = new SlidingMenuItemModel(R.drawable.ic_home, mApplicationContext.getString(R.string.home), false);
        listDataChild.put(homeModel, new ArrayList<SlidingMenuItemModel>());

    }

    public void setApplicationContext(Context context) {
        mApplicationContext = context.getApplicationContext();
    }

    public Map<SlidingMenuItemModel, List<SlidingMenuItemModel>> getData() {
        return listDataChild;
    }


}
