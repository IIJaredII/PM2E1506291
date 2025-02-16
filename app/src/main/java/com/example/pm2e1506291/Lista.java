package com.example.pm2e1506291;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.ImageButton;

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

    ImageButton btnOrdenarLista;
    int ordenActual = 1; // Estado inicial (1 o 2)

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista);

        txtbuscar = findViewById(R.id.txtbuscar);

        listaContactos = findViewById(R.id.listaContactos);
        listaContactos.setLayoutManager(new LinearLayoutManager(this));

        btnOrdenarLista = findViewById(R.id.btnOrdenar);

        // Cargar lista inicial
        cargarLista(ordenActual);

        // Evento para cambiar el orden
        btnOrdenarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Alternar entre 1 y 2
                ordenActual = (ordenActual == 1) ? 2 : 1;

                // Cambiar icono del botón
                if (ordenActual == 1) {
                    btnOrdenarLista.setBackgroundResource(R.drawable.orden_alfabetico);
                } else {
                    btnOrdenarLista.setBackgroundResource(R.drawable.orden_de_creacion);
                }

                // Recargar lista con nuevo orden
                cargarLista(ordenActual);
            }
        });

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

    private void cargarLista(int orden) {
        arrayListContactos = contactosRepository.mostrarContactsos(orden);
        adapter = new listaContactosAdapter(this, arrayListContactos);
        listaContactos.setAdapter(adapter);
    }
}
