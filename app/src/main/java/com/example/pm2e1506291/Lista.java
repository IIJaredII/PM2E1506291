package com.example.pm2e1506291;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pm2e1506291.Adapter.listaContactosAdapter;
import com.example.pm2e1506291.Models.ContactosModel;
import com.example.pm2e1506291.Repository.ContactosRepository;

import java.util.ArrayList;

public class Lista extends AppCompatActivity implements  SearchView.OnQueryTextListener {
    ContactosRepository contactosRepository = new ContactosRepository(this);
    RecyclerView listaContactos;
    ArrayList<ContactosModel> arrayListContactos;
    listaContactosAdapter adapter;
   SearchView txtbuscar;

    Button btnOrdenarLista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista);

        txtbuscar = findViewById(R.id.txtbuscar);
        listaContactos = findViewById(R.id.listaContactos);
        listaContactos.setLayoutManager(new LinearLayoutManager(this));
        arrayListContactos = contactosRepository.mostrarContactsos(1);
        adapter = new listaContactosAdapter(this,arrayListContactos);
        listaContactos.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    txtbuscar.setOnQueryTextListener(this);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filtrado(newText);
        return false;
    }
}