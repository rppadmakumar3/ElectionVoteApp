package com.example.impactmakers.electionvote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class TemplateSelectionActivity extends AppCompatActivity implements TemplateAdapter.OnTemplateClickListener {

    private GridView mGridView;
    private TemplateAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_templateselection);



        // Create a list of template IDs
        List<Integer> templateIds = new ArrayList<>();
        templateIds.add(R.drawable.template1);
        templateIds.add(R.drawable.template2);
        templateIds.add(R.drawable.template3);
        templateIds.add(R.drawable.template4);
        templateIds.add(R.drawable.template1);
        templateIds.add(R.drawable.template2);
        templateIds.add(R.drawable.template3);
        templateIds.add(R.drawable.template4);

        // Create the adapter and set it to the GridView
        mAdapter = new TemplateAdapter(this, templateIds, this);
        mGridView = findViewById(R.id.grid_view_templates);
        mGridView.setAdapter(mAdapter);
    }

    @Override
    public void onTemplateClick(int templateId) {
        String imageUrl = "https://example.com/image.jpg";
        Intent intent = new Intent(this, CustomizationActivity.class);
        intent.putExtra("template_id", templateId);
        intent.putExtra("imageUri", imageUrl);
        startActivity(intent);
    }

}
