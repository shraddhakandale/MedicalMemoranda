package com.sk.miniprojecttrial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Registration extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {

    EditText fullname,bgroup,mobilenum,adhaarnum,disease1et,disease2et,disease3et,disease4et,allergy1et,allergy2et,allergy3et,sym1et,sym2et,sym3et;
    TextView diseaseheading,allergyheading,symptomheading,dateob,bgtextview;
    Button regbtn,dobbtn;
    Spinner spn;
    String Bloodgroup[];
    DatabaseReference reff;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        fullname = (EditText)findViewById(R.id.fullname);
        dateob = (TextView) findViewById(R.id.dob);
        bgroup = (EditText)findViewById(R.id.bgrp);
        mobilenum = (EditText)findViewById(R.id.mobnum);
        adhaarnum = (EditText)findViewById(R.id.Adhaaridnum);
        disease1et = (EditText)findViewById(R.id.disease1hint);
        disease2et = (EditText)findViewById(R.id.disease2hint);
        disease3et = (EditText)findViewById(R.id.disease3hint);
        disease4et = (EditText)findViewById(R.id.disease4hint);
        allergy1et = (EditText)findViewById(R.id.allergy1hint);
        allergy2et = (EditText)findViewById(R.id.allergy2hint);
        allergy3et = (EditText)findViewById(R.id.allergy3hint);
        sym1et = (EditText)findViewById(R.id.symptom1hint);
        sym2et = (EditText)findViewById(R.id.symptom2hint);
        sym3et = (EditText)findViewById(R.id.symptom3hint);
        spn = (Spinner)findViewById(R.id.bgrpspn);
        diseaseheading = (TextView)findViewById(R.id.diseaseheading);
        allergyheading = (TextView)findViewById(R.id.allergyheading);
        symptomheading = (TextView)findViewById(R.id.symptomesheading);
        bgtextview = (TextView)findViewById(R.id.bgtextView);
        regbtn = (Button)findViewById(R.id.Registerbtn);
        dobbtn = (Button)findViewById(R.id.dobbtn);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Bloodgroup,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(adapter);
        spn.setOnItemSelectedListener(this);

        dobbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdatepickup();


                        dateob.setVisibility(View.VISIBLE);
                        dobbtn.setVisibility(View.INVISIBLE);


            }
        });

        dateob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dobbtn.setVisibility(View.VISIBLE);
            }
        });

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = fullname.getText().toString().trim();
                String dob = dateob.getText().toString().trim();
                String bloodgroup = bgroup.getText().toString().trim();
                String mob = mobilenum.getText().toString().trim();
                final String adnum = adhaarnum.getText().toString().trim();
                String disease1 = disease1et.getText().toString().trim();
                String disease2 = disease2et.getText().toString().trim();
                String disease3 = disease3et.getText().toString().trim();
                String disease4 = disease4et.getText().toString().trim();
                String allergy1 = allergy1et.getText().toString().trim();
                String allergy2 = allergy2et.getText().toString().trim();
                String allergy3 = allergy3et.getText().toString().trim();
                String symptom1 = sym1et.getText().toString().trim();
                String symptom2 = sym2et.getText().toString().trim();
                String symptom3 = sym3et.getText().toString().trim();

                if(name.isEmpty()||dob.isEmpty()||bloodgroup.isEmpty()||mob.isEmpty()||adnum.isEmpty()){
                    Toast.makeText(Registration.this, "Oops! Please fill the data correctly!!", Toast.LENGTH_LONG).show();
                    return;
                }

                else if(adnum.length()<12 || adnum.length()>12){
                    Toast.makeText(Registration.this, "Adhaar number should be of 12 digits.", Toast.LENGTH_LONG).show();
                    adhaarnum.setText("");
                    return;
                }

                else if(mob.length()>10 || mob.length()<10) {
                    Toast.makeText(Registration.this, "Mobile number should be of 10 digits.", Toast.LENGTH_LONG).show();
                    mobilenum.setText("");
                    return;
                }

                final dataholder obdataholder = new dataholder(name,dob,mob,bloodgroup,disease1,disease2,disease3,disease4,allergy1,allergy2,allergy3,symptom1,symptom2,symptom3);


                reff = FirebaseDatabase.getInstance().getReference();

                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(adnum)){
                            Toast.makeText(Registration.this,"Patient with this aadhaar number has already registered Please scan your card",Toast.LENGTH_SHORT).show();
                            fullname.setText("");
                            bgroup.setText("");
                            adhaarnum.setText("");
                            mobilenum.setText("");
                            dateob.setText("");
                            disease1et.setText("");
                            disease2et.setText("");
                            disease3et.setText("");
                            disease4et.setText("");
                            allergy1et.setText("");
                            allergy2et.setText("");
                            allergy3et.setText("");
                            sym1et.setText("");
                            sym2et.setText("");
                            sym3et.setText("");
                        }
                        else{
                            reff.child(adnum).setValue(obdataholder);
                            fullname.setText("");
                            bgroup.setText("");
                            adhaarnum.setText("");
                            mobilenum.setText("");
                            dateob.setText("");
                            disease1et.setText("");
                            disease2et.setText("");
                            disease3et.setText("");
                            disease4et.setText("");
                            allergy1et.setText("");
                            allergy2et.setText("");
                            allergy3et.setText("");
                            sym1et.setText("");
                            sym2et.setText("");
                            sym3et.setText("");

                            Toast.makeText(Registration.this, "New Patient's data added successfully", Toast.LENGTH_LONG).show();
                            Intent regscreen = new Intent(Registration.this, qrcode.class);
                            regscreen.putExtra("adhaarnum", adnum);
                            startActivity(regscreen);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });
    }

    private void showdatepickup() {

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        month = month+1;
        String dobpatient = day+"/"+month+"/"+year;
        dateob.setText(dobpatient);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String bg = parent.getItemAtPosition(position).toString();
        bgroup.setText(bg);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}