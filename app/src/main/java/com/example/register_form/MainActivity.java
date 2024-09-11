package com.example.register_form;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.registor_form.R;

public class MainActivity extends AppCompatActivity {
    private Button submitButton;
    private TextView name, email$, phone$, country$, status$, gender$, birthdate$;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        submitButton=findViewById(R.id.submitButton);
        name=findViewById(R.id.userName);
        email$=findViewById(R.id.email);
        phone$=findViewById(R.id.phone);
        country$=findViewById(R.id.country);
        status$=findViewById(R.id.status);
        gender$=findViewById(R.id.gender);
        birthdate$=findViewById(R.id.birthdate);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = name.getText().toString();
                String email = email$.getText().toString();
                String phone = phone$.getText().toString();
                String country = phone$.getText().toString();
                String status = status$.getText().toString();
                String gender = gender$.getText().toString();
                String birthdate = birthdate$.getText().toString();

                Intent intent = new Intent(MainActivity.this, dataForm.class);
                intent.putExtra("keyname", username);
                intent.putExtra("keyemail", email);
                intent.putExtra("keyphone", phone);
                intent.putExtra("keycountry", country);
                intent.putExtra("keystatus", status);
                intent.putExtra("keygender", gender);
                intent.putExtra("keybirthdate", birthdate);

                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
}