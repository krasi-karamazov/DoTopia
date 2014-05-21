package com.augeo.dotopia.ui.fragments;

import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.augeo.dotopia.R;
import com.augeo.dotopia.models.SingleCauseModel;
import com.augeo.dotopia.navigation.events.NavigationEvent;
import com.augeo.dotopia.util.BusProvider;

/**
 * Created by krasimir.karamazov on 5/20/2014.
 */
public class GiveAmountFragment extends BaseFragment {

    public static final String TAG = GiveAmountFragment.class.getSimpleName();
    public static final String CAUSE_ARG_KEY = GiveAmountFragment.class.getSimpleName();
    private long mId;
    private SingleCauseModel mModel;
    private TextView mAmountField;

    public static GiveAmountFragment getInstance(Bundle args) {
        GiveAmountFragment fragment = new GiveAmountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initUI(View rootView) {
        if(getArguments() != null) {
            if(getArguments().containsKey(CAUSE_ARG_KEY)) {
                mModel = (SingleCauseModel)getArguments().get(CAUSE_ARG_KEY);
                if(mModel != null) {
                    mAmountField = (TextView)rootView.findViewById(R.id.tv_amount);
                    final TextView tvName =  (TextView)rootView.findViewById(R.id.tv_cause_name);
                    final TextView tvAddress =  (TextView)rootView.findViewById(R.id.tv_address);
                    final TextView tvCategory =  (TextView)rootView.findViewById(R.id.tv_category);
                    final TextView tvDescription =  (TextView)rootView.findViewById(R.id.tv_description);
                    tvName.setText(mModel.getName());
                    tvAddress.setText(mModel.getAddress());
                    tvCategory.setText(mModel.getCategories());
                    tvDescription.setText(Html.fromHtml(mModel.getDescription()));
                    rootView.findViewById(R.id.tv_give).setOnClickListener(getOnClickListener());
                }
            }
        }
    }

    private View.OnClickListener getOnClickListener() {
        return new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(mAmountField.getText())) {
                    Bundle args = new Bundle();
                    args.putSerializable(GiveConfirmationFragment.CAUSE_ARG_KEY, mModel);
                    int amount = Integer.valueOf(mAmountField.getText().toString());
                    args.putInt(GiveConfirmationFragment.AMOUNT_ARG_KEY, amount);

                    BusProvider.getInstance().post(new NavigationEvent(GiveConfirmationFragment.getInstance(args)));
                }else{
                    Toast.makeText(getActivity(), R.string.amount_missing_error_string, Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_give_ammount;
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
