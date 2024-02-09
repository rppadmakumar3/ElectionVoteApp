package com.example.impactmakers.electionvote;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QueueActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner stateSpinner, districtSpinner, constituencySpinner;
    private EditText queueEditText;

    private String[] indianStates = {"Select a state", "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jharkhand", "Karnataka", "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana", "Tripura", "Uttar Pradesh", "Uttarakhand", "West Bengal"};

    private String[] tamilNaduDistricts = {"Select a district", "Chennai", "Coimbatore", "Cuddalore", "Dindigul", "Erode", "Kanchipuram", "Kanyakumari", "Karur", "Krishnagiri", "Madurai", "Nagapattinam", "Namakkal", "Perambalur", "Pudukkottai", "Ramanathapuram", "Salem", "Sivaganga", "Thanjavur", "The Nilgiris", "Theni", "Thiruvallur", "Thiruvarur", "Thoothukkudi", "Tiruchirappalli", "Tirunelveli", "Tirupathur", "Tiruppur", "Tiruvannamalai", "Vellore", "Viluppuram", "Virudhunagar"};

    private String[] keralaDistricts = {"Select a district", "Alappuzha", "Ernakulam", "Idukki", "Kannur", "Kasaragod", "Kollam", "Kottayam", "Kozhikode", "Malappuram", "Palakkad", "Pathanamthitta", "Thiruvananthapuram", "Thrissur", "Wayanad"};

    private String[] maduraiConstituents = {"Select a constituent", "Madurai East", "Madurai West"};

    private String[] dindigulConstituents = {"Select a constituent", "Dindigul"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);

        stateSpinner = findViewById(R.id.state_spinner);
        districtSpinner = findViewById(R.id.district_spinner);
        constituencySpinner = findViewById(R.id.constituency_spinner);
        queueEditText = findViewById(R.id.queue_edit_text);

        ArrayAdapter<String> stateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, indianStates);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(stateAdapter);
        stateSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> districtAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tamilNaduDistricts);
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        districtSpinner.setAdapter(districtAdapter);
        districtSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> constituencyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, maduraiConstituents);
        constituencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        constituencySpinner.setAdapter(constituencyAdapter);
        constituencySpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.state_spinner:
                String selectedState = indianStates[position];
                if (selectedState.equals("Tamil Nadu")) {
                    ArrayAdapter<String> districtAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tamilNaduDistricts);
                    districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    districtSpinner.setAdapter(districtAdapter);
                } else if (selectedState.equals("Kerala")) {
                    ArrayAdapter<String> districtAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, keralaDistricts);
                    districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    districtSpinner.setAdapter(districtAdapter);
                } else {
                    districtSpinner.setAdapter(null);
                    constituencySpinner.setAdapter(null);
                }
                break;
            case R.id.district_spinner:
                String selectedDistrict = (String) parent.getItemAtPosition(position);
                if (selectedDistrict.equals("Madurai")) {
                    ArrayAdapter<String> constituencyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, maduraiConstituents);
                    constituencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    constituencySpinner.setAdapter(constituencyAdapter);
                } else if (selectedDistrict.equals("Dindigul")) {
                    ArrayAdapter<String> constituencyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dindigulConstituents);
                    constituencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    constituencySpinner.setAdapter(constituencyAdapter);
                } else {
                    constituencySpinner.setAdapter(null);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Nothing selected", Toast.LENGTH_SHORT).show();
    }

    public void saveQueueStatus(View view) {
        String queueStatus = queueEditText.getText().toString().trim();
        if (queueStatus.isEmpty()) {
            Toast.makeText(this, "Please enter the queue status", Toast.LENGTH_SHORT).show();
        } else {
            // Save the queue status to the database or perform any other action
            Toast.makeText(this, "Queue status saved: " + queueStatus, Toast.LENGTH_SHORT).show();
        }
    }
}