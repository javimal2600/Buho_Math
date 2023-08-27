package com.example.buhomath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ayuda2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda2);
    }

    public void Incio(View vie){
        Intent ayu = new Intent(this, MainActivity.class);
        startActivity(ayu);
    }

}