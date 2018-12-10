package com.example.miguelangel.practicau5_2_mensajeauto_duranocampomiguelangel;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    EditText marca,modelo,precioinicial,preciofinal;
    ListView lista;
    Button guardar,actualizar,eliminar;
    LinearLayout layo;
    int id_final;
    Auto auto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        marca=findViewById(R.id.marca);
        modelo=findViewById(R.id.modelo);
        precioinicial=findViewById(R.id.precioinicial);
        preciofinal=findViewById(R.id.preciofinal);
        lista=findViewById(R.id.listaAutos);
        auto=new Auto(Main2Activity.this);
        layo=findViewById(R.id.layo);
        guardar=findViewById(R.id.guardar);
        actualizar=new Button(Main2Activity.this);
        eliminar=new Button(Main2Activity.this);
        actualizar.setText("Actualizar");
        eliminar.setText("Eliminar");
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (insertar()){
                    Toast.makeText(Main2Activity.this, "SE INSERTO CORRECTAMENTE", Toast.LENGTH_LONG).show();
                    cargarLista();
                    limpiarCampos();
                }
                else {
                    Toast.makeText(Main2Activity.this, "NO SE INSERTO", Toast.LENGTH_LONG).show();
                }

            }
        });
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alerta=new AlertDialog.Builder(Main2Activity.this);
                Auto autoo=new Auto(Main2Activity.this);
                final Auto autos[]=autoo.consultar();
                final int pos=position;

                alerta.setTitle("Detalle de "+autos[pos].marca+" "+autos[pos].modelo)
                        .setMessage("Deseas modificar/eliminar el auto:"+autos[pos].marca+" "+autos[pos].modelo+"?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                layo.removeView(guardar);
                                layo.addView(actualizar,4);
                                layo.addView(eliminar,5);
                                marca.setText(autos[pos].marca);
                                modelo.setText(autos[pos].modelo);
                                precioinicial.setText(autos[pos].precioinicial+"");
                                preciofinal.setText(autos[pos].preciofinal+"");
                                id_final=autos[pos].id;
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("No", null).show();
            }
        });
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Auto actualiza=new Auto(id_final,marca.getText().toString(),modelo.getText().toString(),Float.parseFloat(precioinicial.getText().toString()),Float.parseFloat(preciofinal.getText().toString()));
                if (auto.actualizar(actualiza)){
                    Toast.makeText(Main2Activity.this, "AUTO ACTUALIZADO CORRECTAMENTE", Toast.LENGTH_LONG).show();
                    layo.removeView(actualizar);
                    layo.removeView(eliminar);
                    limpiarCampos();
                    cargarLista();
                    layo.addView(guardar,4);
                }
                else{
                    Toast.makeText(Main2Activity.this, "NO SE PUDO ACTUALIZAR", Toast.LENGTH_LONG).show();
                    layo.removeView(actualizar);
                    layo.removeView(eliminar);
                    limpiarCampos();
                    cargarLista();
                    layo.addView(guardar,4);
                }
            }
        });
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Auto actualiza=new Auto(id_final,marca.getText().toString(),modelo.getText().toString(),Float.parseFloat(precioinicial.getText().toString()),Float.parseFloat(preciofinal.getText().toString()));
                if (auto.eliminar(actualiza)){
                    Toast.makeText(Main2Activity.this, "AUTO ELIMINADO CORRECTAMENTE", Toast.LENGTH_LONG).show();
                    layo.removeView(actualizar);
                    layo.removeView(eliminar);
                    limpiarCampos();
                    cargarLista();
                    layo.addView(guardar,4);
                }
                else{
                    Toast.makeText(Main2Activity.this, "NO SE PUDO ELIMINAR", Toast.LENGTH_LONG).show();
                    layo.removeView(actualizar);
                    layo.removeView(eliminar);
                    limpiarCampos();
                    cargarLista();
                    layo.addView(guardar,4);
                }
            }
        });
    }

    private void cargarLista() {
        Auto[] autos=auto.consultar();
        String vacio[]={};
        ArrayAdapter<String> adap=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,vacio);
        lista.setAdapter(adap);
        if(autos==null){
            Toast.makeText(this,"NO HAY AUTOS REGISTRADOS",Toast.LENGTH_LONG).show();
        }
        else {
            String NoAutos[]=new String[autos.length];
            for (int i=0;i<NoAutos.length;i++){
                NoAutos[i]="Marca:  "+autos[i].marca+"\nModelo: "+autos[i].modelo+"\nPrecio Inicial: "+autos[i].precioinicial+"\nPrecio Inicial: "+autos[i].preciofinal;
            }
            ArrayAdapter <String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,NoAutos);
            lista.setAdapter(adapter);
        }
    }

    private boolean insertar() {
        Auto autos=new Auto(0,marca.getText().toString().toUpperCase(),modelo.getText().toString().toUpperCase(),Float.parseFloat(precioinicial.getText().toString()),Float.parseFloat(preciofinal.getText().toString()));
        return auto.insertar(autos);
    }

    private void limpiarCampos() {
        marca.setText("");
        modelo.setText("");
        preciofinal.setText("");
        precioinicial.setText("");
    }

    protected void onStart(){
        super.onStart();
        cargarLista();
    }
}
