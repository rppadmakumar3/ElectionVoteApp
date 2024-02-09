package com.example.impactmakers.electionvote;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;

public class TemplateAdapter extends BaseAdapter {

    private Context mContext;
    private List<Integer> mTemplateIds;
    private OnTemplateClickListener mListener;

    public TemplateAdapter(Context context, List<Integer> templateIds, OnTemplateClickListener listener) {
        mContext = context;
        mTemplateIds = templateIds;
        mListener = listener;
    }

    @Override
    public int getCount() {
        return mTemplateIds.size();
    }

    @Override
    public Object getItem(int position) {
        return mTemplateIds.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // If it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        // Load the template image into the ImageView
        int templateId = mTemplateIds.get(position);
        imageView.setImageResource(templateId);

        // Set a click listener for the ImageView
        imageView.setOnClickListener(v -> mListener.onTemplateClick(templateId));

        return imageView;
    }

    public interface OnTemplateClickListener {
        void onTemplateClick(int templateId);
    }
}
