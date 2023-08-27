package com.example.buhomath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    Button cerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        cerrar = findViewById(R.id.salir);

        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences=getSharedPreferences("preferenciasLlogin", Context.MODE_PRIVATE);
                preferences.edit().clear().commit();

                Intent intent=new Intent(getApplicationContext(),Login_2.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /*public void finalizar(View v){
        finish();
    }
    public void ayuda(View vie){
        Intent ayu = new Intent(this, ayuda.class);
        startActivity(ayu);
    }*/


    public void juego(View vie){
        Intent juego = new Intent(this, juego1.class);
        startActivity(juego);
    }

    public void rank(View vie){
        Intent ayu = new Intent(this, Ranking.class);
        startActivity(ayu);
    }

    public void ayuda(View vie){
        Intent ayu = new Intent(this, ayuda.class);
        startActivity(ayu);
    }
}