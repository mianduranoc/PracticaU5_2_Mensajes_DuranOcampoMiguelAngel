package com.example.miguelangel.practicau5_2_mensajeauto_duranocampomiguelangel;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BaseDatos extends SQLiteOpenHelper {

    public BaseDatos(Context context,String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE AUTOS (ID INTEGER PRIMARY KEY AUTOINCREMENT,MARCA VARCHAR(255), MODELO VARCHAR(255),PRECIO_INICIAL VARCHAR(50),PRECIO_FINAL VARCHAR(50));");
        db.execSQL("INSERT INTO AUTOS VALUES (NULL,'HYUNDAI','SANTAFE',540000,690000)");
        db.execSQL("INSERT INTO AUTOS VALUES (NULL,'HYUNDAI','ELANTRA',290000,379000)");
        db.execSQL("INSERT INTO AUTOS VALUES (NULL,'KIA','FORTE',261000,363000)");
        db.execSQL("INSERT INTO AUTOS VALUES (NULL,'KIA','SPORTAGE',372400,514000)");
        db.execSQL("INSERT INTO AUTOS VALUES (NULL,'TOYOTA','COROLLA',261600,385000)");
        db.execSQL("INSERT INTO AUTOS VALUES (NULL,'TOYOTA','AVANZA',239700,274700)");
        db.execSQL("INSERT INTO AUTOS VALUES (NULL,'HONDA','CIVIC',335000,440000)");
        db.execSQL("INSERT INTO AUTOS VALUES (NULL,'HONDA','CR-V',415000,525000)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
