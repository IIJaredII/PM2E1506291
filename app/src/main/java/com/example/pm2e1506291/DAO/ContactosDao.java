package com.example.pm2e1506291.DAO;

import com.example.pm2e1506291.configuracion.ContactosContract;

public class ContactosDao {
    public static String SELECT_ALL="SELECT * FROM "+ ContactosContract.TABLE_NAME;

    public static String SELECT_ALL_ALFABETICO="SELECT * FROM "+
            ContactosContract.TABLE_NAME+ "ORDER BY "+
            ContactosContract.COLUMN_NOMBRE;

    public static String SELECT_ALL_FECHA_CREACION="SELECT * FROM "+
            ContactosContract.TABLE_NAME+ "ORDER BY "+
            ContactosContract.COLUMN_FECHA_CREACION;

}
