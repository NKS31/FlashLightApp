package com.example.flashlight;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class MainActivity extends AppCompatActivity {

        @SuppressLint("UseSwitchCompatOrMaterialCode")
        private SwitchCompat aSwitch;
        private CameraManager cameraManager;
        private TextView textView;
        private String getCameraID;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            aSwitch = findViewById(R.id.on_off_switch);

            textView = findViewById((R.id.cam_result));

            cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            try {
                getCameraID = cameraManager.getCameraIdList()[0];
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
            aSwitch.setOnCheckedChangeListener((compoundButton, isChecked) -> torch(isChecked));
        }

        @SuppressLint("SetTextI18n")
        private void torch(boolean isChecked) {
            if (aSwitch.isChecked()) {
                try {
                    cameraManager.setTorchMode(getCameraID, isChecked);
                    Toast.makeText(MainActivity.this, "Flashlight ON", Toast.LENGTH_SHORT).show();
                    textView.setText("ON");
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    cameraManager.setTorchMode(getCameraID, isChecked);
                    Toast.makeText(MainActivity.this, "Flashlight OFF", Toast.LENGTH_SHORT).show();
                    textView.setText("OFF");
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void finish() {
            super.finish();
            try {
                cameraManager.setTorchMode(getCameraID, false);
                Toast.makeText(MainActivity.this, "Flashlight OFF", Toast.LENGTH_SHORT).show();
                textView.setText("OFF");
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }