package com.augeo.dotopia.ui.fragments;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.augeo.dotopia.R;
import com.augeo.dotopia.models.SingleCauseModel;

/**
 * Created by krasimir.karamazov on 5/20/2014.
 */
public class OrganizationDetailsFragment extends BaseFragment {

    public static final String TAG = OrganizationDetailsFragment.class.getSimpleName();
    public static final String ORG_ARG_KEY = "selected_org";

    public static OrganizationDetailsFragment getInstance(Bundle args) {
        OrganizationDetailsFragment fragment = new OrganizationDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initUI(View rootView) {
        final TextView tvAddress = (TextView)rootView.findViewById(R.id.tv_address);
        final TextView tvCategory = (TextView)rootView.findViewById(R.id.tv_category);
        final TextView tvDescription = (TextView)rootView.findViewById(R.id.tv_description);
        if(getArguments() != null) {
            final SingleCauseModel org = (SingleCauseModel)getArguments().getSerializable(ORG_ARG_KEY);
            if(org != null) {
                tvAddress.setText(org.getAddress());
                tvCategory.setText(org.getCategories());
                tvDescription.setText(Html.fromHtml(org.getDescription()));
                if(getActivity() instanceof ActionBarActivity) {
                    ((ActionBarActivity)getActivity()).getSupportActionBar().setTitle(org.getName());
                }
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_organization_details;
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
