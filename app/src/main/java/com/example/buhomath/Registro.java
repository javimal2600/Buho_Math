package com.example.buhomath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Registro extends AppCompatActivity {
    EditText edtNuevoUsuario, edtNuevaPassword;
    Button btnRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        edtNuevoUsuario = findViewById(R.id.edtNuevoUsuario);
        edtNuevaPassword = findViewById(R.id.edtNuevaPassword);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nuevoUsuario = edtNuevoUsuario.getText().toString();
                String nuevaPassword = edtNuevaPassword.getText().toString();

                if (nuevoUsuario.isEmpty() || nuevaPassword.isEmpty()) {
                    Toast.makeText(Registro.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    // Crea una solicitud HTTP para registrar el nuevo usuario
                    registrarUsuario(nuevoUsuario, nuevaPassword);
                }
            }
        });
    }

    private void registrarUsuario(final String usuario, final String password) {
        String URL = "https://buhomath.000webhostapp.com/insertar_usuario.php";
        StringRequest request = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("registro_exitoso")) {
                            Toast.makeText(Registro.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                            // Redirige al usuario a la pantalla de inicio de sesión u otra actividad
                        } else if (response.equals("usuario_existente")) {
                            Toast.makeText(Registro.this, "El usuario ya existe", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Registro.this, "Error en el registro", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Registro.this, "Error en la conexión", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("usu_usuario", usuario);
                params.put("usu_password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public void Login(View vie){
        Intent ayu = new Intent(this, Login_2.class);
        startActivity(ayu);
    }
}