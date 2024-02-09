package com.example.impactmakers.electionvote;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import java.io.File;

public class CustomizationActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SELECT_PHOTO = 1;

    private static final int REQUEST_CODE_SHARE = 123;

    private ImageView mImageViewTemplate;
    private ImageView mImageViewUserPhoto;
    private EditText mEditTextName;
    private Button mButtonSave;

    private int mTemplateId;

    private boolean isAdjustingText = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);

        // Get references to UI elements
        mImageViewTemplate = findViewById(R.id.image_user_photo);
        mEditTextName = findViewById(R.id.edit_text_name);
        mButtonSave = findViewById(R.id.button_save);

        EditText editText = findViewById(R.id.edit_text_name);
        TextView textView = findViewById(R.id.memeTopText);

        // Get the ID of the selected template
        Intent intent = getIntent();
        mTemplateId = intent.getIntExtra("template_id", 0);

        // Set the selected template as the background of the template image view
        mImageViewTemplate.setImageResource(mTemplateId);
        mImageViewTemplate = findViewById(R.id.image_user_photo);
        mTemplateId = intent.getIntExtra("template_id", 0);


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // update the text of the TextView with the user entered text
                textView.setText(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // do nothing
            }
        });

        // Add an onClickListener to the "Save" button
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save the customized poster
            }
        });
        Button buttonShare = findViewById(R.id.button_save);
        buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Share the image file
                File imageFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "my_image.jpg");
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("image/*");
                Uri imageUri = FileProvider.getUriForFile(CustomizationActivity.this, BuildConfig.APPLICATION_ID + ".fileprovider", imageFile);
                shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this image");
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivityForResult(Intent.createChooser(shareIntent, "Share image via"), REQUEST_CODE_SHARE);
            }
        });
        Button buttonDownload = findViewById(R.id.button_save1);
        buttonDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(CustomizationActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    // Permission not granted, request it again
                    ActivityCompat.requestPermissions(CustomizationActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    // Permission already granted, proceed with download
                    String imageUrl = getIntent().getStringExtra("imageUri");
                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(imageUrl));
                    request.setTitle("Image Download");
                    request.setDescription("Downloading image");
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "image.jpg");
                    DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                    if (downloadManager != null) {
                        downloadManager.enqueue(request);
                        Toast.makeText(CustomizationActivity.this, "Downloading...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}






















