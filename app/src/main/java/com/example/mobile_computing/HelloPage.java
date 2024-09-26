package com.example.mobile_computing;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import com.example.registor_form.R;

public class HelloPage extends AppCompatActivity {
    private TextView clickText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hello_page);
        clickText = findViewById(R.id.click);

        clickText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HelloPage.this, LogIn_Form.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.scale_in, R.anim.scale_out);
                overridePendingTransition(500, 0);
            }
        });

    }
}