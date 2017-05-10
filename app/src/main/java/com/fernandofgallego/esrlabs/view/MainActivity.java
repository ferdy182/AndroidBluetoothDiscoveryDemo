package com.fernandofgallego.esrlabs.view;

import android.Manifest;
import android.bluetooth.BluetoothDevice;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.fernandofgallego.esrlabs.R;
import com.fernandofgallego.esrlabs.presenter.MainPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainPresenter.View {

    RecyclerView recyclerView;
    DevicesAdapter adapter;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DevicesAdapter();
        recyclerView.setAdapter(adapter);
        presenter = new MainPresenter(this, this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        } else {
            presenter.startDiscovery();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void showDevices(List<BluetoothDevice> devices) {
        adapter.setItems(devices);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            presenter.startDiscovery();
        }
    }

}
