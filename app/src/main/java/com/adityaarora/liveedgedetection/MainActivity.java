package com.adityaarora.liveedgedetection;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.adityaarora.liveedgedetection.activity.ScanActivity;
import com.adityaarora.liveedgedetection.constants.ScanConstants;
import com.adityaarora.liveedgedetection.util.ScanUtils;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 101;
    private ImageView scannedImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scannedImageView = findViewById(R.id.scanned_image);
        startScan();
    }

    protected void startScan() {
        Intent intent = new Intent(this, ScanActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            String filePath = data.getExtras().getString(ScanConstants.SCANNED_RESULT);
            Bitmap baseBitmap = ScanUtils.decodeBitmapFromFile(filePath, ScanConstants.IMAGE_NAME);
            scannedImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            scannedImageView.setImageBitmap(baseBitmap);
        }
    }
}
