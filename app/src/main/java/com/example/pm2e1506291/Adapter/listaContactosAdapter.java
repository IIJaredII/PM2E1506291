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
import androidx.recyclerview.widget.RecyclerView;

import com.example.pm2e1506291.Acciones;
import com.example.pm2e1506291.Funciones.imageUtils;
import com.example.pm2e1506291.Models.ContactosModel;
import com.example.pm2e1506291.R;
import com.example.pm2e1506291.Repository.ContactosRepository;
import com.example.pm2e1506291.Repository.PaisesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class listaContactosAdapter extends RecyclerView.Adapter<listaContactosAdapter.ViewHolder> {

    private Context context;
    private PaisesRepository paises;
    private ArrayList<ContactosModel> listaContactos;
    private ArrayList<ContactosModel> listaorg;


    public listaContactosAdapter(Context context, ArrayList<ContactosModel> listaContactos) {
        this.context = context;
        this.listaContactos = listaContactos;
        this.paises = new PaisesRepository(context);
        this.listaorg = new ArrayList<>();
        listaorg.addAll(listaContactos);
    }

    @NonNull
    @Override
    public listaContactosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_contacto,parent,false);
        return new ViewHolder(view);
    }

    public  void filtrado(String txtbuscar){
        int longitud = txtbuscar.length();
        if (longitud == 0) {
            listaContactos.clear();
            listaContactos.addAll(listaorg);
        } else if (!txtbuscar.isEmpty()) {
            List<ContactosModel> coleccion = listaContactos.stream()
                    .filter(i -> i.getNombre().toLowerCase().contains(txtbuscar.toLowerCase()))
                    .collect(Collectors.toList());
            listaContactos.clear();
            listaContactos.addAll(coleccion);
        } else {
            for(ContactosModel c: listaorg) {
            if(c.getNombre().toLowerCase().contains(txtbuscar.toLowerCase())){
                listaContactos.add(c);
            }
            }
        }
        notifyDataSetChanged();

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ContactosModel contacto = listaContactos.get(position);
        holder.txtNombre.setText(contacto.getNombre());
        String codigo = PaisesRepository.obtenerCodigoPorId(contacto.getIdpais());
        holder.txtTelefono.setText("("+codigo+") "+contacto.getNumero());
        if(contacto.getImagen().equals("1")){
            holder.imageviwe.setImageResource(R.drawable.round_account_circle_24);
        }else{
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
