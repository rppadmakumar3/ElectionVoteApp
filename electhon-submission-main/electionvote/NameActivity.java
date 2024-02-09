package com.example.impactmakers.electionvote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NameActivity extends AppCompatActivity {

    private EditText nameEditText;
    private Button submitButton;
    private DatabaseReference databaseReference;
    private FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        nameEditText = findViewById(R.id.name_edittext);
        submitButton = findViewById(R.id.submit_button);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUser.getUid());
        }

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEditText.getText().toString().trim();

                if (name.isEmpty()) {
                    Toast.makeText(NameActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                } else {
                    databaseReference.child("name").setValue(name).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(NameActivity.this, "Name saved successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(NameActivity.this, MainActivity.class);
                                intent.putExtra("name", name);
                                startActivity(intent);
                                finish(); // finish this activity and go back to MainActivity
                            } else {
                                Toast.makeText(NameActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}