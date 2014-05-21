package com.augeo.dotopia.ui.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import com.augeo.dotopia.R;
import com.augeo.dotopia.navigation.events.NavigationEvent;
import com.augeo.dotopia.ui.activities.MainActivity;
import com.augeo.dotopia.util.BusProvider;

/**
 * Created by krasimir.karamazov on 5/13/2014.
 */
public class GiveFragment extends BaseFragment {

    public static final String TAG = GiveFragment.class.getSimpleName();
    private EditText mEtSearchTerm;
    public static GiveFragment getInstance(Bundle args) {
        GiveFragment fragment = new GiveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initUI(View rootView) {
        mEtSearchTerm = (EditText)rootView.findViewById(R.id.et_search_term_field);
        mEtSearchTerm.setImeOptions(EditorInfo.IME_ACTION_GO);
        mEtSearchTerm.setOnEditorActionListener(getOnEditorActionListener());
        rootView.findViewById(R.id.but_login).setOnClickListener(getOnClickListener());
    }

    private TextView.OnEditorActionListener getOnEditorActionListener() {
        return new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (keyEvent != null && keyEvent.getAction() != KeyEvent.ACTION_DOWN) {
                    return false;
                } else if (actionId == EditorInfo.IME_ACTION_SEARCH || keyEvent == null || keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {

                    startSearch();
                    return true;
                }
                return false;
            }
        };
    }

    private View.OnClickListener getOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSearch();
            }
        };
    }

    private void startSearch() {
        ((MainActivity)getActivity()).hideSoftKeyboard();
        final String searchTerm = mEtSearchTerm.getText().toString();

        if(!TextUtils.isEmpty(searchTerm)) {
            mEtSearchTerm.clearFocus();
            final Bundle args = new Bundle();
            args.putString(GiveListFragment.SEARCH_TERM_ARG_KEY, searchTerm);
            BusProvider.getInstance().post(new NavigationEvent(GiveListFragment.getInstance(args)));
        }
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
