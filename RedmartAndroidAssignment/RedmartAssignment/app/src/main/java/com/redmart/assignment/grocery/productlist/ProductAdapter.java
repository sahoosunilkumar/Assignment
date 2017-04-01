package com.redmart.assignment.grocery.productlist;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.widget.ImageView;

import com.redmart.assignment.grocery.common.Constants;
import com.squareup.picasso.Picasso;
import com.sunilsahoo.inventorycontroller.entity.Product;
import com.redmart.assignment.grocery.R;
import com.redmart.assignment.grocery.databinding.ItemRowBinding;
import com.sunilsahoo.viewmodelbinding.ViewModelRecyclerViewAdapter;

public class ProductAdapter extends ViewModelRecyclerViewAdapter<ItemRowBinding> {
    private ObservableList<Product> list = new ObservableArrayList<>();

    private ProductListViewModel viewModel;
    public ProductAdapter(ObservableList<Product> list, ProductListViewModel viewModel) {
        super(R.layout.item_row);
        this.list = list;
        this.viewModel = viewModel;
        this.list.addOnListChangedCallback
                (super.getObservableCallback());
    }

    @Override
    public void onBindViewHolder(com.sunilsahoo.viewmodelbinding.common
                                             .BaseRecyclerViewHolder<ItemRowBinding> holder, int position) {
        holder.binding.setProduct(list.get
                (position));
        loadImage(position, ((ItemRowBinding)holder.binding).itemIcon);
        //load next set of item when scrolled to last element
        if ((position >= getItemCount() - 1))
            this.viewModel.getItems();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private void loadImage(int position, ImageView imageView) {
        if ((list.get(position) == null) || (list.get(position)
                .getImg().getName() == null)) {
            Picasso
                    .with(imageView.getContext())
                    .load(R.mipmap.ic_launcher)
                    .fit()
                    .into(imageView);
        } else {
            Picasso
                    .with(imageView.getContext())
                    .load(Constants.IMAGE_BASE_URL+list.get(position)
                            .getImg().getName())
                    .fit()
                    .into(imageView);
        }
    }

}