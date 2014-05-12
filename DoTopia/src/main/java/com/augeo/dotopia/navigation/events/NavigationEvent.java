package com.augeo.dotopia.navigation.events;

import com.augeo.dotopia.ui.fragments.BaseFragment;

/**
 * Created by krasimir.karamazov on 4/30/2014.
 */
public class NavigationEvent {

    private BaseFragment mFragment;

    public NavigationEvent(BaseFragment fragment) {
        mFragment = fragment;
    }

    public BaseFragment getFragment() {
        return mFragment;
    }
}
