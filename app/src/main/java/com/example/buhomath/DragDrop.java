package com.example.buhomath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class DragDrop extends AppCompatActivity {
    MediaPlayer correc,incorrect;
    Button verificar;
    ImageView b1, b2;
    LinearLayout dropzone1, dropzone2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_drop);

        correc = MediaPlayer.create(this, R.raw.correcto);
        incorrect = MediaPlayer.create(this,R.raw.incorrecto);
        b1 = findViewById(R.id.B1);
        b2 = findViewById(R.id.B2);
        dropzone1 = findViewById(R.id.DropZone1);
        dropzone2 = findViewById(R.id.DropZone2);
        verificar = findViewById(R.id.btnverificar);

        b1.setOnTouchListener(new MyTouchListener());
        b2.setOnTouchListener(new MyTouchListener());
        dropzone1.setOnDragListener(new MyDragListener(dropzone1));
        dropzone2.setOnDragListener(new MyDragListener(dropzone2));

        verificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDragAndDrop(null, shadowBuilder, view, 0);
                return true;
            }
            return false;
        }
    }

    private class MyDragListener implements View.OnDragListener {
        private LinearLayout targetLayout;

        public MyDragListener(LinearLayout targetLayout) {
            this.targetLayout = targetLayout;
        }

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            ImageView draggedView = (ImageView) event.getLocalState();

            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    return true;
                case DragEvent.ACTION_DRAG_ENTERED:
                    targetLayout.setBackgroundColor(Color.GREEN);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    targetLayout.setBackgroundColor(Color.DKGRAY);
                    break;
                case DragEvent.ACTION_DROP:
                    ViewGroup newParent = (ViewGroup) v;

                    // Check if the target layout already has a child
                    if (newParent.getChildCount() > 0) {
                        // Remove the existing child (prevents selecting the same answer twice)
                        newParent.removeAllViews();
                    }

                    // Make a copy of the dragged image
                    ImageView newImageView = new ImageView(DragDrop.this);
                    newImageView.setImageDrawable(draggedView.getDrawable());
                    newImageView.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    ));

                    newParent.addView(newImageView);
                    draggedView.setVisibility(View.INVISIBLE); // Hide the original dragged image
                    targetLayout.setBackgroundColor(Color.DKGRAY);
                    break;

                case DragEvent.ACTION_DRAG_ENDED:
                    targetLayout.setBackgroundColor(Color.DKGRAY);
                    break;
                default:
                    break;
            }
            return true;
        }
    }

    private void checkAnswer() {
        boolean isCorrect = false;
        //preferencias del usuario
        SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        String usuario = preferences.getString("usuario", "");
        ImageView droppedImageView1 = (ImageView) dropzone1.getChildAt(0);
        ImageView droppedImageView2 = (ImageView) dropzone2.getChildAt(0);

        if (droppedImageView1 != null && droppedImageView2 != null) {
            if (droppedImageView1.getDrawable().getConstantState().equals(b1.getDrawable().getConstantState()) &&
                    droppedImageView2.getDrawable().getConstantState().equals(b2.getDrawable().getConstantState())) {
                isCorrect = true;
            }
        }

        if (isCorrect) {
            ImageView c = new ImageView(getApplicationContext());
            c.setImageResource(R.drawable.correcto);
            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(c);
            toast.show();
            correc.start();
            actualizarPuntajeEnServidor(usuario);
        } else {
            ImageView i = new ImageView(getApplicationContext());
            i.setImageResource(R.drawable.incorrecto);
            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(i);
            toast.show();
            incorrect.start();
            recreate();
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
                params.put("nuevo_puntaje", "9"); // Aquí se establece el nuevo puntaje
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public void menu(View vie){
        Intent menu = new Intent(this, MainActivity.class);
        startActivity(menu);
    }
}