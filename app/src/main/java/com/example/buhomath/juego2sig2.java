package com.example.buhomath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class juego2sig2 extends AppCompatActivity {
    MediaPlayer correc,incorrect;
    Spinner nuevospn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego2sig2);

        correc = MediaPlayer.create(this, R.raw.correcto);
        incorrect = MediaPlayer.create(this,R.raw.incorrecto);

        nuevospn=(Spinner) findViewById(R.id.sp);

        String[] resp={"a","b","c","d"};

        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,resp);
        nuevospn.setAdapter(adaptador);

    }

    public void dd(View vie){
        Intent drop = new Intent(this, DragDrop.class);
        startActivity(drop);
    }

    public void ayudajuego(View vie){
        Intent ayu = new Intent(this, ayuda_juego2.class);
        startActivity(ayu);
    }
    public void sig(View vie){
        Intent reg = new Intent(this, MainActivity.class);
        startActivity(reg);
    }
    public void menu(View vie){
        Intent menu = new Intent(this, MainActivity.class);
        startActivity(menu);
    }
    public void res(View v){
        SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        String usuario = preferences.getString("usuario", "");
        ImageView i = new ImageView(getApplicationContext());
        i.setImageResource(R.drawable.incorrecto);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(i);

        ImageView c = new ImageView(getApplicationContext());
        c.setImageResource(R.drawable.correcto);

        Toast toast2 = new Toast(getApplicationContext());
        toast2.setDuration(Toast.LENGTH_SHORT);
        toast2.setView(c);
        String selecionada=nuevospn.getSelectedItem().toString();
        if(selecionada.equals("c")){
            toast2.show();
            correc.start();
            actualizarPuntajeEnServidor(usuario);
            //Toast.makeText(this, String.valueOf("correcto"), Toast.LENGTH_SHORT).show();
        }
        if(selecionada.equals("a"))
        {
            toast.show();
            incorrect.start();
            //Toast.makeText(this, String.valueOf("incorrecto"), Toast.LENGTH_SHORT).show();
        }
        if(selecionada.equals("b"))
        {
            toast.show();
            incorrect.start();
            //Toast.makeText(this, String.valueOf("incorrecto"), Toast.LENGTH_SHORT).show();
        }
        if(selecionada.equals("d"))
        {
            toast.show();
            incorrect.start();
            //Toast.makeText(this, String.valueOf("incorrecto"), Toast.LENGTH_SHORT).show();
        }
    }

    private void actualizarPuntajeEnServidor(String usuario) {
        String URL = "https://buhomath.000webhostapp.com/actualizar_puntaje.php"; // Cambia a la URL correcta
        StringRequest request = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("actualizacion_exitosa")) {
                            // El puntaje se actualizó exitosamente en el servidor
                        } else {
                            // Error en la actualización
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Error en la conexión
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("usu_usuario", usuario);
                params.put("nuevo_puntaje", "7"); // Aquí estableces el nuevo puntaje
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}