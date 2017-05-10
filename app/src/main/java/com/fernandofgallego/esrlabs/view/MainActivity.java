package com.fernandofgallego.esrlabs.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.fernandofgallego.esrlabs.R;
import com.fernandofgallego.esrlabs.presenter.MainPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainPresenter.View {

    RecyclerView recyclerView;
    DevicesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.list);
        MainPresenter presenter = new MainPresenter(this, this);
        presenter.startDiscovery();
    }

    @Override
    public void showDevices(List<Pair<String, String>> devices) {
        adapter = new DevicesAdapter(devices);
        adapter.notifyDataSetChanged();
    }

    class DevicesAdapter extends RecyclerView.Adapter<DevicesAdapter.DeviceViewHolder> {

        List<Pair<String, String>> items;

        public DevicesAdapter(List<Pair<String, String>> items) {
            this.items = items;
        }

        @Override
        public DeviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
            return new DeviceViewHolder(view);
        }

        @Override
        public void onBindViewHolder(DeviceViewHolder holder, int position) {
            Pair<String,String> device = items.get(position);
            holder.name.setText(device.first);
            holder.address.setText(device.second);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        class DeviceViewHolder extends RecyclerView.ViewHolder {
            TextView name;
            TextView address;

            public DeviceViewHolder(View itemView) {
                super(itemView);
                name = (TextView) itemView.findViewById(R.id.name);
                address = (TextView) itemView.findViewById(R.id.address);
            }
        }

    }
}
