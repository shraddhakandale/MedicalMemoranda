package com.sk.miniprojecttrial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class qrcode extends AppCompatActivity {

    String str;
    TextView getadnumtv,nametxt,dobtxt,bgrptxt,bgrpfix,namefix,dobfix;
    ImageView qrimg;
    Button genbtn;
    LinearLayout ll1;

    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        getadnumtv = (TextView)findViewById(R.id.getadnumtv);
        qrimg = (ImageView)findViewById(R.id.qrimg);
        genbtn = (Button)findViewById(R.id.genqrbutton);
        nametxt = (TextView)findViewById(R.id.nametxt);
        dobtxt = (TextView)findViewById(R.id.dobtxt);
        bgrptxt = (TextView)findViewById(R.id.bgrptxt);
        bgrpfix = (TextView)findViewById(R.id.bgrpfix);
        namefix = (TextView)findViewById(R.id.namefix);
        dobfix = (TextView)findViewById(R.id.dobfix);
        ll1 = (LinearLayout)findViewById(R.id.ll1) ;

        Intent genqrcode = getIntent();
        str = genqrcode.getStringExtra("adhaarnum");
        getadnumtv.setText(str);



        genbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String qrdata = getadnumtv.getText().toString();
                QRGEncoder qrgEncoder = new QRGEncoder(qrdata,null, QRGContents.Type.TEXT,150);

                Bitmap bitmap = qrgEncoder.getBitmap();
                qrimg.setImageBitmap(bitmap);

                qrimg.setVisibility(View.VISIBLE);
                reff = FirebaseDatabase.getInstance().getReference();
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String name = dataSnapshot.child(str).child("name").getValue().toString();
                        String bloodgroup = dataSnapshot.child(str).child("bloodgroup").getValue().toString();
                        String dateofbirth = dataSnapshot.child(str).child("dob").getValue().toString();

                        nametxt.setText(name);
                        bgrptxt.setText(bloodgroup);
                        dobtxt.setText(dateofbirth);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                ll1.setVisibility(View.VISIBLE);

            }

        });

    }
}