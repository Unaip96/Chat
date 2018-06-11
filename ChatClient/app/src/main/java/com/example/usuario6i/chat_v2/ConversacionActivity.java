package com.example.usuario6i.chat_v2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ConversacionActivity extends AppCompatActivity {


    Conexion conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversacion);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("sp", Context.MODE_PRIVATE);
        final String nick = sharedPreferences.getString("nick","");
        conn  = new Conexion(this);
        Thread hilo = new Thread(conn);
        hilo.start();


        Button btnEnviar = findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText tbMensaje = findViewById(R.id.tbMensaje);
                final String strMensaje = tbMensaje.getText().toString();
                if(!strMensaje.equals("")){

                    Mensaje mensaje = new Mensaje(conn.ip, nick, strMensaje);
                    conn.enviarMensaje(mensaje.toString());

                }
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
