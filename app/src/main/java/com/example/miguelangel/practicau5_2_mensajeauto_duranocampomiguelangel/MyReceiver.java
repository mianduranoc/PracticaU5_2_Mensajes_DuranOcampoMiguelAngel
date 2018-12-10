package com.example.miguelangel.practicau5_2_mensajeauto_duranocampomiguelangel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Bundle extras=intent.getExtras();
        Object pdus[]=(Object[])extras.get("pdus");
        SmsMessage mensaje=SmsMessage.createFromPdu((byte[])pdus[0]);
        //Toast.makeText(context, "TELEFONO ORIGEN: "+mensaje.getOriginatingAddress()+" CONTENIDO: "+mensaje.getMessageBody(), Toast.LENGTH_LONG).show();
        String texto=mensaje.getMessageBody();
        if (texto.startsWith("AUTO")){
            String []partes=texto.split(" ");
            if (partes.length==3){
                Auto auto=new Auto(context);
                Auto obtener=auto.consultarMarcaModelo(partes[1],partes[2]);
                if (obtener!=null)enviarSMS(mensaje.getOriginatingAddress(),"EL AUTOMOVIL "+partes[1]+" "+partes[2]+" TIENE UN COSTO DE "+obtener.precioinicial+" A "+obtener.preciofinal,context);
                //else enviarSMS(mensaje.getOriginatingAddress(),"NO SE ENCONTRO EL AUTOMOVIL",context);
            }
            /*else{
                enviarSMS(mensaje.getOriginatingAddress(),"FORMATO INCORRECTO",context);
            }*/
        }
       /* else{
            enviarSMS(mensaje.getOriginatingAddress(),"EL MENSAJE NO TIENE EL FORMATO CORRECTO",context);
        }*/
    }
    private void enviarSMS(String numero, String mensaje, Context context) {
        try{
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(numero,null,mensaje,null,null);
            Toast.makeText(context, "SE ENVIO RESPUESTA", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
