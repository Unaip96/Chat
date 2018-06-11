package com.example.usuario6i.chat_v2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("sp", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        Button btnEntrar = findViewById(R.id.btnEntrar);
        final TextView tbNick = findViewById(R.id.tbNick);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nick = tbNick.getText().toString();
                if(!nick.equals("")){
                    mostrarConversaciones();
                    editor.putString("nick",nick);
                    editor.commit();
                }
            }
        });
    }
    private void mostrarConversaciones(){
        Intent i = new Intent(this, ConversacionesActivity.class);
        startActivity(i);
    }
}
