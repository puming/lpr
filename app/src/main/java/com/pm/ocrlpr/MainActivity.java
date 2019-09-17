package com.pm.ocrlpr;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.pm.lpr.LPRActivity;

/**
 * @author pm
 */
public class MainActivity extends AppCompatActivity {

    private final int REQUEST_LPR_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestDangerousPermissions();
        findViewById(R.id.brn_lpr).setOnClickListener(v -> {
            startActivityForResult(new Intent(this, LPRActivity.class), REQUEST_LPR_CODE);
        });
    }

    @SuppressLint("ShowToast")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 识别成功回调，车牌识别
        if (requestCode == REQUEST_LPR_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                String card = data.getStringExtra("card");
                new AlertDialog.Builder(this)
                        .setMessage(card)
                        .setNegativeButton("OK", (dialog, which) -> {
                        })
                        .show();
//                Toast.makeText(this, card, Toast.LENGTH_SHORT);
            }
        }
    }

    /**
     * 请求权限
     */
    public void requestDangerousPermissions() {
        String[] strings = new String[]{Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(this, strings, 100);
    }
}
