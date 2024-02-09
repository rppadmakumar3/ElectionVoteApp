package com.example.impactmakers.electionvote;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class RewardActivity extends AppCompatActivity {

    private Bitmap qrCodeBitmap = null;
    private Button Btn1,Btn2,Btn3,Btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward2);

        Btn1 = findViewById(R.id.redeem_button_1);
        Btn2 = findViewById(R.id.redeem_button_2);
        Btn3 = findViewById(R.id.redeem_button_3);
        Btn4 = findViewById(R.id.redeem_button_4);

        Btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Generate the unique QR code for reward 1
                generateQRCode("Reward 1 QR code data");

                // Display the QR code in a popup
                showQRCodePopup();
            }
        });

        Btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Generate the unique QR code for reward 2
                generateQRCode("Reward 2 QR code data");

                // Display the QR code in a popup
                showQRCodePopup();
            }
        });

        Btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Generate the unique QR code for reward 1
                generateQRCode("Reward 3 QR code data");

                // Display the QR code in a popup
                showQRCodePopup();
            }
        });

        Btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Generate the unique QR code for reward 1
                generateQRCode("Reward 4 QR code data");

                // Display the QR code in a popup
                showQRCodePopup();
            }
        });
    }

    private void generateQRCode(String data) {
        try {
            // Encode the data into a QR code bitmap
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            qrCodeBitmap = barcodeEncoder.encodeBitmap(data, BarcodeFormat.QR_CODE, 400, 400);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

// Similarly for the other two redeem buttons

    private void showQRCodePopup() {
        // Inflate the layout for the popup
        View popupView = getLayoutInflater().inflate(R.layout.qr_code_popup, null);

        // Set the QR code bitmap to the ImageView in the popup
        ImageView qrCodeImageView = popupView.findViewById(R.id.qr_code_image);
        qrCodeImageView.setImageBitmap(qrCodeBitmap);

        // Create the popup window
        PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);

        // Show the popup window at the center of the screen
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
    }



}