/**
 *
 * Responsibility : Adapter for Recycler View
 */
package com.logitech.androidchallenge.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.logitech.androidchallenge.R;
import com.logitech.inventorycontoller.entity.Device;

import java.util.List;

public class DeviceListAdapter extends RecyclerView.Adapter<DeviceListAdapter
        .DeviceViewHolder> {

    private List<Device> deviceList;

    public class DeviceViewHolder extends RecyclerView.ViewHolder {
        public TextView name, type, model;

        public DeviceViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            model = (TextView) view.findViewById(R.id.model);
            type = (TextView) view.findViewById(R.id.type);
        }
    }


    public DeviceListAdapter(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    @Override
    public DeviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.device_list_item, parent, false);

        return new DeviceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DeviceViewHolder holder, int position) {
        Device device = deviceList.get(position);
        holder.name.setText(device.getName());
        holder.model.setText(device.getModel());
        holder.type.setText(device.getDeviceType());
    }

    @Override
    public int getItemCount() {
        return deviceList.size();
    }
}

