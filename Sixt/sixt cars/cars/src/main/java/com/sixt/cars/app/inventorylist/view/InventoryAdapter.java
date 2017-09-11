package com.sixt.cars.app.inventorylist.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sixt.cars.R;
import com.sixt.inventorycontroller.entity.CarInfo;

import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter
        .ViewHolder> {
    private final OnItemClickListener listener;
    private List<CarInfo> data;

    public InventoryAdapter(List<CarInfo> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
    }


    @Override
    public InventoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int
            viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .item_gamelist, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView
                .LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams
                .WRAP_CONTENT));
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(InventoryAdapter.ViewHolder holder, int
            position) {
        holder.click(data.get(position), listener);
        holder.name.setText(data.get(position).getName());
        holder.model.setText(data.get(position).getModelName());
        holder.color.setText(data.get(position).getColor());
        getImage(holder.icon, data.get(position));
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    private void getImage(ImageView view, CarInfo carInfo) {
        String url = String.format(view.getContext().getString(R.string
                .image_url), carInfo.getModelIdentifier(), carInfo.getColor());
        Glide.with(view.getContext()).load(url).into
                (view);
    }

    public interface OnItemClickListener {
        void onClick(CarInfo Item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, model, color;
        ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            model = (TextView) itemView.findViewById(R.id.model);
            color = (TextView) itemView.findViewById(R.id.color);
            icon = (ImageView) itemView.findViewById(R.id.icon);
        }


        public void click(final CarInfo cityListData, final OnItemClickListener
                listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(cityListData);
                }
            });
        }
    }


}
