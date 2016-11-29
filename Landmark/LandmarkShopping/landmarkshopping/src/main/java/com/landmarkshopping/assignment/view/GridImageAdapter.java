package com.landmarkshopping.assignment.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.landmarkshopping.assignment.R;
import com.landmarkshopping.assignment.databinding.GridItemDetailBinding;
import com.landmarkshopping.assignment.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GridImageAdapter extends BaseAdapter {
    private List<Product> mGridItemList = null;
    private Context mContext;
    private int mNoOfPageInGrid = 0;
    private int mPageStartIndex = 0;
    private int mPageEndIndex = 0;


    public GridImageAdapter(Context context, List<Product> itemDtlList, int
			startIndex, int endIndex) {
        this.mGridItemList = itemDtlList;
        this.mContext = context;
        this.mPageStartIndex = startIndex;
        this.mPageEndIndex = endIndex;
        this.mNoOfPageInGrid = (mPageEndIndex - mPageStartIndex) + 1;
    }

    @Override
    public int getCount() {
        this.mNoOfPageInGrid = Math.min(Math.max(this.mNoOfPageInGrid, 0),
				mGridItemList.size());
        return this.mNoOfPageInGrid;
    }

    @Override
    public Product getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return mPageStartIndex + position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        GridItemDetailBinding rowBinding = DataBindingUtil.getBinding
				(convertView);
        if (rowBinding == null) {
            rowBinding =
                    GridItemDetailBinding.bind(LayoutInflater.from(parent
							.getContext()).inflate(R.layout.grid_item_detail,
							parent, false));
        }

        Product product = mGridItemList.get(position + mPageStartIndex);
        rowBinding.setProduct(product);
        setProductImage(product, (ImageView) rowBinding.getRoot()
				.findViewById(R.id.image));
//Log.d("Adapter", "inside product :"+product);
        return (rowBinding.getRoot());
    }

    public void setProductImage(Product product, ImageView imageView) {

        Picasso.with(mContext)
                .load(product.getImagePath())
                .fit()
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }

}
