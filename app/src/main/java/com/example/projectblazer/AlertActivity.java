package com.example.projectblazer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AlertActivity extends AppCompatActivity {

    Button btnWin,btnLose;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        btnWin = findViewById(R.id.btnWin);
        btnLose = findViewById(R.id.btnLose);
        dialog = new Dialog(this);

        btnWin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWinDialog();

            }
        });

        btnLose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoseDialog();

            }
        });




    }

    private void openLoseDialog() {
    }

    private void openWinDialog() {
        dialog.setContentView(R.layout.win_layout_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView imageViewClose = dialog.findViewById(R.id.imageViewClose);
        Button btnOk = dialog.findViewById(R.id.btnOK);
        dialog.show();
    }
}