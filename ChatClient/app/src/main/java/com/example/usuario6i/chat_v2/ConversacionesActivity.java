package com.example.usuario6i.chat_v2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import javax.xml.datatype.Duration;

public class ConversacionesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversaciones);


        ListView lista = findViewById(R.id.listview);
        ArrayAdapter<String> adaptador;


        adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, new String[]{"Global"});

        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                iniciarConversacion();
            }
        });
    }


    private void iniciarConversacion(){
        Intent i = new Intent(this, ConversacionActivity.class);
        startActivity(i);
    }
}
