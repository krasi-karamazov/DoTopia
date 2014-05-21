package com.augeo.dotopia.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.augeo.dotopia.R;
import com.augeo.dotopia.models.SingleCauseModel;
import com.augeo.dotopia.navigation.events.NavigationEvent;
import com.augeo.dotopia.ui.fragments.BaseFragment;
import com.augeo.dotopia.ui.fragments.GiveAmountFragment;
import com.augeo.dotopia.ui.fragments.OrganizationDetailsFragment;
import com.augeo.dotopia.util.BusProvider;

import java.util.List;

/**
 * Created by krasimir.karamazov on 5/19/2014.
 */
public class CausesAdapter extends ArrayAdapter<SingleCauseModel> {

    public CausesAdapter(Context context, List<SingleCauseModel> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    static class ViewHolder{
        TextView nameLabel;
        TextView addressLabel;
        TextView categoryLabel;
        View infoContainer;
        View giveView;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;
        if(row == null) {
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.cause_list_item, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.nameLabel = (TextView)row.findViewById(R.id.tv_cause_name);
            viewHolder.addressLabel = (TextView)row.findViewById(R.id.tv_address);
            viewHolder.categoryLabel = (TextView)row.findViewById(R.id.tv_category);
            viewHolder.infoContainer = row.findViewById(R.id.ll_info_container);
            viewHolder.giveView = row.findViewById(R.id.tv_give);
            row.setTag(viewHolder);
        }
        holder = (ViewHolder)row.getTag();
        holder.nameLabel.setText(getItem(position).getName());
        holder.addressLabel.setText(getItem(position).getAddress());
        holder.categoryLabel.setText(getItem(position).getCategories());

        holder.infoContainer.setOnClickListener(getOnClickListener(position));
        holder.giveView.setOnClickListener(getOnClickListener(position));
        return row;
    }

    private View.OnClickListener getOnClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SingleCauseModel model = getItem(position);
                Bundle args = new Bundle();
                BaseFragment fragment = null;
                switch(view.getId()) {
                    case R.id.tv_give:
                        args.putSerializable(GiveAmountFragment.CAUSE_ARG_KEY, model);
                        fragment = GiveAmountFragment.getInstance(args);
                    break;
                    case R.id.ll_info_container:
                        args.putSerializable(OrganizationDetailsFragment.ORG_ARG_KEY, model);
                        fragment = OrganizationDetailsFragment.getInstance(args);
                    break;
                }

                if(fragment != null) {
                    fragment.setArguments(args);
                    BusProvider.getInstance().post(new NavigationEvent(fragment));
                }
            }
        };
    }
}
