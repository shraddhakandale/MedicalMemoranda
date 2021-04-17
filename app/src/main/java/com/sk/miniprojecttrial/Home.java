package com.sk.miniprojecttrial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Home extends AppCompatActivity implements View.OnClickListener {

    String str;
    String str2;
    TextView tv,fixnametv,varnametv,fixbloodgrouptv,varbloodgrouptv,fixmobnumtv,varmobnumtv,fixdobtv,vardobtv;
    DatabaseReference reff;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button button1 = (Button) findViewById(R.id.btn1);
        Button button2 = (Button) findViewById(R.id.btn2);
        Button button3 = (Button) findViewById(R.id.btn3);


        tv = (TextView)findViewById(R.id.trialtextView);
        fixnametv = (TextView)findViewById(R.id.fixname);
        varnametv = (TextView)findViewById(R.id.varname);
        fixdobtv = (TextView)findViewById(R.id.fixdob);
        vardobtv = (TextView)findViewById(R.id.vardob);
        fixmobnumtv = (TextView)findViewById(R.id.fixmobnum);
        varmobnumtv = (TextView)findViewById(R.id.varmobnum);
        fixbloodgrouptv = (TextView)findViewById(R.id.fixbloodgrp);
        varbloodgrouptv = (TextView)findViewById(R.id.varbloodgroup);


        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);


        Intent adhaarnumber = getIntent();
        str = adhaarnumber.getStringExtra("adhaarnum");
        tv.setText(str);

        reff = FirebaseDatabase.getInstance().getReference();
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child(str).child("name").getValue().toString();
                String bloodgroup = dataSnapshot.child(str).child("bloodgroup").getValue().toString();
                String dateofbirth = dataSnapshot.child(str).child("dob").getValue().toString();
                String mobilenumber = dataSnapshot.child(str).child("mob").getValue().toString();

                varnametv.setText(name);
                varbloodgrouptv.setText(bloodgroup);
                vardobtv.setText(dateofbirth);
                varmobnumtv.setText(mobilenumber);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                str2 = tv.getText().toString();
                Intent diseases = new Intent(this, Diseases.class);
                diseases.putExtra("adhaarnumtodiseases",str2);
                startActivity(diseases);

                Toast.makeText(this, "Diseases", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn2:
                Toast.makeText(this, "Allergies", Toast.LENGTH_LONG).show();
                str2 = tv.getText().toString();
                Intent allergy = new Intent(this, Allergy.class);
                allergy.putExtra("adhaarnumtoallergy",str2);
                startActivity(allergy);
                break;

            case R.id.btn3:
                Toast.makeText(this, "Symptomes", Toast.LENGTH_LONG).show();
                str2 = tv.getText().toString();
                Intent symptom = new Intent(this, Symptomes.class);
                symptom.putExtra("adhaarnumtosymptom",str2);
                startActivity(symptom);
                break;

        }
    }
}