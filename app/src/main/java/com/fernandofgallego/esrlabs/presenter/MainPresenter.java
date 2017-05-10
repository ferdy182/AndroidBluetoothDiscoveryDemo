package com.fernandofgallego.esrlabs.presenter;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import com.fernandofgallego.esrlabs.model.BluetoothDevices;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ferdy182 on 10/5/17.
 */

public class MainPresenter implements BluetoothDevices.DevicesCallback {

    private View view;
    private BluetoothDevices bluetoothDevices;
    private List<BluetoothDevice> foundDevices;

    public MainPresenter(View view, Context context) {
        this.view = view;
        bluetoothDevices = new BluetoothDevices(this, context);
        foundDevices = new ArrayList<>();
    }

    public void startDiscovery() {
        bluetoothDevices.startDiscovery();
    }

    @Override
    public void onDevicesFound(BluetoothDevice device) {
        foundDevices.add(device);
        view.showDevices(foundDevices);
    }


    public interface View {
        void showDevices(List<BluetoothDevice> devices);
    }
}
