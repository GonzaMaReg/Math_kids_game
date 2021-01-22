package com.example.mates_con_gonzalo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Perder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perder);
        getSupportActionBar().hide();
    }

    public void VolverInicio(View view){
        Intent Inicio = new Intent(this,MainActivity.class);
        startActivity(Inicio);
        overridePendingTransition(R.anim.fade_in, android.R.anim.fade_out);
    }
}