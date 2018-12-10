package com.example.miguelangel.practicau5_2_mensajeauto_duranocampomiguelangel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class Auto {
    BaseDatos base;
    int id;
    String marca,modelo;
    float precioinicial,preciofinal;
    public Auto(Context activity){
        base=new BaseDatos(activity,"auto",null,1);
    }
    public Auto(int i,String marca,String modelo,float precioinicial,float preciofinal){
        id=i;
        this.marca=marca;
        this.modelo=modelo;
        this.precioinicial=precioinicial;
        this.preciofinal=preciofinal;
    }
    public boolean insertar(Auto auto){
        try{
            SQLiteDatabase db=base.getWritableDatabase();
            ContentValues values=new ContentValues();
            values.put("MARCA",auto.marca);
            values.put("MODELO",auto.modelo);
            values.put("PRECIO_INICIAL",auto.precioinicial);
            values.put("PRECIO_FINAL",auto.preciofinal);
            long resul=db.insert("AUTOS","ID",values);
            if (resul<0)return false;
            return true;
        }catch (SQLiteException e){
            e.printStackTrace();
            return false;
        }
    }
    public Auto[] consultar(){
        try{
            SQLiteDatabase db=base.getReadableDatabase();
            Cursor c=db.rawQuery("SELECT * FROM AUTOS",null);
            if (c.moveToFirst()) {
                Auto[] autos = new Auto[c.getCount()];
                int pos = 0;
                do {
                    autos[pos] = new Auto(c.getInt(0), c.getString(1), c.getString(2), c.getFloat(3),c.getFloat(4));
                    pos++;
                } while (c.moveToNext());
                return autos;
            }
        }catch(SQLiteException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }
    public Auto consultarMarcaModelo(String marca,String modelo){
        try{
            SQLiteDatabase db=base.getReadableDatabase();
            String []datos={marca,modelo};
            Auto autos;
            Cursor c=db.rawQuery("SELECT * FROM AUTOS WHERE MARCA=? AND MODELO=?",datos);
            if (c.moveToFirst()) {
                autos= new Auto(c.getInt(0), c.getString(1), c.getString(2), c.getFloat(3),c.getFloat(4));
                return autos;
            }
        }catch(SQLiteException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }
    public boolean actualizar(Auto dato){
        try{
            SQLiteDatabase db=base.getWritableDatabase();
            ContentValues valores=new ContentValues();
            valores.put("MARCA",dato.marca);
            valores.put("MODELO",dato.modelo);
            valores.put("PRECIO_INICIAL",dato.precioinicial);
            valores.put("PRECIO_FINAL",dato.preciofinal);
            String []campo={dato.id +""};
            long resultado=db.update("AUTOS",valores,"ID=?",campo);
            if (resultado<0)return false;
            return true;
        }catch(SQLiteException e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean eliminar(Auto dato){
        try{
            SQLiteDatabase db=base.getWritableDatabase();
            String[] campo={dato.id+""};
            long resultado=db.delete("AUTOS","ID=?",campo);
            return resultado >= 0;
        }catch(SQLiteException e){
            e.printStackTrace();
            return false;
        }
    }
}
