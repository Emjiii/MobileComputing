package com.example.mobile_computing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class dataForm extends AppCompatActivity {

    private FirebaseAuth auth;
    private Button logout_button, continue_button;
    private FirebaseUser currentUser;
    private TextView email$;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_data_form);


        auth = FirebaseAuth.getInstance();
        logout_button = findViewById(R.id.logoutBtn);
        continue_button = findViewById(R.id.continue_Btn);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users");

        currentUser = auth.getCurrentUser();



        if(currentUser == null){
            Intent intent = new Intent(getApplicationContext(), LogIn_Form.class);
            startActivity(intent);
            finish();
        }
        else {
            fetchDataFromFirebase(currentUser.getUid());


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
    private void fetchDataFromFirebase(String uid){
        reference.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    HelperClass userData = snapshot.getValue(HelperClass.class);

                    if (userData != null){
                        TextView usernameTV = findViewById(R.id.dataUserName);
                        TextView provinceTV = findViewById(R.id.dataProvince);
                        TextView emailTV = findViewById(R.id.dataEmail);
                        TextView phoneTV = findViewById(R.id.dataPhone);
                        TextView statusTV = findViewById(R.id.dataStatus);
                        TextView genderTV = findViewById(R.id.dataGender);
                        TextView birthdateTV = findViewById(R.id.dataBirthdate);

                        usernameTV.setText(userData.getUsername());
                        provinceTV.setText(userData.getProvince());
                        emailTV.setText(userData.getEmail());
                        phoneTV.setText(userData.getPhone());
                        statusTV.setText(userData.getStatus());
                        genderTV.setText(userData.getGender());
                        birthdateTV.setText(userData.getBirthdate());
                    }
                    else {
                        Toast.makeText(dataForm.this, "No data found", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(dataForm.this, "Failed to fetch data.", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void openDialog(){
        DialogFragment dialogFragment = new DialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "example");
    }



}