package com.fernandofgallego.esrlabs.view;

import android.bluetooth.BluetoothDevice;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.fernandofgallego.esrlabs.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ferdy182 on 10/5/17.
 */
class DevicesAdapter extends RecyclerView.Adapter<DevicesAdapter.DeviceViewHolder> {

    private List<BluetoothDevice> items = new ArrayList<>();

    public void setItems(List<BluetoothDevice> items) {
        this.items = items;
    }

    @Override
    public DeviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new DeviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DeviceViewHolder holder, int position) {
        BluetoothDevice device = items.get(position);
        holder.name.setText(device.getName());
        holder.address.setText(device.getAddress());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class DeviceViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView address;

        DeviceViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            address = (TextView) itemView.findViewById(R.id.address);
        }
    }

}
