package com.example.buhomath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ayudaJuego extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda_juego);
    }

    public void regresar(View vie){
        Intent reg = new Intent(this, juego1.class);
        startActivity(reg);
    }
}