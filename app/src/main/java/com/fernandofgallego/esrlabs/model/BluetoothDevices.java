package com.fernandofgallego.esrlabs.model;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Created by ferdy182 on 10/5/17.
 */

public class BluetoothDevices {

    BluetoothAdapter bluetoothAdapter;
    DevicesCallback callback;

    public BluetoothDevices(DevicesCallback callback, Context context) {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        this.callback = callback;

        // Register for broadcasts when a device is discovered.
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        context.registerReceiver(mReceiver, filter);
    }

    public void startDiscovery() {
        if(bluetoothAdapter != null) {
            bluetoothAdapter.startDiscovery();
        }
    }

    // Create a BroadcastReceiver for ACTION_FOUND.
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Discovery has found a device. Get the BluetoothDevice
                // object and its info from the Intent.
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address

                if(callback != null) {
                    callback.onDevicesFound(deviceName, deviceHardwareAddress);
                }
            }
        }
    };

    public interface DevicesCallback {
        void onDevicesFound(String deviceName, String deviceHardwareAddress);
    }
}
