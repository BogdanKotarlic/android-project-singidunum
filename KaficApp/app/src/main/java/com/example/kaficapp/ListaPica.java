package com.example.kaficapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kaficapp.SQL.DatabaseHelper;
import com.example.kaficapp.SQL.DatabaseHelper2;

import org.w3c.dom.Text;

import java.util.List;

public class ListaPica extends AppCompatActivity {

    DatabaseHelper2 databaseHelper2;

    TextView dataList;
    TextView dataList_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pica);

        databaseHelper2 = new DatabaseHelper2(ListaPica.this);

        Button delete = findViewById(R.id.delete_data);
        Button insert = findViewById(R.id.insert_data);
        Button update = findViewById(R.id.update_data);
        Button read = findViewById(R.id.refresh_data);

        dataList = findViewById(R.id.all_data_list);
        dataList_count = findViewById(R.id.data_list_count);

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshData();
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowInputDialog();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowUpdateIdDialog();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteDialog();
            }
        });

    }

    private void refreshData() {
        dataList_count.setText("Broj pića: " + databaseHelper2.brojac());

        List<Pica> picaLista = databaseHelper2.getAllPica();
        dataList.setText("");
        for(Pica pica:picaLista){
            dataList.append("ID: " + pica.getId() + " | Naziv: " + pica.getNaziv() + " | Cena: " + pica.getCena() + "\n\n");
        }
    }

    private void showDeleteDialog() {
        AlertDialog.Builder al = new AlertDialog.Builder(ListaPica.this);
        View view = getLayoutInflater().inflate(R.layout.delete_dialog, null);
        al.setView(view);

        EditText id_input = view.findViewById(R.id.id_input);
        Button delete_btn = view.findViewById(R.id.delete_btn);

        AlertDialog alertDialog = al.show();

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper2.deletePice(id_input.getText().toString());
                alertDialog.dismiss();
                refreshData();
            }
        });
    }

    private void ShowUpdateIdDialog() {
        AlertDialog.Builder al = new AlertDialog.Builder(ListaPica.this);
        View view = getLayoutInflater().inflate(R.layout.update_id_dialog, null);
        al.setView(view);

        EditText id_input = view.findViewById(R.id.id_input);
        Button fetch_button = view.findViewById(R.id.update_id_btn);

        AlertDialog alertDialog = al.show();

        fetch_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDataDialog(id_input.getText().toString());
                alertDialog.dismiss();
                refreshData();
            }
        });
    }

    private void showDataDialog(String id) {
        Pica pica = databaseHelper2.getPice(Integer.parseInt(id));
        AlertDialog.Builder al = new AlertDialog.Builder(ListaPica.this);
        View view = getLayoutInflater().inflate(R.layout.update_dialog, null);

        EditText naziv = view.findViewById(R.id.naziv);
        EditText cena = view.findViewById(R.id.cena);
        Button updateBtn = view.findViewById(R.id.update_btn);

        al.setView(view);

        naziv.setText(pica.getNaziv());
        cena.setText(pica.getCena());

        AlertDialog alertDialog = al.show();

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pica pica = new Pica();
                pica.setNaziv(naziv.getText().toString());
                pica.setId(id);
                pica.setCena(cena.getText().toString());
                databaseHelper2.updatePica(pica);
                alertDialog.dismiss();
                refreshData();
            }
        });
    }

    private void ShowInputDialog() {
        AlertDialog.Builder al = new AlertDialog.Builder(ListaPica.this);
        View view = getLayoutInflater().inflate(R.layout.insert_dialog, null);

        EditText naziv = view.findViewById(R.id.naziv);
        EditText cena = view.findViewById(R.id.cena);
        Button insertBtn = view.findViewById(R.id.insert_btn);

        al.setView(view);

        AlertDialog alertDialog = al.show();

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pica pica = new Pica();
                pica.setNaziv(naziv.getText().toString());
                pica.setCena(cena.getText().toString());
                databaseHelper2.addPice(pica);
                alertDialog.dismiss();
                refreshData();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, Pocetna.class);
        startActivity(i);
        finish();
    }
}