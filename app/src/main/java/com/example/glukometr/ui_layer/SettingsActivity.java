package com.example.glukometr.ui_layer;

import static androidx.core.app.ActivityCompat.startActivityForResult;

import static com.example.glukometr.data_layer.Bluetooth.REQUEST_ENABLE_BT;
import static com.example.glukometr.data_layer.Bluetooth.bluetoothAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.glukometr.R;
import com.example.glukometr.data_layer.Bluetooth;

import java.util.Set;

public class SettingsActivity extends AppCompatActivity {
    SwitchCompat switchCompat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        switchCompat = findViewById(R.id.switch_compat);
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    if (!bluetoothAdapter.isEnabled()) {
                        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        try {
                            startActivity(enableBtIntent);
                        } catch (SecurityException e) {
                            Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_SHORT).show();
                        }
                    }
                    try {
                        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
                        if (pairedDevices.size() > 0) {
                            // There are paired devices. Get the name and address of each paired device.
                            for (BluetoothDevice device : pairedDevices) {
                                String deviceName = device.getName();
                                String deviceHardwareAddress = device.getAddress(); // MAC address
                            }
                        }
                    }
                    catch (SecurityException e){
                        return;
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Bluetooth turned off",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void onClickHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void onClickRecentList(View view){
        Intent intent = new Intent(this, RecentListActivity.class);
        startActivity(intent);
    }


}