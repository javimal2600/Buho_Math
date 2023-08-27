package com.example.buhomath;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class Login_2 extends AppCompatActivity {

    EditText edtUsuario,edtPassword;
    Button btnLogin;
    String usuario, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        edtUsuario = findViewById(R.id.edtUsuario);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin= findViewById(R.id.btnLogin);
        recuperarPreferencias();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = edtUsuario.getText().toString();
                password = edtPassword.getText().toString();
                if (!usuario.isEmpty() && !password.isEmpty()) {
                    validarUsuario("https://buhomath.000webhostapp.com/validad_usuario.php");
                }else{
                    Toast.makeText(Login_2.this, "No se permiten campos vacios", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void validarUsuario(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("login_exitoso")) {
                            guardarPreferencias();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else if (response.equals("credenciales_incorrectas")) {
                            Toast.makeText(Login_2.this, "Usuario o contraseñas incorrectas", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Login_2.this, "Error en el servidor", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login_2.this, "Error de conexión al servidor", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("usuario", usuario);
                parametros.put("password", password);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void Registro(View vie){
        Intent ayu = new Intent(this, Registro.class);
        startActivity(ayu);
    }

    private void guardarPreferencias(){
        SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("usuario",usuario);
        editor.putString("password",password);
        editor.putBoolean("sesion",true);
        editor.commit();
    }

    private void recuperarPreferencias(){
        SharedPreferences preferences=getSharedPreferences("preferenciasLogin",Context.MODE_PRIVATE);
        edtUsuario.setText(preferences.getString("usuario","micorreo@gmail.com"));
        edtPassword.setText(preferences.getString("password","123456"));
    }

}