package com.example.mates_con_gonzalo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        et = findViewById(R.id.editTextNombre);

        if(savedInstanceState != null){
            et.setText(savedInstanceState.getString("nombre"));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("nombre",et.getText().toString());
    }

    public void Jugar(View view){
        String nombre = et.getText().toString();
        if(nombre.isEmpty()) Toast.makeText(this, "\n" +
                "Fill in the name field", Toast.LENGTH_SHORT).show();
        else{
            Intent siguiente = new Intent(this,Seleccionar_dificultad.class);
            siguiente.putExtra("nombre",nombre);
            startActivity(siguiente);
            overridePendingTransition(R.anim.fade_in, android.R.anim.fade_out);
        }
    }

    public void Historial(View view){
        Intent siguiente = new Intent(this,Historial.class);
        startActivity(siguiente);
        overridePendingTransition(R.anim.fade_in, android.R.anim.fade_out);
    }
}