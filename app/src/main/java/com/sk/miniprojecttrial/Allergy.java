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

import org.w3c.dom.Text;

public class Allergy extends AppCompatActivity {

    Button showbtn;
    TextView allergytexttv,allergy1tv,allergy2tv,allergy3tv,testtv;
    DatabaseReference reff;
    String adhaarnumfromhome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergy);

        testtv = (TextView)findViewById(R.id.testtv);
        showbtn = (Button)findViewById(R.id.showbtn);
        allergytexttv = (TextView)findViewById(R.id.allergytexttv);
        allergy1tv = (TextView)findViewById(R.id.allergy1);
        allergy2tv = (TextView)findViewById(R.id.allergy2);
        allergy3tv = (TextView)findViewById(R.id.allergy3);

        final Intent allergy1 = getIntent();
        adhaarnumfromhome = allergy1.getStringExtra("adhaarnumtoallergy");
        testtv.setText(adhaarnumfromhome);

        showbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reff = FirebaseDatabase.getInstance().getReference();
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(adhaarnumfromhome.toString())) {
                            String al1 = dataSnapshot.child(adhaarnumfromhome.toString()).child("allergy1").getValue().toString();
                            String al2 = dataSnapshot.child(adhaarnumfromhome.toString()).child("allergy2").getValue().toString();
                            String al3 = dataSnapshot.child(adhaarnumfromhome.toString()).child("allergy3").getValue().toString();

                            if(al1.isEmpty() && al2.isEmpty() && al3.isEmpty()){
                                allergytexttv.setText("This patient has no allergy of any medicine");
                                allergytexttv.setVisibility(View.VISIBLE);
                            }
                            else {

                                allergy1tv.setText(al1);
                                allergy2tv.setText(al2);
                                allergy3tv.setText(al3);

                                allergy1tv.setVisibility(View.VISIBLE);
                                allergy2tv.setVisibility(View.VISIBLE);
                                allergy3tv.setVisibility(View.VISIBLE);
                                allergytexttv.setVisibility(View.VISIBLE);
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