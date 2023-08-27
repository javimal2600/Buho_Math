package com.example.buhomath;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;
import java.util.Map;

public class juego1 extends AppCompatActivity {

    int count=0, fl1=0, fl2=0, fl3=0, fl4=0, fl5=0, fl6=0, fl7=0, fl8=0;
    TextView score, msj;
    Button btn;
    MediaPlayer correc,incorrect;
    ImageButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego1);


        correc = MediaPlayer.create(this, R.raw.correcto);
        incorrect = MediaPlayer.create(this,R.raw.incorrecto);
        score=(TextView) findViewById(R.id.score);
        msj=(TextView) findViewById(R.id.msj1);
        btn=(Button) findViewById(R.id.btn_1);
        btn1=(ImageButton) findViewById(R.id.imageButton);
        btn2=(ImageButton) findViewById(R.id.imageButton2);
        btn3=(ImageButton) findViewById(R.id.imageButton3);
        btn4=(ImageButton) findViewById(R.id.imageButton4);
        btn5=(ImageButton) findViewById(R.id.imageButton5);
        btn6=(ImageButton) findViewById(R.id.imageButton6);
        btn7=(ImageButton) findViewById(R.id.imageButton7);
        btn8=(ImageButton) findViewById(R.id.imageButton9);
        if(count>=4){
            btn.setEnabled(true);
        }else {
            btn.setEnabled(false);
        }

    }


    public void ayudajuego(View vie){
        Intent ayu = new Intent(this, ayudaJuego.class);
        startActivity(ayu);
    }

    public void menu(View vie){
        Intent menu = new Intent(this, MainActivity.class);
        startActivity(menu);
    }
    public void sig(View vie){
        Intent menu = new Intent(this, juego2.class);
        startActivity(menu);
    }

    public void buena1(View v){
        ImageView pm = new ImageView(getApplicationContext());
        ImageView c = new ImageView(getApplicationContext());
        c.setImageResource(R.drawable.correcto);
        pm.setImageResource(R.drawable.banner);

        Toast toast2 = new Toast(getApplicationContext());
        toast2.setDuration(Toast.LENGTH_SHORT);
        toast2.setView(c);
        Toast toast3 = new Toast(getApplicationContext());
        toast3.setDuration(Toast.LENGTH_SHORT);
        toast3.setView(pm);

        //Toast.makeText(this, "respuesta correcta", Toast.LENGTH_SHORT).show();
        fl1=fl1+1;
        if(fl1>=2){
            msj.setText("Ya fue seleccionado");
        } else {
            msj.setText("Elige todos los binomios");
            count=count+1;
            score.setText(String.valueOf(count));
            toast2.show();
            correc.start();
        }
        if(count>=4){
            btn.setEnabled(true);
            toast3.show();
            correc.start();
        }else {
            btn.setEnabled(false);
        }
        actualizarPuntajeEnServidor(count); // Llama a la función de actualización
    }

    public void buena2(View v){
        ImageView pm = new ImageView(getApplicationContext());
        ImageView c = new ImageView(getApplicationContext());
        c.setImageResource(R.drawable.correcto);
        pm.setImageResource(R.drawable.banner);

        Toast toast2 = new Toast(getApplicationContext());
        toast2.setDuration(Toast.LENGTH_SHORT);
        toast2.setView(c);
        Toast toast3 = new Toast(getApplicationContext());
        toast3.setDuration(Toast.LENGTH_SHORT);
        toast3.setView(pm);

        fl2=fl2+1;
        score.setText(String.valueOf(count));
        if(fl2>=2){
            msj.setText("Ya fue seleccionado");
        } else {
            msj.setText("Elige todos los binomios");
            count=count+1;
            score.setText(String.valueOf(count));
            toast2.show();
            correc.start();
        }
        if(count>=4){
            btn.setEnabled(true);
            toast3.show();
            correc.start();
        }else {
            btn.setEnabled(false);
        }
        actualizarPuntajeEnServidor(count); // Llama a la función de actualización

    }

    public void buena3(View v){
        ImageView pm = new ImageView(getApplicationContext());
        ImageView c = new ImageView(getApplicationContext());
        c.setImageResource(R.drawable.correcto);
        pm.setImageResource(R.drawable.banner);

        Toast toast2 = new Toast(getApplicationContext());
        toast2.setDuration(Toast.LENGTH_SHORT);
        toast2.setView(c);
        Toast toast3 = new Toast(getApplicationContext());
        toast3.setDuration(Toast.LENGTH_SHORT);
        toast3.setView(pm);

        fl3=fl3+1;
        if(fl3>=2){
            msj.setText("Ya fue seleccionado");
        } else {
            msj.setText("Elige todos los binomios");
            count=count+1;
            score.setText(String.valueOf(count));
            toast2.show();
            correc.start();
        }
        if(count>=4){
            btn.setEnabled(true);
            toast3.show();
            correc.start();
        }else {
            btn.setEnabled(false);
        }
        actualizarPuntajeEnServidor(count); // Llama a la función de actualización

    }

    public void buena4(View v){
        ImageView pm = new ImageView(getApplicationContext());
        ImageView c = new ImageView(getApplicationContext());
        c.setImageResource(R.drawable.correcto);
        pm.setImageResource(R.drawable.banner);

        Toast toast2 = new Toast(getApplicationContext());
        toast2.setDuration(Toast.LENGTH_SHORT);
        toast2.setView(c);
        Toast toast3 = new Toast(getApplicationContext());
        toast3.setDuration(Toast.LENGTH_SHORT);
        toast3.setView(pm);

        fl4=fl4+1;
        score.setText(String.valueOf(count));
        if(fl4>=2){
            msj.setText("Ya fue seleccionado");
        } else {
            msj.setText("Elige todos los binomios");
            count=count+1;
            score.setText(String.valueOf(count));
            toast2.show();
            correc.start();
        }
        if(count>=4){
            btn.setEnabled(true);
            toast3.show();
            correc.start();
        }else {
            btn.setEnabled(false);
        }
        actualizarPuntajeEnServidor(count); // Llama a la función de actualización

    }

    public void mala1(View v){
        ImageView i = new ImageView(getApplicationContext());
        i.setImageResource(R.drawable.incorrecto);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(i);

        fl5=fl5+1;
        if(fl5>=2){
            msj.setText("Ya fue seleccionado");
        } else {
            msj.setText("Elige todos los binomios");
            toast.show();
            incorrect.start();
        }
    }

    public void mala2(View v){
        ImageView i = new ImageView(getApplicationContext());
        i.setImageResource(R.drawable.incorrecto);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(i);
        fl6=fl6+1;
        if(fl6>=2){
            msj.setText("Ya fue seleccionado");
        } else {
            msj.setText("Elige todos los binomios");
            toast.show();
            incorrect.start();
        }
    }

    public void mala3(View v){
        ImageView i = new ImageView(getApplicationContext());
        i.setImageResource(R.drawable.incorrecto);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(i);
        fl7=fl7+1;
        if(fl7>=2){
            msj.setText("Ya fue seleccionado");
        } else {
            msj.setText("Elige todos los binomios");
            toast.show();
            incorrect.start();
        }
    }

    public void mala4(View v){
        ImageView i = new ImageView(getApplicationContext());
        i.setImageResource(R.drawable.incorrecto);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(i);
        fl8=fl8+1;
        if(fl8>=2){
            msj.setText("Ya fue seleccionado");
        } else {
            msj.setText("Elige todos los binomios");
            toast.show();
            incorrect.start();
        }
    }

    private void actualizarPuntajeEnServidor(int nuevoPuntaje) {
        SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        String usuario = preferences.getString("usuario", ""); // Recupera el nombre de usuario del usuario logeado

        if (!usuario.isEmpty()) {
            String URL = "https://buhomath.000webhostapp.com/actualizar_puntaje.php";
            StringRequest request = new StringRequest(Request.Method.POST, URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("actualizacion_exitosa")) {
                                // Puntaje actualizado en el servidor
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
                    params.put("nuevo_puntaje", String.valueOf(nuevoPuntaje));
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(request);
        }
    }

}