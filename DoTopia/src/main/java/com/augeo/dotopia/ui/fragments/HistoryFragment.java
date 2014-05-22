package com.augeo.dotopia.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.augeo.dotopia.R;
import com.augeo.dotopia.adapters.HistoryAdapter;
import com.augeo.dotopia.models.HistoryModel;
import com.augeo.dotopia.networking.RestServiceCreator;
import com.augeo.dotopia.util.Constants;
import com.augeo.dotopia.util.DataHolder;
import com.augeo.dotopia.util.DoTopiaLog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by krasimir.karamazov on 5/21/2014.
 */
public class HistoryFragment extends BaseFragment {

    public static final String TAG = HistoryFragment.class.getSimpleName();
    private SearchView mSearchView;
    private List<HistoryModel> historyModels = new ArrayList<HistoryModel>();
    private HistoryAdapter mAdapter;

    public static HistoryFragment getInstance(Bundle args) {
        HistoryFragment fragment = new HistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchView.setOnQueryTextListener(getOnQueryTextListener());
    }

    private SearchView.OnQueryTextListener getOnQueryTextListener() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        };
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_search:
                mSearchView.setIconified(false);
                return true;
        }

        return false;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        setHasOptionsMenu(true);
    }

    @Override
    protected void initUI(View rootView) {
        final TextView tvBalance = (TextView)rootView.findViewById(R.id.tv_balance_amount);
        final TextView tvGivenThisYear = (TextView)rootView.findViewById(R.id.tv_given_this_year);
        final TextView tvGivenLastYear = (TextView)rootView.findViewById(R.id.tv_given_last_year);
        final TextView tvTotalGiven = (TextView)rootView.findViewById(R.id.tv_total_give);
        final ListView historyListView = (ListView)rootView.findViewById(R.id.lv_history);
        mAdapter = new HistoryAdapter(getActivity(), historyModels);
        historyListView.setAdapter(mAdapter);
        RestServiceCreator.createDataServiceService().getHistory(Constants.CONTENT_TYPE, "Bearer " + DataHolder.getInstance().getAuthToken().getAccessToken(), new Callback<List<HistoryModel>>() {
            @Override
            public void success(List<HistoryModel> historyModel, Response response) {
                if(historyModels.size() > 0) {
                    historyModels.clear();
                }
                sortModels(historyModel);
                historyModels.addAll(historyModel);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
                DoTopiaLog.d("kdfgjdfhgjkdf");
            }
        });

    }

    private void sortModels(List<HistoryModel> unsorted) {

        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-DD'T'hh:mm:ss");

        Collections.sort(unsorted, new Comparator<HistoryModel>() {
            @Override
            public int compare(HistoryModel historyModel, HistoryModel historyModel2) {
                try{
                    Long model1Date = formatter.parse(historyModel.getDate()).getTime();
                    Long model2Date = formatter.parse(historyModel2.getDate()).getTime();
                    return model2Date.compareTo(model1Date);
                }catch(Exception e) {
                    return 0;
                }
            }
        });
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
