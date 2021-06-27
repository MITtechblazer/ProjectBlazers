package com.example.projectblazer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class HomeActivity extends AppCompatActivity {
    private TextView alertTextView;
    private TextView textView;
    private TextView textSteps;
    private double MagnitudePrevious = 0;
    private Integer stepCount = 0;
    private AlertDialog.Builder builder;
    ToggleButton btnToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        textView = findViewById(R.id.tv_stepsTaken);
        textSteps = findViewById(R.id.steps);
        btnToggle = findViewById(R.id.togglebutton);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SensorEventListener stepDetector = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (sensorEvent!= null) {
                    float x_acceleration = sensorEvent.values[0];
                    float y_acceleration = sensorEvent.values[1];
                    float z_acceleration = sensorEvent.values[2];
                    double Magnitude = Math.sqrt(x_acceleration*x_acceleration + y_acceleration*y_acceleration + z_acceleration*z_acceleration);
                    double MagnitudeDelta = Magnitude - MagnitudePrevious;
                    MagnitudePrevious = Magnitude;

                    if (MagnitudeDelta > 3){
                        stepCount++;

                        if (stepCount==10) {

                            builder = new AlertDialog.Builder(HomeActivity.this);
                            builder.setTitle("You are moving")
                                    .setMessage("Stay alert on your surroundings.")
                                    .setCancelable(true);

                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });

                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //alertTextView.setVisibility(View.VISIBLE);
                                    dialogInterface.cancel();
                                }
                            });

                            builder.show();
                        } else if (stepCount==20) {

                            builder = new AlertDialog.Builder(HomeActivity.this);
                            builder.setTitle("You are still moving")
                                    .setMessage("Stay alert on your surroundings.")
                                    .setCancelable(true);

                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });

                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //alertTextView.setVisibility(View.VISIBLE);
                                    dialogInterface.cancel();
                                }
                            });

                            builder.show();
                        }
                    }

                    textView.setText(stepCount.toString());

                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };

        sensorManager.registerListener(stepDetector, sensor, SensorManager.SENSOR_DELAY_NORMAL);


        btnToggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // The toggle is enabled
                Toast.makeText(HomeActivity.this, "Toggle is ON", Toast.LENGTH_SHORT).show();
                textSteps.setText("Count steps");
                textView.setVisibility(View.VISIBLE);
            } else {
                // The toggle is disabled
                Toast.makeText(HomeActivity.this, "Toggle is OFF", Toast.LENGTH_SHORT).show();
                //stepCount=null;
                textSteps.setText("App is disabled");
                textView.setVisibility(View.GONE);
                /*PackageManager pm = getPackageManager();
                pm.setComponentEnabledSetting(new ComponentName(this, HomeActivity.class),
                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);*/
            }
        });
    }

    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putInt("stepCount", stepCount);
        editor.apply();
    }

    protected void onStop() {
        super.onStop();

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putInt("stepCount", stepCount);
        editor.apply();
    }

    protected void onResume() {
        super.onResume();
        Toast.makeText(HomeActivity.this, "Resumed", Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        stepCount = sharedPreferences.getInt("stepCount", 0);
    }
}