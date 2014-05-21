package com.augeo.dotopia.ui.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.augeo.dotopia.R;
import com.augeo.dotopia.models.SingleCauseModel;

/**
 * Created by krasimir.karamazov on 5/20/2014.
 */
public class GiveConfirmationFragment extends BaseFragment {

    public static final String TAG = GiveConfirmationFragment.class.getSimpleName();
    public static final String CAUSE_ARG_KEY = "selected_cause";
    public static final String AMOUNT_ARG_KEY = "amount";

    public static GiveConfirmationFragment getInstance(Bundle args) {
        GiveConfirmationFragment fragment = new GiveConfirmationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initUI(View rootView) {
        if(getArguments() != null) {
            if(getArguments().containsKey(CAUSE_ARG_KEY)) {
                SingleCauseModel model = (SingleCauseModel)getArguments().getSerializable(CAUSE_ARG_KEY);
                if(model != null) {
                    final TextView tvConfirmationSubtitle = (TextView)rootView.findViewById(R.id.tv_confirmation_subtext);
                    tvConfirmationSubtitle.setText(getString(R.string.an_email_will_be_sent) + " " + model.getName() + " " + getString(R.string.within_tf_hours));
                    final TextView tvRecipient = (TextView)rootView.findViewById(R.id.tv_recipient);
                    tvRecipient.setText(model.getName());
                    final TextView tvAmount = (TextView)rootView.findViewById(R.id.tv_amount);
                    int amount = getArguments().getInt(AMOUNT_ARG_KEY);
                    tvAmount.setText("" + amount);

                    final TextView tvQuote = (TextView)rootView.findViewById(R.id.tv_quote);
                    tvQuote.setText(getString(R.string.quote_start) + " " + amount + " DoDollars to " + model.getName() + ". " + getString(R.string.quote_end));
                }
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_give_confirmation;
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
