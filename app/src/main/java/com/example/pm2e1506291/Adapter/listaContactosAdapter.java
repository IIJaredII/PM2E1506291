package com.example.pm2e1506291.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pm2e1506291.Acciones;
import com.example.pm2e1506291.Funciones.imageUtils;
import com.example.pm2e1506291.Models.ContactosModel;
import com.example.pm2e1506291.R;
import com.example.pm2e1506291.Repository.PaisesRepository;

import java.util.ArrayList;
import java.util.List;

public class listaContactosAdapter extends RecyclerView.Adapter<listaContactosAdapter.ViewHolder> {

    private Context context;
    private PaisesRepository paises;
    private List<ContactosModel> listaContactos = new ArrayList<>();

    public listaContactosAdapter(Context context) {
        this.context = context;
        this.paises = new PaisesRepository(context);
    }

    public void setContactos(List<ContactosModel> contactos) {
        this.listaContactos = contactos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public listaContactosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_contacto, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ContactosModel contacto = listaContactos.get(position);
        holder.txtNombre.setText(contacto.getNombre());
        String codigo = PaisesRepository.obtenerCodigoPorId(contacto.getIdpais());
        holder.txtTelefono.setText("(" + codigo + ") " + contacto.getNumero());

        if (contacto.getImagen().equals("1")) {
            holder.imageviwe.setImageResource(R.drawable.round_account_circle_24);
        } else {
            holder.imageviwe.setImageBitmap(imageUtils.decodeFromBase64(contacto.getImagen()));
        }

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, Acciones.class);
            intent.putExtra("contacto_id", contacto.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtTelefono;
        ImageView imageviwe;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtTelefono = itemView.findViewById(R.id.txtTelefono);
            imageviwe = itemView.findViewById(R.id.ivContactoLista);
        }
    }
}
