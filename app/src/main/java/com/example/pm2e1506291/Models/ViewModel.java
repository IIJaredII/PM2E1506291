package com.example.pm2e1506291.Models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pm2e1506291.Repository.ContactosRepository;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private ContactosRepository contactosRepository;
    private LiveData<List<ContactosModel>> contactosList;

    public ViewModel(@NonNull Application application) {
        super(application);
    }
}
