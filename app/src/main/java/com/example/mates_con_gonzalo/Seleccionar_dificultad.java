package com.example.mates_con_gonzalo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Seleccionar_dificultad extends AppCompatActivity {

    String nombre;
    ImageView IVFacil,IVMedio,IVDificil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_dificultad);
        getSupportActionBar().hide();

        IVFacil = findViewById(R.id.imageViewfacil);
        IVMedio = findViewById(R.id.imageViewmedio);
        IVDificil = findViewById(R.id.imageViewdificil);
        IVFacil.setImageResource(R.drawable.sumres);IVFacil.getLayoutParams().width = 300;IVFacil.getLayoutParams().height = 300;
        IVFacil.setAdjustViewBounds(true);
        IVMedio.setImageResource(R.drawable.difmedio);IVMedio.getLayoutParams().width = 300;IVMedio.getLayoutParams().height = 300;
        IVMedio.setAdjustViewBounds(true);
        IVDificil.setImageResource(R.drawable.cronometro);IVDificil.getLayoutParams().width = 300;IVDificil.getLayoutParams().height = 300;
        IVDificil.setAdjustViewBounds(true);

        nombre = getIntent().getStringExtra("nombre");
    }

    public void Facil(View view){
        Intent siguiente = new Intent(this,Juego.class);
        siguiente.putExtra("dificultad","facil");
        siguiente.putExtra("nombre",nombre);
        startActivity(siguiente);
        overridePendingTransition(R.anim.fade_in, android.R.anim.fade_out);
    }

    public void Medio(View view){
        Intent siguiente = new Intent(this,Juego.class);
        siguiente.putExtra("dificultad","medio");
        siguiente.putExtra("nombre",nombre);
        startActivity(siguiente);
        overridePendingTransition(R.anim.fade_in, android.R.anim.fade_out);
    }

    public void Dificil(View view){
        Intent siguiente = new Intent(this,Juego.class);
        siguiente.putExtra("dificultad","dificil");
        siguiente.putExtra("nombre",nombre);
        startActivity(siguiente);
        overridePendingTransition(R.anim.fade_in, android.R.anim.fade_out);
    }

    public void Atras(View view){
        onBackPressed();
        overridePendingTransition(R.anim.fade_in, android.R.anim.fade_out);
    }
}