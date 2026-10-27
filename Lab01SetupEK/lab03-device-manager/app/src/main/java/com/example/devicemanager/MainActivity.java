package com.example.devicemanager;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityLC";

    // Task 9: Keys for saving/restoring instance state on rotation
    private static final String KEY_DEVICE_NAME = "saved_device_name";
    private static final String KEY_BATTERY = "saved_battery";

    public static final String EXTRA_DEVICE_NAME = "com.example.devicemanager.DEVICE_NAME";
    public static final String EXTRA_BATTERY = "com.example.devicemanager.BATTERY";

    private String deviceName = "Sensor-A";
    private int battery = 15;

    // Task 5_ Activity Result Launcher setup
    private final ActivityResultLauncher<Intent> editDeviceLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    String returnedName = data.getStringExtra(EXTRA_DEVICE_NAME);
                    int returnedBattery = data.getIntExtra(EXTRA_BATTERY, battery);

                    if (returnedName != null) {
                        deviceName = returnedName;
                    }
                    battery = returnedBattery;
                    updateSummary();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_main);

        // Task 9: Restore state if activity was recreated (e.g., after screen rotation)
        if (savedInstanceState != null) {
            deviceName = savedInstanceState.getString(KEY_DEVICE_NAME, "Sensor-A");
            battery = savedInstanceState.getInt(KEY_BATTERY, 15);
            Log.d(TAG, "Restored state on rotation: " + deviceName + ", " + battery);
        }

        updateSummary();

        // Task 3 & 5: Open Edit Device
        Button editButton = findViewById(R.id.btnEditDevice);
        editButton.setOnClickListener(view -> {
            Intent editIntent = new Intent(MainActivity.this, EditDeviceActivity.class);
            editIntent.putExtra(EXTRA_DEVICE_NAME, deviceName);
            editIntent.putExtra(EXTRA_BATTERY, battery);
            editDeviceLauncher.launch(editIntent);
        });

        // Task 6: Implicit Intent to open documentation link
        Button docsButton = findViewById(R.id.btnOpenDocumentation);
        docsButton.setOnClickListener(view -> {
            Uri page = Uri.parse("https://developer.android.com");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, page);
            try {
                startActivity(webIntent);
            } catch (ActivityNotFoundException ex) {
                Toast.makeText(this, "No application can open this link", Toast.LENGTH_LONG).show();
            }
        });
    }

    // Task 9: Save state before activity is recreated due to configuration changes
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_DEVICE_NAME, deviceName);
        outState.putInt(KEY_BATTERY, battery);
        Log.d(TAG, "onSaveInstanceState: Saved current state before rotation");
    }

    private void updateSummary() {
        TextView nameView = findViewById(R.id.tvDeviceName);
        TextView batteryView = findViewById(R.id.tvBattery);
        nameView.setText(getString(R.string.device_name_value, deviceName));
        batteryView.setText(getString(R.string.battery_value, battery));
    }

    // Task 7: Activity Lifecycle Overrides
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }
    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }
    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }
    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }
}