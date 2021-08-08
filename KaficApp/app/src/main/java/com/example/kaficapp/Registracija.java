package com.example.kaficapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kaficapp.SQL.DatabaseHelper;

public class Registracija extends AppCompatActivity {

    DatabaseHelper databaseHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registracija);

        Button buttonRegistracija = findViewById(R.id.buttonRegistracija);

        buttonRegistracija.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextIme = findViewById(R.id.editTextImeRegistracija);
                EditText editTextBrojTelefona = findViewById(R.id.editTextBrojTelefona);
                EditText editTextLozinka = findViewById(R.id.editTextLozinkaRegistracija);

                String ime = editTextIme.getText().toString();
                String telefon = editTextBrojTelefona.getText().toString();
                String lozinka = editTextLozinka.getText().toString();

                User user = new User();
                user.setName(ime);
                user.setPhone(telefon);
                user.setPassword(lozinka);

                databaseHelper.insertUser(user);
                Toast.makeText(Registracija.this, "Uspešno kreiran nalog!", Toast.LENGTH_SHORT).show();
                changeActivityOnMainActivity();
            }
        });

    }

    private void changeActivityOnMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

}