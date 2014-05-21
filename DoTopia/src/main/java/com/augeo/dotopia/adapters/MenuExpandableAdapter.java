package com.augeo.dotopia.adapters;

/**
 * Created by krasimir.karamazov on 4/29/2014.
 */

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.augeo.dotopia.R;
import com.augeo.dotopia.models.SlidingMenuItemModel;
import com.augeo.dotopia.ui.RoundedTransformation;
import com.augeo.dotopia.ui.views.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MenuExpandableAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private Map<SlidingMenuItemModel, List<SlidingMenuItemModel>> mData;
    private List<SlidingMenuItemModel> mHeaders;
    public MenuExpandableAdapter(Context context, Map<SlidingMenuItemModel, List<SlidingMenuItemModel>> data){
        mContext = context;
        mData = data;
        mHeaders = new ArrayList<SlidingMenuItemModel>();
        mHeaders.addAll(data.keySet());
    }

    static class ModelHolder{
        ImageView imgView;
        TextView txtView;
    }

    @Override
    public int getGroupCount() {
        return mData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mData.get(mHeaders.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mHeaders.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mData.get(mHeaders.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ModelHolder holder;
        View groupRow = convertView;
        if (groupRow == null) {
            groupRow = LayoutInflater.from(mContext).inflate(R.layout.layout_list_item_group, parent, false);
            holder = new ModelHolder();
            holder.imgView = (ImageView)groupRow.findViewById(R.id.iv_item_icon);
            holder.txtView = (TextView)groupRow.findViewById(R.id.lblListHeader);
            groupRow.setTag(holder);
        }
        holder = (ModelHolder)groupRow.getTag();
        SlidingMenuItemModel model = (SlidingMenuItemModel)getGroup(groupPosition);

        if(model.getIsUser()) {
            Picasso.with(mContext)
                    .load(R.drawable.pic)
                    .transform(new RoundedTransformation((int)mContext.getResources().getDimension(R.dimen.menu_icon_size)))
                    .into(holder.imgView);
        }else{
            if(model.getIcon() != SlidingMenuItemModel.NO_ICON){
                Picasso.with(mContext)
                        .load(model.getIcon())
                        .resize((int)mContext.getResources().getDimension(R.dimen.menu_icon_size), (int)mContext.getResources().getDimension(R.dimen.menu_icon_size))
                        .into(holder.imgView);
            }
        }
        TextView lblListHeader = holder.txtView;
        String modelLabel = model.getLabel();
        lblListHeader.setText((!TextUtils.isEmpty(modelLabel))?model.getLabel().toUpperCase():"");


        return groupRow;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View childRow = convertView;
        if(childRow == null){
            childRow = LayoutInflater.from(mContext).inflate(R.layout.layout_list_item_single, parent, false);
        }

        TextView tvChild = (TextView)childRow.findViewById(R.id.lblListItem);
        tvChild.setText(mData.get(mHeaders.get(groupPosition)).get(childPosition).getLabel());
        return childRow;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
