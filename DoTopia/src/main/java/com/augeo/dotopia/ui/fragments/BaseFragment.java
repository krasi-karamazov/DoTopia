package com.augeo.dotopia.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.augeo.dotopia.R;

/**
 * Created by krasimir.karamazov on 4/30/2014.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        if(getActivity() != null){
            if(getActivity() instanceof ActionBarActivity){
                ((ActionBarActivity)getActivity()).getSupportActionBar().setTitle(getTitle());
            }
        }
        initUI(rootView);
        return rootView;
    }

    public final String getBackstackTag() {
        return getFragmentTag();
    }

    protected abstract void initUI(View rootView);
    protected abstract int getLayoutId();
    protected abstract String getTitle();
    protected abstract String getFragmentTag();
}
