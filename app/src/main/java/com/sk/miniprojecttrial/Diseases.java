package com.sk.miniprojecttrial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Diseases extends AppCompatActivity {

    TextView fixnametv,fixbloodgrptv,fixdobtv,fixdiseasestv,varnametv,varbloodgrptv,vardobtv,vardisease1tv,vardisease2tv,vardisease3tv,vardisease4tv,testtv,diseasetextview;

    Button showbtn;

    String adhaarnumfromhome;

    DatabaseReference reff;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diseases);



//        fixnametv = (TextView)findViewById(R.id.fixname);
//        fixbloodgrptv = (TextView)findViewById(R.id.fixbloodgrp);
//        fixdobtv = (TextView)findViewById(R.id.dob);
//        fixdiseasestv = (TextView)findViewById(R.id.diseasetv);
//        varnametv = (TextView)findViewById(R.id.name);
//        varbloodgrptv = (TextView)findViewById(R.id.bloodgroup);
//        vardobtv = (TextView)findViewById(R.id.dateofbirth);
        vardisease1tv = (TextView)findViewById(R.id.disease1tv);
        vardisease2tv = (TextView)findViewById(R.id.disease2tv);
        vardisease3tv = (TextView)findViewById(R.id.disease3tv);
        vardisease4tv = (TextView)findViewById(R.id.disease4tv);
        diseasetextview = (TextView)findViewById(R.id.diseasetextView);
        showbtn = (Button)findViewById(R.id.showbtn);
        testtv = (TextView)findViewById(R.id.testingtv);


        Intent d1 = getIntent();
        adhaarnumfromhome = d1.getStringExtra("adhaarnumtodiseases");
        testtv.setText(adhaarnumfromhome);

        showbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View view) {
                reff = FirebaseDatabase.getInstance().getReference();
//                .child(adhaarnumfromhome.toString())
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(adhaarnumfromhome.toString())) {
//                            String name = dataSnapshot.child(adhaarnumfromhome).child("name").getValue().toString();
                            String disease1 = dataSnapshot.child(adhaarnumfromhome).child("disease1").getValue().toString();
                            String disease2 = dataSnapshot.child(adhaarnumfromhome).child("disease2").getValue().toString();
                            String disease3 = dataSnapshot.child(adhaarnumfromhome).child("disease3").getValue().toString();
                            String disease4 = dataSnapshot.child(adhaarnumfromhome).child("disease4").getValue().toString();
//                            String bloodgroup = dataSnapshot.child(adhaarnumfromhome).child("bloodgroup").getValue().toString();
//                            String dateofbirth = dataSnapshot.child(adhaarnumfromhome).child("dob").getValue().toString();

                            if(disease1.isEmpty() && disease2.isEmpty() && disease3.isEmpty() && disease4.isEmpty()){
                                diseasetextview.setText("Patient has no major disease");
                                diseasetextview.setVisibility(View.VISIBLE);
                            }
                            else {

//                                varnametv.setText(name);
                                vardisease1tv.setText(disease1);
                                vardisease2tv.setText(disease2);
                                vardisease3tv.setText(disease3);
                                vardisease4tv.setText(disease4);
//                                varbloodgrptv.setText(bloodgroup);
//                                vardobtv.setText(dateofbirth);

//                                fixnametv.setVisibility(View.VISIBLE);
//                                fixbloodgrptv.setVisibility(View.VISIBLE);
//                                fixdobtv.setVisibility(View.VISIBLE);
//                                fixdiseasestv.setVisibility(View.VISIBLE);
//                                varnametv.setVisibility(View.VISIBLE);
//                                varbloodgrptv.setVisibility(View.VISIBLE);
//                                vardobtv.setVisibility(View.VISIBLE);
                                vardisease1tv.setVisibility(View.VISIBLE);
                                vardisease2tv.setVisibility(View.VISIBLE);
                                vardisease3tv.setVisibility(View.VISIBLE);
                                vardisease4tv.setVisibility(View.VISIBLE);
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        System.out.println("no valid registration please register first!! Thank you for visiting.");
                    }
                });


            }
        });
    }
}