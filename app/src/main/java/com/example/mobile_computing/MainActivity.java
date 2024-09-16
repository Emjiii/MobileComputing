package com.example.mobile_computing;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.registor_form.R;

public class MainActivity extends AppCompatActivity {
    private Button submitButton;
    private TextView name, email$, phone$, province$, status$, gender$, birthdate$;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        submitButton=findViewById(R.id.submitButton);
        name=findViewById(R.id.userName);
        email$=findViewById(R.id.email);
        phone$=findViewById(R.id.phone);
        province$=findViewById(R.id.province);
        status$=findViewById(R.id.status);
        gender$=findViewById(R.id.gender);
        birthdate$=findViewById(R.id.birthdate);

        birthdate$.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            openDialog();
        }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = name.getText().toString();
                String email = email$.getText().toString();
                String phone = phone$.getText().toString();
                String province = province$.getText().toString();
                String status = status$.getText().toString();
                String gender = gender$.getText().toString();
                String birthdate = birthdate$.getText().toString();

                Intent intent = new Intent(MainActivity.this, dataForm.class);
                intent.putExtra("keyname", username);
                intent.putExtra("keyemail", email);
                intent.putExtra("keyphone", phone);
                intent.putExtra("keyprovince", province);
                intent.putExtra("keystatus", status);
                intent.putExtra("keygender", gender);
                intent.putExtra("keybirthdate", birthdate);

                startActivity(intent);
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