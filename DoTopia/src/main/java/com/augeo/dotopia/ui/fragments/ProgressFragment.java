package com.augeo.dotopia.ui.fragments;

import android.os.Bundle;
import android.view.View;

import com.augeo.dotopia.R;

/**
 * Created by krasimir.karamazov on 5/13/2014.
 */
public class ProgressFragment extends BaseFragment {
    public static final String TAG = ProgressFragment.class.getSimpleName();
    public static ProgressFragment getInstance(Bundle args) {
        ProgressFragment fragment = new ProgressFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initUI(View rootView) {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_progress;
    }

    @Override
    protected String getTitle() {
        return "";
    }

    @Override
    protected String getFragmentTag() {
        return null;
    }
}
