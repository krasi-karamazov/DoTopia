package com.augeo.dotopia.ui.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.augeo.dotopia.R;

/**
 * Created by krasimir.karamazov on 5/21/2014.
 */
public class HistoryFragment extends BaseFragment {

    public static final String TAG = HistoryFragment.class.getSimpleName();

    public static HistoryFragment getInstance(Bundle args) {
        HistoryFragment fragment = new HistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initUI(View rootView) {
        final TextView tvBalance = (TextView)rootView.findViewById(R.id.tv_balance_amount);
        final TextView tvGivenThisYear = (TextView)rootView.findViewById(R.id.tv_given_this_year);
        final TextView tvGivenLastYear = (TextView)rootView.findViewById(R.id.tv_given_last_year);
        final TextView tvTotalGiven = (TextView)rootView.findViewById(R.id.tv_total_give);


    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_history;
    }

    @Override
    protected String getTitle() {
        return getString(R.string.history).toUpperCase();
    }

    @Override
    protected String getFragmentTag() {
        return TAG;
    }
}
