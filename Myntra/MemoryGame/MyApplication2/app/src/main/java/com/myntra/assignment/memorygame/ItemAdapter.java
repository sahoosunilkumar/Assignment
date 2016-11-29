package com.myntra.assignment.memorygame;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.myntra.assignment.controller.entity.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sunilkumarsahoo on 11/14/16.
 */
public class ItemAdapter extends BaseAdapter {

    private Context mContext;
    private List<Item> itemList;
    private int mImageWidth;

    ItemAdapter(Context context, List<Item> itemList, int imageWidth) {
        mContext = context;
        this.itemList = itemList;
        mImageWidth = imageWidth;
    }

    @Override
// Get a View that displays the data at the specified position in
// the data set.
    public View getView(int position, View convertView,
                        ViewGroup gridView) {
        // try to reuse the views.
        ImageView view = (ImageView) convertView;
        // if convert view is null then create a new instance else reuse
        // it
        if (view == null) {
            view = new ImageView(mContext);
            view.setMinimumWidth(mImageWidth);
            view.setMinimumHeight(mImageWidth);
        }
        loadImage(position, view);//.setImageResource(mDrawables.get(position));
        view.setTag(String.valueOf(position));
        return view;
    }

    private void loadImage(int position, ImageView imageView) {
        if ((itemList.get(position) == null) || (itemList.get(position)
                .getMedia() == null)) {
            Picasso
                    .with(mContext)
                    .load(R.drawable.question)
                    .fit()
                    .into(imageView);
        } else {
            Picasso
                    .with(mContext)
                    .load(itemList.get(position).getMedia().getM())
                    .fit()
                    .into(imageView);
        }
    }

    @Override
// Get the row id associated with the specified position in the
// list.
    public long getItemId(int position) {
        return position;
    }

    @Override
// Get the data item associated with the specified position in the
// data set.
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
// How many items are in the data set represented by this Adapter.
    public int getCount() {
        return itemList.size();
    }
}