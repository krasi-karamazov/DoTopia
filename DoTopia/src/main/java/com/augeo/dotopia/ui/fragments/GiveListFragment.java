package com.augeo.dotopia.ui.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.augeo.dotopia.R;
import com.augeo.dotopia.adapters.CausesAdapter;
import com.augeo.dotopia.models.CauseModel;
import com.augeo.dotopia.models.SingleCauseModel;
import com.augeo.dotopia.navigation.events.NavigationEvent;
import com.augeo.dotopia.networking.RestServiceCreator;
import com.augeo.dotopia.ui.fragments.dialogs.BaseDialog;
import com.augeo.dotopia.ui.fragments.dialogs.ProgressDialog;
import com.augeo.dotopia.util.BusProvider;
import com.augeo.dotopia.util.Constants;
import com.augeo.dotopia.util.DataHolder;
import com.augeo.dotopia.util.DoTopiaLog;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by krasimir.karamazov on 5/19/2014.
 */
public class GiveListFragment extends BaseFragment {

    public static final String TAG = GiveListFragment.class.getSimpleName();
    public static final String SEARCH_TERM_ARG_KEY = "search_term";
    private List<SingleCauseModel> mModels = new ArrayList<SingleCauseModel>();
    private CausesAdapter mAdapter;
    public static GiveListFragment getInstance(Bundle args) {
        GiveListFragment fragment = new GiveListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initUI(View rootView) {
        final ListView lvCauses = (ListView)rootView.findViewById(R.id.lv_causes);
        lvCauses.setOnItemClickListener(getOnItemClickListener());
        mAdapter  = new CausesAdapter(getActivity(), mModels);
        lvCauses.setAdapter(mAdapter);
        if(getArguments() != null && mModels.isEmpty()) {
            if(getArguments().containsKey(SEARCH_TERM_ARG_KEY)) {
                String searchTermString = getArguments().getString(SEARCH_TERM_ARG_KEY);
                showProgressDialog();
                RestServiceCreator.createDataServiceService().getCauses(Constants.CONTENT_TYPE, "Bearer " + DataHolder.getInstance().getAuthToken().getAccessToken(), searchTermString, getNonProfitOrgsCallbacks());
            }
        }
    }

    private AdapterView.OnItemClickListener getOnItemClickListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        };
    }

    private void showProgressDialog() {
        Bundle args = new Bundle();
        args.putString(BaseDialog.MESSAGE_ARG_KEY, getString(R.string.please_wait));
        BaseDialog progressDialog = ProgressDialog.getInstance(args);
        progressDialog.show(getActivity().getSupportFragmentManager(), ProgressDialog.TAG);
    }

    private void hideProgressDialog() {
        BaseDialog dialog = (ProgressDialog)getActivity().getSupportFragmentManager().findFragmentByTag(ProgressDialog.TAG);
        if(dialog != null) {
            DoTopiaLog.d("Dialog dismissed");
            dialog.dismiss();
        }
    }

    private Callback<CauseModel> getNonProfitOrgsCallbacks() {
        return new Callback<CauseModel>() {
            @Override
            public void success(CauseModel result, Response response) {
                if(result.getStatus().equals("Failed")){
                    Toast.makeText(getActivity(), result.getError(), Toast.LENGTH_SHORT).show();
                }else{
                    if(mModels.size() > 0){
                        mModels.clear();
                    }
                    mModels.addAll(result.getItems());
                    mAdapter.notifyDataSetChanged();
                    hideProgressDialog();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_give_list;
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
