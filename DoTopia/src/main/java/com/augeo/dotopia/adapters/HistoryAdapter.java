package com.augeo.dotopia.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.augeo.dotopia.R;
import com.augeo.dotopia.models.HistoryModel;
import com.augeo.dotopia.util.DoTopiaLog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by krasimir.karamazov on 5/21/2014.
 */
public class HistoryAdapter extends ArrayAdapter<HistoryModel> {

    private SimpleDateFormat formatter = new SimpleDateFormat();
    public HistoryAdapter(Context context, List<HistoryModel> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    static class ViewHolder{
        TextView nameLabel;
        TextView dateLabel;
        TextView amount;
        TextView pending;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;
        if(row == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.layout_history_list_item, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.nameLabel = (TextView)row.findViewById(R.id.tv_name);
            viewHolder.dateLabel = (TextView)row.findViewById(R.id.tv_date);
            viewHolder.amount = (TextView)row.findViewById(R.id.tv_amount);
            viewHolder.pending = (TextView)row.findViewById(R.id.tv_pending);
            row.setTag(viewHolder);
        }

        holder = (ViewHolder)row.getTag();
        holder.nameLabel.setText(getItem(position).getName());
        String date = getItem(position).getDate();
        formatter.applyPattern("yyyy-MM-DD'T'hh:mm:ss");
        String dateString = "";
        try{
            Date d = formatter.parse(date);
            formatter.applyPattern("MMM DD, yyyy");
            dateString = formatter.format(d);

        }catch(Exception e) {
            DoTopiaLog.d("Cannot parse date");
        }
        if(getItem(position).getIsPending()) {
            holder.pending.setText(R.string.pending);
        }
        holder.dateLabel.setText(dateString);
        holder.amount.setText(getItem(position).getAmount() + "");

        return row;
    }
}
