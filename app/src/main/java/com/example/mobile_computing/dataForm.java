package com.example.mobile_computing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.registor_form.R;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class dataForm extends AppCompatActivity {

    private FirebaseAuth auth;
    private Button logout_button, continue_button;
    private FirebaseUser user;
    private TextView email$;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_data_form);

        auth = FirebaseAuth.getInstance();
        logout_button = findViewById(R.id.logoutBtn);
        continue_button = findViewById(R.id.continue_Btn);
        user = auth.getCurrentUser();


//        name = findViewById(R.id.userName);
        email$=findViewById(R.id.email);
//        phone$=findViewById(R.id.phone);
//        province$=findViewById(R.id.province);
//        status$=findViewById(R.id.status);
//        gender$=findViewById(R.id.gender);
//        birthdate$=findViewById(R.id.birthdate);

//        String username = getIntent().getStringExtra("keyname");
//        String email = getIntent().getStringExtra("keyemail");
//        String phone = getIntent().getStringExtra("keyphone");
//        String country = getIntent().getStringExtra("keyprovince");
//        String status = getIntent().getStringExtra("keystatus");
//        String gender = getIntent().getStringExtra("keygender");
//        String birthdate = getIntent().getStringExtra("keybirthdate");

        if(user == null){
            Intent intent = new Intent(getApplicationContext(), LogIn_Form.class);
            startActivity(intent);
            finish();
        }
        else {
            //name.setText(username);
            email$.setText(user.getEmail());
            //email$.setText(email);
//            phone$.setText(phone);
//            province$.setText(country);
//            status$.setText(status);
//            gender$.setText(gender);
//            birthdate$.setText(birthdate);
        }

        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Calculator.class);
                startActivity(intent);
            }
        });

        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LogIn_Form.class);
                startActivity(intent);
                finish();
            }
        });

        //openDialog();

    }

    private void openDialog(){
        DialogFragment dialogFragment = new DialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "example");
    }
}