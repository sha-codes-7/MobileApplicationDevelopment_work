package com.example.devicemanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditDeviceActivity extends AppCompatActivity {

    private static final String TAG = "EditDeviceActivityLC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_edit_device);

        EditText nameInput = findViewById(R.id.etDeviceName);
        EditText batteryInput = findViewById(R.id.etBattery);

        String receivedName = getIntent().getStringExtra(MainActivity.EXTRA_DEVICE_NAME);
        int receivedBattery = getIntent().getIntExtra(MainActivity.EXTRA_BATTERY, 0);

        nameInput.setText(receivedName == null ? "" : receivedName);
        batteryInput.setText(String.valueOf(receivedBattery));

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(view -> {
            String updatedName = nameInput.getText().toString().trim();
            String batteryText = batteryInput.getText().toString().trim();

            // Task 8 Validation Case 1: Empty device name (Invalid Case)
            if (updatedName.isEmpty()) {
                nameInput.setError("Device name cannot be empty");
                nameInput.requestFocus();
                return;
            }

            // Task 8 Validation Case 2: Empty battery input (Invalid Case)
            if (batteryText.isEmpty()) {
                batteryInput.setError("Battery percentage is required");
                batteryInput.requestFocus();
                return;
            }

            // Task 8 Validation Case 3: Non-numeric battery input (Invalid Case)
            int updatedBattery;
            try {
                updatedBattery = Integer.parseInt(batteryText);
            } catch (NumberFormatException ex) {
                batteryInput.setError("Enter a valid whole number");
                batteryInput.requestFocus();
                return;
            }

            // Task 8 Validation Case 4: Range check [0, 100] (Boundary: 0, 100; Invalid: <0, >100)
            if (updatedBattery < 0 || updatedBattery > 100) {
                batteryInput.setError("Battery percentage must be between 0 and 100");
                batteryInput.requestFocus();
                return;
            }

            // Valid Normal & Boundary Cases: Pass result back to MainActivity
            Intent resultData = new Intent();
            resultData.putExtra(MainActivity.EXTRA_DEVICE_NAME, updatedName);
            resultData.putExtra(MainActivity.EXTRA_BATTERY, updatedBattery);
            setResult(RESULT_OK, resultData);
            finish();
        });

        Button btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(view -> {
            setResult(RESULT_CANCELED);
            finish();
        });
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