package com.example.mobile_computing;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.registor_form.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button submitButton;
    private EditText name, email$, password$, phone$, province$, status$, gender$, birthdate$;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private TextView textView;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), dataForm.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        textView = findViewById(R.id.regLogin);

        submitButton=findViewById(R.id.submitButton);
        name=findViewById(R.id.userName);
        email$=findViewById(R.id.email);
        password$ = findViewById(R.id.password);
        phone$=findViewById(R.id.phone);
        province$=findViewById(R.id.province);
        status$=findViewById(R.id.status);
        gender$=findViewById(R.id.gender);
        birthdate$=findViewById(R.id.birthdate);
        progressBar = findViewById(R.id.progressBar);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LogIn_Form.class);
                startActivity(intent);
                finish();
            }
        });

        birthdate$.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            openDialog();
        }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email1, password1;
                email1=String.valueOf(email$.getText());
                password1=String.valueOf(password$.getText());
                //String username = name.getText().toString();
//                String phone = phone$.getText().toString();
//                String province = province$.getText().toString();
//                String status = status$.getText().toString();
//                String gender = gender$.getText().toString();
//                String birthdate = birthdate$.getText().toString();

                if (TextUtils.isEmpty(email1)) {
                    Toast.makeText(MainActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password1)) {
                    Toast.makeText(MainActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                mAuth.createUserWithEmailAndPassword(email1, password1)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Account Created.",
                                            Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), LogIn_Form.class);
                                        startActivity(intent);
                                        finish();
                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();


                                }
                            }
                        });



//                intent.putExtra("keyname", username);
//                intent.putExtra("keyemail", email);
//                intent.putExtra("keyphone", phone);
//                intent.putExtra("keyprovince", province);
//                intent.putExtra("keystatus", status);
//                intent.putExtra("keygender", gender);
//                intent.putExtra("keybirthdate", birthdate);

            }
        });

    }

    private void openDialog() {
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                birthdate$.setText(String.valueOf(year) +"."+String.valueOf(month)+"."+String.valueOf(day));
            }
        }, 2000, 0, 1);

        // Set the maximum date to the current date
        dialog.getDatePicker().setMaxDate(System.currentTimeMillis());

        dialog.show();
    }
}