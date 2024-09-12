package com.example.register_form;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.registor_form.R;

public class dataForm extends AppCompatActivity {

    private TextView name, email$, phone$, country$, status$, gender$, birthdate$;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_data_form);

        name = findViewById(R.id.userName);
        email$=findViewById(R.id.email);
        phone$=findViewById(R.id.phone);
        country$=findViewById(R.id.country);
        status$=findViewById(R.id.status);
        gender$=findViewById(R.id.gender);
        birthdate$=findViewById(R.id.birthdate);

        String username = getIntent().getStringExtra("keyname");
        String email = getIntent().getStringExtra("keyemail");
        String phone = getIntent().getStringExtra("keyphone");
        String country = getIntent().getStringExtra("keycountry");
        String status = getIntent().getStringExtra("keystatus");
        String gender = getIntent().getStringExtra("keygender");
        String birthdate = getIntent().getStringExtra("keybirthdate");


        name.setText(username);
        email$.setText(email);
        phone$.setText(phone);
        country$.setText(country);
        status$.setText(status);
        gender$.setText(gender);
        birthdate$.setText(birthdate);

        openDialog();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void openDialog(){
        DialogFragment dialogFragment = new DialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "example");
    }
}