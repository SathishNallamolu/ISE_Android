package com.example.foodforall;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    EditText mEmail,mPassword;
    Button mLoginBtn;
    TextView mCreateBtn,forgotTextLink;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    Button signupbutton, loginbutton;
    TextInputLayout email_var, pass_var;

//    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email_var = findViewById(R.id.Email);
        pass_var = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();
//        mLoginBtn = findViewById(R.id.Btn1);
        loginbutton = findViewById(R.id.Btn1);
        mCreateBtn = findViewById(R.id.createText);
//        forgotTextLink = findViewById(R.id.forgotPassword);



//        email_var = findViewById(R.id.Email);
//        pass_var = findViewById(R.id.password);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //every thing we need to convert into string
                String email = email_var.getEditText().getText().toString();
                String pass = pass_var.getEditText().getText().toString();

                if(!email.isEmpty()){
                    email_var.setError(null);
                    email_var.setErrorEnabled(false);
                    if(!pass.isEmpty()){
                        pass_var.setError(null);
                        pass_var.setErrorEnabled(false);

                        final String email_data = email_var.getEditText().getText().toString();
                        final String pass_data = pass_var.getEditText().getText().toString();

                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = firebaseDatabase.getReference("datauser");

                        Query check_email = databaseReference.orderByChild("email").equalTo(email_data);
                        check_email.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()){
                                    email_var.setError(null);
                                    email_var.setErrorEnabled(false);
                                    String passcheck = snapshot.child("fullname").child("password").getValue(String.class);
                                    if (passcheck.equals(pass_data)){
                                        pass_var.setError(null);
                                        pass_var.setErrorEnabled(false);
                                        Toast.makeText(getApplicationContext(), "login Successful", Toast.LENGTH_SHORT).show();
                                        Intent intent= new Intent(getApplicationContext(),home.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        pass_var.setError("Wrong password");
                                    }

                                }else {
                                    email_var.setError("Email is not registered");
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }else{
                        pass_var.setError("please enter your password");
                    }
                }else{
                    email_var.setError("Please enter your email");
                }
            }
        });

        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });

 /*       forgotTextLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText resetMail = new EditText(v.getContext());
                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password ?");
                passwordResetDialog.setMessage("Enter Your Email To Received Reset Link.");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // extract the email and send reset link
                        String mail = resetMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Login.this, "Reset Link Sent To Your Email.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this, "Error ! Reset Link is Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // close the dialog
                    }
                });

                passwordResetDialog.create().show();

            }
        });
*/

    }
}