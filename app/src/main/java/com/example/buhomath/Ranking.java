package com.example.buhomath;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Ranking extends AppCompatActivity {

    ListView list1;
    List<UsuarioRanking> usuariosList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        list1 = findViewById(R.id.list1);

        obtenerUsuariosDesdeServidor();
    }

    private void obtenerUsuariosDesdeServidor() {
        String URL = "https://buhomath.000webhostapp.com/obtener_usuarios.php";

        StringRequest request = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String nombre = jsonObject.getString("usu_usuario");
                                int puntaje = jsonObject.getInt("puntaje");

                                UsuarioRanking usuario = new UsuarioRanking(nombre, puntaje);
                                usuariosList.add(usuario);
                            }

                            mostrarUsuariosEnLista();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar el error de conexión
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void mostrarUsuariosEnLista() {
        ArrayList<UsuarioRanking> usuariosArrayList = new ArrayList<>(usuariosList);
        Adaptador adaptador = new Adaptador(this, usuariosArrayList);
        list1.setAdapter(adaptador);

        for (UsuarioRanking usuario : usuariosArrayList) {
            String nombre = usuario.getNombre();
            Integer puntaje = usuario.getPuntaje();
            if (puntaje == null) {
                puntaje = 0; // Otra opción es mostrar un mensaje o valor diferente para representar nulos
            }
            Log.d("Usuario", "Nombre: " + nombre + ", Puntaje: " + puntaje);
        }
    }


    public void Iincio(View vie){
        Intent ayu = new Intent(this, MainActivity.class);
        startActivity(ayu);
    }
}