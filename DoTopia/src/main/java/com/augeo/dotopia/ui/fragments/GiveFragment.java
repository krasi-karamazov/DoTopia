package com.augeo.dotopia.ui.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.augeo.dotopia.R;

/**
 * Created by krasimir.karamazov on 5/13/2014.
 */
public class GiveFragment extends BaseFragment {

    public static final String TAG = GiveFragment.class.getSimpleName();

    public static GiveFragment getInstance(Bundle args) {
        GiveFragment fragment = new GiveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initUI(View rootView) {
        final EditText etSearchTerm = (EditText)rootView.findViewById(R.id.et_organization_name);
        rootView.findViewById(R.id.but_login).setOnClickListener(getOnClickListener(etSearchTerm));
    }

    private View.OnClickListener getOnClickListener(final EditText field) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String searchTerm = field.getText().toString();

                if(!TextUtils.isEmpty(searchTerm)) {

                }
            }
        };
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_give;
    }

    @Override
    protected String getTitle() {
        return getString(R.string.give_upper);
    }

    @Override
    protected String getFragmentTag() {
        return TAG;
    }
}
