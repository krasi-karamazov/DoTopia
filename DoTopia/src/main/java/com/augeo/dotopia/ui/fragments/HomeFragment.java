package com.augeo.dotopia.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.augeo.dotopia.R;
import com.augeo.dotopia.navigation.events.NavigationEvent;
import com.augeo.dotopia.ui.RoundedTransformation;
import com.augeo.dotopia.util.BusProvider;
import com.augeo.dotopia.util.Constants;
import com.squareup.picasso.Picasso;

/**
 * Created by krasimir.karamazov on 5/13/2014.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {
    public static final String TAG = HomeFragment.class.getSimpleName();

    public static HomeFragment getInstance(Bundle args) {
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initUI(View rootView) {
        final TextView tvContactUsUs = (TextView)rootView.findViewById(R.id.tv_contact_us);
        tvContactUsUs.setOnClickListener(this);
        final View giveContainer = rootView.findViewById(R.id.give_container);
        giveContainer.setOnClickListener(this);
        final ImageView imageView = (ImageView)rootView.findViewById(R.id.iv_account_icon);
        Picasso.with(getActivity())
                .load(R.drawable.pic)
                .transform(new RoundedTransformation((int) getActivity().getResources().getDimension(R.dimen.menu_icon_size)))
                .into(imageView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected String getTitle() {
        return "";
    }

    @Override
    protected String getFragmentTag() {
        return TAG;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_contact_us:
                final Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{Constants.DOTOPIA_CONTACT_EMAIL});
                startActivity(Intent.createChooser(intent, "Send"));
                break;
            case R.id.give_container:
                BusProvider.getInstance().post(new NavigationEvent(GiveFragment.getInstance(null)));
                break;
        }
    }
}
