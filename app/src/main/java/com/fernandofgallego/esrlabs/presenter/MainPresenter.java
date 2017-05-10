package com.fernandofgallego.esrlabs.presenter;

import android.content.Context;
import android.util.Pair;
import com.fernandofgallego.esrlabs.model.BluetoothDevices;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ferdy182 on 10/5/17.
 */

public class MainPresenter implements BluetoothDevices.DevicesCallback {

    View view;
    BluetoothDevices bluetoothDevices;
    List<Pair<String,String>> foundDevices;

    public MainPresenter(View view, Context context) {
        this.view = view;
        bluetoothDevices = new BluetoothDevices(this, context);
        foundDevices = new ArrayList<>();
    }

    public void startDiscovery() {
        bluetoothDevices.startDiscovery();
    }

    @Override
    public void onDevicesFound(String deviceName, String deviceHardwareAddress) {
        foundDevices.add(new Pair<String, String>(deviceName, deviceHardwareAddress));
        view.showDevices(foundDevices);
    }


    public interface View {
        void showDevices(List<Pair<String,String>> devices);
    }
}
