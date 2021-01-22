package com.example.mates_con_gonzalo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class CreditosGanar extends AppCompatActivity {

    MediaPlayer GanarBocas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditos_ganar);
        getSupportActionBar().hide();

        GanarBocas = MediaPlayer.create(this,R.raw.voz_ganarbocas);
        GanarBocas.start();
    }

    public void VolverInicio(View view){
        Intent Inicio = new Intent(this,MainActivity.class);
        startActivity(Inicio);
        overridePendingTransition(R.anim.fade_in, android.R.anim.fade_out);
    }
}