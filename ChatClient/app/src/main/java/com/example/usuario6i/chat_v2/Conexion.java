package com.example.usuario6i.chat_v2;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Unaip on 14/01/2018.
 */

public class Conexion implements Runnable {

    static Activity activity;

    DataOutputStream dos;
    DataInputStream dis;
    final String nick;
    String ip;
    Socket socket;

    public Conexion(Activity activity){
        this.activity = activity;
        SharedPreferences sharedPreferences = activity.getApplicationContext().getSharedPreferences("sp", Context.MODE_PRIVATE);
        nick = sharedPreferences.getString("nick","");
    }
    @Override
    public void run() {
        socket = new Socket();
        InetSocketAddress addr = new InetSocketAddress("10.0.2.2",1234);
        try {
            socket.connect(addr);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());

            ip = socket.getLocalSocketAddress().toString();

            while(true){

                String mensajeRecibido = dis.readUTF();
                Log.i("Recibido", mensajeRecibido);
                Mensaje mensaje = new Mensaje(mensajeRecibido);
                cambiarVista(mensaje);

            }

        } catch (IOException e) {
            e.printStackTrace();
            cerrar();
        }


    }

    public void cerrar(){
        try {
            socket.close();
            dis.close();
            dos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void enviarMensaje(final String strEnviar){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF(strEnviar);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void cambiarVista(final Mensaje mensaje){
        if(!mensaje.getMensaje().equals(null)){
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    final ArrayList<String> mensajes = new ArrayList<String>();
                    final ListView listView = activity.findViewById(R.id.listMensajes);
                    ListAdapter auxAdapter = listView.getAdapter();
                    for ( int i = 0 ; i < listView.getCount() ; i++){
                        mensajes.add(listView.getItemAtPosition(i).toString());
                    }
                    String ultimoMensaje ="";
                    if(!mensaje.getIp().equals(ip))
                        ultimoMensaje= mensaje.getNick()+": ";
                    ultimoMensaje+=mensaje.getMensaje();
                    Log.i("IP",mensaje.getIp());
                    Log.i("Mensaje",mensaje.getMensaje());
                    Log.i("UltimoMensaje","hola");
                    mensajes.add(ultimoMensaje);

                    StableArrayAdapter adapter = new StableArrayAdapter(activity,
                            android.R.layout.simple_list_item_1, mensajes);

                    listView.setAdapter(null);
                    listView.setAdapter(adapter);
                }
            });
        }

    }
}