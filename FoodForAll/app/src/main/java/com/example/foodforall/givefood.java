package com.example.foodforall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class givefood extends AppCompatActivity {

    EditText name, email, city, phone, date;
    Button submitBtn;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_givefood);

        name   = findViewById(R.id.gname);
        email      = findViewById(R.id.gemail);
        city   = findViewById(R.id.gcity);
        phone      = findViewById(R.id.gphone);
        date = findViewById(R.id.gdate);
        submitBtn   = findViewById(R.id.gsubmit);

        firebaseDatabase = firebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("datauser");

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ggname,ggemail,ggphone,ggcity,ggdate;
                ggname=name.getText().toString().trim();
                ggemail=email.getText().toString().trim();
                ggphone=phone.getText().toString().trim();
                ggcity=city.getText().toString().trim();
                ggdate=date.getText().toString().trim();


                givefoodformat storinginfo = new givefoodformat(ggname, ggphone, ggemail, ggdate, ggcity);

                reference.child(ggname).setValue(storinginfo);
                Toast.makeText(getApplicationContext(), "Data stored Successfully.Needy people will contact you soon", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),home.class);
                startActivity(intent);
                finish();

            }
        });

    }
}