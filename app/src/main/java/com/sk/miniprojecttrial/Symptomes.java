package com.sk.miniprojecttrial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Symptomes extends AppCompatActivity {

    Button showbtn;
    TextView csfix,cs1tv,cs2tv,cs3tv,testtv;
    DatabaseReference reff;
    String adhaarnumfromhome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptomes);

        showbtn = (Button)findViewById(R.id.showbtn);
        csfix = (TextView)findViewById(R.id.commonsymptomesfix);
        cs1tv = (TextView)findViewById(R.id.cs1);
        cs2tv = (TextView)findViewById(R.id.cs2);
        cs3tv = (TextView)findViewById(R.id.cs3);
        testtv = (TextView)findViewById(R.id.testtv);

        Intent symptom1 = getIntent();
        adhaarnumfromhome = symptom1.getStringExtra("adhaarnumtosymptom");
        testtv.setText(adhaarnumfromhome);


        showbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reff = FirebaseDatabase.getInstance().getReference();
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(adhaarnumfromhome.toString())) {
                            String varcs1 = dataSnapshot.child(adhaarnumfromhome.toString()).child("symptom1").getValue().toString();
                            String varcs2 = dataSnapshot.child(adhaarnumfromhome.toString()).child("symptom2").getValue().toString();
                            String varcs3 = dataSnapshot.child(adhaarnumfromhome.toString()).child("symptom3").getValue().toString();

                            if(varcs1.isEmpty()&& varcs2.isEmpty() && varcs3.isEmpty()){
                                csfix.setText("The patient has no common symptomes");
                                csfix.setVisibility(View.VISIBLE);
                            }

                            else {

                                cs1tv.setText(varcs1);
                                cs2tv.setText(varcs2);
                                cs3tv.setText(varcs3);

                                cs1tv.setVisibility(View.VISIBLE);
                                cs2tv.setVisibility(View.VISIBLE);
                                cs3tv.setVisibility(View.VISIBLE);
                                csfix.setVisibility(View.VISIBLE);
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}