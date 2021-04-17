package com.sk.miniprojecttrial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    Button regbtn,scanbtn;
    String adhaarpass;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        scanbtn = (Button)findViewById(R.id.scanbutton);
        // home activity



        regbtn = (Button)findViewById(R.id.regbtn);


        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regscreen = new Intent(MainActivity.this, Registration.class);
                startActivity(regscreen);
            }
        });

        scanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    if(getApplicationContext().checkSelfPermission(Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED)
                    {
                        Intent scanscreen = new Intent(MainActivity.this, Scanqrcode.class);
                        startActivity(scanscreen);
                    }
                    else{
                        requestPermissions(new String[]{Manifest.permission.CAMERA},1);
                    }
                }
                else{
                    Intent scanscreen = new Intent(MainActivity.this, Scanqrcode.class);
                    startActivity(scanscreen);
                }

            }
        });




    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent scanscreen = new Intent(MainActivity.this, Scanqrcode.class);
                startActivity(scanscreen);
            }
            else{
                Toast.makeText(MainActivity.this,"Permission denied",Toast.LENGTH_SHORT).show();
            }
        }
    }

    //                 Intent Adhaarnumer = new Intent(MainActivity.this,Diseases.class);
//                Adhaarnumer.putExtra("adhaarnum", adhaarpass);



//        Button button1 = (Button)findViewById(R.id.btn1);
//        Button button2 = (Button)findViewById(R.id.btn2);
//        Button button3 = (Button)findViewById(R.id.btn3);
//        Button button4 = (Button)findViewById(R.id.btn4);
//
//
//        button1.setOnClickListener(this);
//        button2.setOnClickListener(this);
//        button3.setOnClickListener(this);
//        button4.setOnClickListener(this);
//
//
//    }
//
//
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.btn1:
//                Intent diseases = new Intent(this,Diseases.class);
//                startActivity(diseases);
//
//                Toast.makeText(this,"Diseases",Toast.LENGTH_LONG).show();
//                break;
//
//            case R.id.btn2:
//                Toast.makeText(this,"Allergies",Toast.LENGTH_LONG).show();
//                Intent allergy = new Intent(this,Allergy.class);
//                startActivity(allergy);
//                break;
//
//            case R.id.btn3:
//                Toast.makeText(this,"Symptomes",Toast.LENGTH_LONG).show();
//                Intent symptomes = new Intent(this,Symptomes.class);
//                startActivity(symptomes);
//                break;
//
//            case R.id.btn4:
//                Toast.makeText(this,"Treatements",Toast.LENGTH_LONG).show();
//                Intent treatement = new Intent(this,Treatement.class);
//                startActivity(treatement);
//                break;
//        }
    }



