package com.example.kaficapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Pocetna extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetna);

        String imeZaPrikaz = getIntent().getStringExtra("Ime");

        TextView etPrikazImena = findViewById(R.id.tVPrikazImena);
        etPrikazImena.setText(imeZaPrikaz);

        Button buttonOdjaviSe = findViewById(R.id.buttonOdjaviSe);
        Button buttonListaPica = findViewById(R.id.buttonListaPica);

        buttonOdjaviSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivityOnMainActivity();
            }
        });
        
        buttonListaPica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivityOnDrinkMenu();
            }
        });
    }

    private void changeActivityOnMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void changeActivityOnDrinkMenu() {
        Intent i = new Intent(this, ListaPica.class);
        startActivity(i);
        finish();
    }
}