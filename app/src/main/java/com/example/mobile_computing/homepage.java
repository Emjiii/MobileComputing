package com.example.mobile_computing;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.registor_form.R;

import java.net.CacheRequest;

public class homepage extends AppCompatActivity {

    CardView calculatorCard, infraredCard, bluetoothCard, fileTransferCard, shareCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_homepage);

        calculatorCard = findViewById(R.id.calculatorSelect);
        infraredCard = findViewById(R.id.infraredSelect);
        bluetoothCard = findViewById(R.id.bluetoothSelect);
        fileTransferCard = findViewById(R.id.fileTransferSelect);
        shareCard = findViewById(R.id.shareSelect);

        calculatorCard.setOnClickListener(view -> {
            Intent intent = new Intent(homepage.this, Calculator.class);
            startActivity(intent);
        });

        infraredCard.setOnClickListener(view -> {
            Intent intent = new Intent(homepage.this, InfraredCommunication.class);
            startActivity(intent);
        });

        bluetoothCard.setOnClickListener(view -> {
            Intent intent = new Intent(homepage.this, Bluetooth.class);
            startActivity(intent);
        });

        fileTransferCard.setOnClickListener(view -> {
            Intent intent = new Intent(homepage.this, fileTransfer.class);
            startActivity(intent);
        });

        shareCard.setOnClickListener(view -> {
            Intent intent = new Intent(homepage.this, ShareFiles.class);
            startActivity(intent);
        });

    }
}