package com.example.kaficapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kaficapp.SQL.DatabaseHelper;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    EditText etIme;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonPrijaviSe = findViewById(R.id.buttonPrijaviSe);
        Button buttonRegistrujSe = findViewById(R.id.buttonRegistrujSe);

        buttonPrijaviSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etIme = findViewById(R.id.editTextIme);
                etPassword = findViewById(R.id.editTextLozinka);

                String ime = etIme.getText().toString();
                String lozinka = etPassword.getText().toString();

               String password =  databaseHelper.searhPass(ime);
               if(lozinka.equals(password)){
                   Intent i = new Intent(MainActivity.this, Pocetna.class);
                   i.putExtra("Ime", ime);
                   startActivity(i);
               }
               else {
                   Toast.makeText(MainActivity.this, "Greška!", Toast.LENGTH_SHORT).show();
               }
            }
        });

        buttonRegistrujSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivityOnRegistration();
            }
        });

    }


    private void changeActivityOnRegistration() {
        Intent i = new Intent(this, Registracija.class);
        startActivity(i);
        finish();
    }
}
