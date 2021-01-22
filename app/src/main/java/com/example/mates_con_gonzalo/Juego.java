package com.example.mates_con_gonzalo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class Juego extends AppCompatActivity {

    ImageView IVIzquierda, IVDerecha, IVSimbolo, IVVidas;
    String dificultad,nombrejugador;
    int numizq, numdec, imgsimbolo,resultado, vidas, puntuacion;
    EditText etRespuesta;
    TextView TvVidas, TvJugador, TvTiempo, TvPuntuacion, TvDificultad;
    CountDownTimer temporizador;
    Boolean tempoiniciado = false;
    MediaPlayer Ganar,Perder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        getSupportActionBar().hide();

        IVIzquierda = findViewById(R.id.imageViewIzquierda);
        IVDerecha = findViewById(R.id.imageViewDerecha);
        IVSimbolo = findViewById(R.id.imageViewSimbolo);
        IVVidas = findViewById(R.id.imageViewVidas);
        etRespuesta = findViewById(R.id.editTextNumber);
        TvVidas = findViewById(R.id.textViewVidas);
        TvJugador = findViewById(R.id.textViewJugador);
        TvDificultad = findViewById(R.id.textViewDificultad);
        TvTiempo = findViewById(R.id.textViewTiempo);
        TvPuntuacion = findViewById(R.id.textViewPuntuacion);
        nombrejugador = getIntent().getStringExtra("nombre");

        if(savedInstanceState != null){
            TvPuntuacion.setText(savedInstanceState.getString("TVpuntuacion"));
            puntuacion = savedInstanceState.getInt("puntuacion");
            numizq = savedInstanceState.getInt("numizq");
            numdec = savedInstanceState.getInt("numdec");
            resultado = savedInstanceState.getInt("resultado");
            vidas = savedInstanceState.getInt("vidas");
            imgsimbolo = savedInstanceState.getInt("imgsim");
            if(dificultad == "dificil"){
                tempoiniciado = savedInstanceState.getBoolean("tempoiniciado");
            }
            if (!tempoiniciado)
                EmpezarTemporizador();
            else {
                tempoiniciado = false;
                temporizador.cancel();
                EmpezarTemporizador();
            }
            rellenarImagenes(numdec,numizq,imgsimbolo);
            EjecutarJuegoGuardado();
        }else{
            numizq = 0; numdec = 0; resultado = 0; vidas = 3; puntuacion = 0;
            EjecutarJuego();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("TVpuntuacion",TvPuntuacion.getText().toString());
        outState.putInt("puntuacion",puntuacion);
        outState.putInt("numizq",numizq);
        outState.putInt("numdec",numdec);
        outState.putInt("resultado",resultado);
        outState.putInt("vidas",vidas);
        outState.putInt("imgsim",imgsimbolo);
        if(dificultad == "dificil"){
            outState.putBoolean("tempoiniciado",tempoiniciado);
        }
    }

    public void EjecutarJuego() {
        TvJugador.setText("Player: " + nombrejugador);
        if (vidas == 3) {
            IVVidas.setImageResource(R.drawable.vida3);
            IVVidas.getLayoutParams().width = 400;
            IVVidas.getLayoutParams().height = 150;
            IVVidas.setAdjustViewBounds(true);
        }

        dificultad = getIntent().getStringExtra("dificultad");
        if (dificultad.equals("facil")) Facil();
        else if (dificultad.equals("medio")) Medio();
        else if (dificultad.equals("dificil")) Dificil();
    }

    public void EjecutarJuegoGuardado(){
        dificultad = getIntent().getStringExtra("dificultad");
        TvJugador.setText("Player: " + nombrejugador);
        if(puntuacion == 0 && vidas == 3){
            EjecutarJuego();
        }else {
            if (vidas == 3) {
                IVVidas.setImageResource(R.drawable.vida3);
                IVVidas.getLayoutParams().width = 400;
                IVVidas.getLayoutParams().height = 150;
                IVVidas.setAdjustViewBounds(true);
            }
            if (vidas == 2) {
                IVVidas.setImageResource(R.drawable.vida3);
                IVVidas.getLayoutParams().width = 400;
                IVVidas.getLayoutParams().height = 150;
                IVVidas.setAdjustViewBounds(true);
            }
            if (vidas == 1) {
                IVVidas.setImageResource(R.drawable.vida3);
                IVVidas.getLayoutParams().width = 400;
                IVVidas.getLayoutParams().height = 150;
                IVVidas.setAdjustViewBounds(true);
            }
        }
    }
    public void rellenarImagenes(int imgdec, int imgizq, int imgsim) {
        if (imgdec == 1) {
            IVDerecha.setImageResource(R.drawable.n1);
            IVDerecha.getLayoutParams().width = 400;
            IVDerecha.getLayoutParams().height = 400;
            IVDerecha.setAdjustViewBounds(true);
        } else if (imgdec == 2) {
            IVDerecha.setImageResource(R.drawable.n2);
            IVDerecha.getLayoutParams().width = 400;
            IVDerecha.getLayoutParams().height = 400;
            IVDerecha.setAdjustViewBounds(true);
        } else if (imgdec == 3) {
            IVDerecha.setImageResource(R.drawable.n3);
            IVDerecha.getLayoutParams().width = 400;
            IVDerecha.getLayoutParams().height = 400;
            IVDerecha.setAdjustViewBounds(true);
        } else if (imgdec == 4) {
            IVDerecha.setImageResource(R.drawable.n4);
            IVDerecha.getLayoutParams().width = 400;
            IVDerecha.getLayoutParams().height = 400;
            IVDerecha.setAdjustViewBounds(true);
        } else if (imgdec == 5) {
            IVDerecha.setImageResource(R.drawable.n5);
            IVDerecha.getLayoutParams().width = 400;
            IVDerecha.getLayoutParams().height = 400;
            IVDerecha.setAdjustViewBounds(true);
        } else if (imgdec == 6) {
            IVDerecha.setImageResource(R.drawable.n6);
            IVDerecha.getLayoutParams().width = 400;
            IVDerecha.getLayoutParams().height = 400;
            IVDerecha.setAdjustViewBounds(true);
        } else if (imgdec == 7) {
            IVDerecha.setImageResource(R.drawable.n7);
            IVDerecha.getLayoutParams().width = 400;
            IVDerecha.getLayoutParams().height = 400;
            IVDerecha.setAdjustViewBounds(true);
        } else if (imgdec == 8) {
            IVDerecha.setImageResource(R.drawable.n8);
            IVDerecha.getLayoutParams().width = 400;
            IVDerecha.getLayoutParams().height = 400;
            IVDerecha.setAdjustViewBounds(true);
        } else if (imgdec == 9) {
            IVDerecha.setImageResource(R.drawable.n9);
            IVDerecha.getLayoutParams().width = 400;
            IVDerecha.getLayoutParams().height = 400;
            IVDerecha.setAdjustViewBounds(true);
        }
        if (imgizq == 1) {
            IVIzquierda.setImageResource(R.drawable.n1);
            IVIzquierda.getLayoutParams().width = 400;
            IVIzquierda.getLayoutParams().height = 400;
            IVIzquierda.setAdjustViewBounds(true);
        } else if (imgizq == 2) {
            IVIzquierda.setImageResource(R.drawable.n2);
            IVIzquierda.getLayoutParams().width = 400;
            IVIzquierda.getLayoutParams().height = 400;
            IVIzquierda.setAdjustViewBounds(true);
        } else if (imgizq == 3) {
            IVIzquierda.setImageResource(R.drawable.n3);
            IVIzquierda.getLayoutParams().width = 400;
            IVIzquierda.getLayoutParams().height = 400;
            IVIzquierda.setAdjustViewBounds(true);
        } else if (imgizq == 4) {
            IVIzquierda.setImageResource(R.drawable.n4);
            IVIzquierda.getLayoutParams().width = 400;
            IVIzquierda.getLayoutParams().height = 400;
            IVIzquierda.setAdjustViewBounds(true);
        } else if (imgizq == 5) {
            IVIzquierda.setImageResource(R.drawable.n5);
            IVIzquierda.getLayoutParams().width = 400;
            IVIzquierda.getLayoutParams().height = 400;
            IVIzquierda.setAdjustViewBounds(true);
        } else if (imgizq == 6) {
            IVIzquierda.setImageResource(R.drawable.n6);
            IVIzquierda.getLayoutParams().width = 400;
            IVIzquierda.getLayoutParams().height = 400;
            IVIzquierda.setAdjustViewBounds(true);
        } else if (imgizq == 7) {
            IVIzquierda.setImageResource(R.drawable.n7);
            IVIzquierda.getLayoutParams().width = 400;
            IVIzquierda.getLayoutParams().height = 400;
            IVIzquierda.setAdjustViewBounds(true);
        } else if (imgizq == 8) {
            IVIzquierda.setImageResource(R.drawable.n8);
            IVIzquierda.getLayoutParams().width = 400;
            IVIzquierda.getLayoutParams().height = 400;
            IVIzquierda.setAdjustViewBounds(true);
        } else if (imgizq == 9) {
            IVIzquierda.setImageResource(R.drawable.n9);
            IVIzquierda.getLayoutParams().width = 400;
            IVIzquierda.getLayoutParams().height = 400;
            IVIzquierda.setAdjustViewBounds(true);
        }
        if (imgsim == 0) {
            IVSimbolo.setImageResource(R.drawable.suma);
            IVSimbolo.getLayoutParams().width = 300;
            IVSimbolo.getLayoutParams().height = 300;
            IVSimbolo.setAdjustViewBounds(true);
        } else if (imgsim == 1) {
            IVSimbolo.setImageResource(R.drawable.resta);
            IVSimbolo.getLayoutParams().width = 300;
            IVSimbolo.getLayoutParams().height = 300;
            IVSimbolo.setAdjustViewBounds(true);
        } else if (imgsim == 2) {
            IVSimbolo.setImageResource(R.drawable.multiplicar);
            IVSimbolo.getLayoutParams().width = 300;
            IVSimbolo.getLayoutParams().height = 300;
            IVSimbolo.setAdjustViewBounds(true);
        } else if (imgsim == 3) {
            IVSimbolo.setImageResource(R.drawable.dividir);
            IVSimbolo.getLayoutParams().width = 300;
            IVSimbolo.getLayoutParams().height = 300;
            IVSimbolo.setAdjustViewBounds(true);
        }
    }

    public void calcular(View view) {
        int respuesta = 3000;
        try {
            respuesta = Integer.parseInt(etRespuesta.getText().toString());
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

        if (respuesta == resultado) {
            Ganar = MediaPlayer.create(this,R.raw.voz_ganar1);
            Ganar.start();
            Toast.makeText(this, "You are a genius!!!", Toast.LENGTH_SHORT).show();
            if (dificultad.equals("facil")) {
                puntuacion = puntuacion + 50;
                TvPuntuacion.setText("Score: " + puntuacion);
            } else if (dificultad.equals("medio")) {
                puntuacion = puntuacion + 75;
                TvPuntuacion.setText("Score: " + puntuacion);
            } else if (dificultad.equals("dificil")) {
                puntuacion = puntuacion + 100;
                if (puntuacion >= 500) {
                    guardarcalculo();
                    Intent ganador = new Intent(this, CreditosGanar.class);
                    startActivity(ganador);
                    overridePendingTransition(R.anim.fade_in, android.R.anim.fade_out);
                } else TvPuntuacion.setText("Score: " + puntuacion);
            }
            EjecutarJuego();
        } else {
            Perder = MediaPlayer.create(this,R.raw.voz_perder);
            Perder.start();
            Toast.makeText(this, "Ohh noo!!", Toast.LENGTH_SHORT).show();
            if (dificultad.equals("facil")) {
                puntuacion = puntuacion - 20;
                TvPuntuacion.setText("Score: " + puntuacion);
            } else if (dificultad.equals("medio")) {
                puntuacion = puntuacion - 35;
                TvPuntuacion.setText("Score: " + puntuacion);
            } else if (dificultad.equals("dificil")) {
                puntuacion = puntuacion - 50;
                TvPuntuacion.setText("Score: " + puntuacion);
            }
            vidas--;
            if (vidas == 0) {
                guardarcalculo();
                Intent perdedor = new Intent(this, Perder.class);
                startActivity(perdedor);
                overridePendingTransition(R.anim.fade_in, android.R.anim.fade_out);
            } else if (vidas == 2) {
                IVVidas.setImageResource(R.drawable.vida2);
                IVVidas.getLayoutParams().width = 400;
                IVVidas.getLayoutParams().height = 150;
                IVVidas.setAdjustViewBounds(true);
            } else if (vidas == 1) {
                IVVidas.setImageResource(R.drawable.vida1);
                IVVidas.getLayoutParams().width = 400;
                IVVidas.getLayoutParams().height = 150;
                IVVidas.setAdjustViewBounds(true);
            }
        }
        EjecutarJuego();
    }

    public void Facil() {
        TvTiempo.setVisibility(View.INVISIBLE);
        TvDificultad.setText("Difficulty: Easy");
        Random rndIzq = new Random();
        int izq = rndIzq.nextInt(9);
        Random rndDec = new Random();
        int dec = rndDec.nextInt(9);
        Random sim = new Random();
        int simbolo = sim.nextInt(2);
        for (int i = 0; i <= izq; i++)
            if (i == izq) numizq = i + 1;
        for (int d = 0; d <= dec; d++)
            if (d == dec) numdec = d + 1;
        if (simbolo == 0) resultado = numizq + numdec;
        if (simbolo == 1) {
            if (numizq < numdec) {
                int a = numizq;
                numizq = numdec;
                numdec = a;
                resultado = numizq - numdec;
            } else resultado = numizq - numdec;
        }
        imgsimbolo = simbolo;
        rellenarImagenes(numdec, numizq, simbolo);
    }

    public void Medio() {
        TvTiempo.setVisibility(View.INVISIBLE);
        TvDificultad.setText("Difficulty: Medium");
        Random rndIzq = new Random();
        int izq = rndIzq.nextInt(9);
        Random rndDec = new Random();
        int dec = rndDec.nextInt(9);
        Random sim = new Random();
        int simbolo = sim.nextInt(4);
        for (int i = 0; i <= izq; i++)
            if (i == izq) numizq = i + 1;
        for (int d = 0; d <= dec; d++)
            if (d == dec) numdec = d + 1;
        if (simbolo == 0) resultado = numizq + numdec;
        else if (simbolo == 1) {
            if (numizq < numdec) {
                int a = numizq;
                numizq = numdec;
                numdec = a;
                resultado = numizq - numdec;
            } else resultado = numizq - numdec;
        } else if (simbolo == 2) resultado = numizq * numdec;
        else if (simbolo == 3) {
            while (numizq % numdec != 0 || numizq < numdec) {
                izq = rndIzq.nextInt(9);
                dec = rndDec.nextInt(9);
                for (int i = 0; i <= izq; i++)
                    if (i == izq) numizq = i + 1;
                for (int d = 0; d <= dec; d++)
                    if (d == dec) numdec = d + 1;
            }
            resultado = numizq / numdec;
        }
        imgsimbolo = simbolo;
        rellenarImagenes(numdec, numizq, simbolo);
    }

    public void EmpezarTemporizador() {
        tempoiniciado = true;
        temporizador = new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                TvTiempo.setText("Time: " + millisUntilFinished / 1000 + " S");
            }

            public void onFinish() {
                calcular(null);
            }
        }.start();
    }

    public void Dificil() {
        if (!tempoiniciado)
            EmpezarTemporizador();
        else {
            tempoiniciado = false;
            temporizador.cancel();
            EmpezarTemporizador();
        }

        TvDificultad.setText("Difficulty: Hard");
        Random rndIzq = new Random();
        int izq = rndIzq.nextInt(9);
        Random rndDec = new Random();
        int dec = rndDec.nextInt(9);
        Random sim = new Random();
        int simbolo = sim.nextInt(4);
        for (int i = 0; i <= izq; i++)
            if (i == izq) numizq = i + 1;
        for (int d = 0; d <= dec; d++)
            if (d == dec) numdec = d + 1;
        if (simbolo == 0) resultado = numizq + numdec;
        else if (simbolo == 1) {
            if (numizq < numdec) {
                int a = numizq;
                numizq = numdec;
                numdec = a;
                resultado = numizq - numdec;
            } else resultado = numizq - numdec;
        } else if (simbolo == 2) resultado = numizq * numdec;
        else if (simbolo == 3) {
            while (numizq % numdec != 0 || numizq < numdec) {
                izq = rndIzq.nextInt(9);
                dec = rndDec.nextInt(9);
                for (int i = 0; i <= izq; i++)
                    if (i == izq) numizq = i + 1;
                for (int d = 0; d <= dec; d++)
                    if (d == dec) numdec = d + 1;
            }
            resultado = numizq / numdec;
        }
        imgsimbolo = simbolo;
        rellenarImagenes(numdec, numizq, simbolo);
    }

    public void VolverInicio(View view) {
        new AlertDialog.Builder(this).setTitle("Save punctuation").setMessage("Do you want to save the punctuation?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        guardarcalculo();
                        Toast.makeText(Juego.this, "Punctuation added", Toast.LENGTH_SHORT).show();
                        vinicio();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Juego.this, "Punctuation not added", Toast.LENGTH_SHORT).show();
                        vinicio();
                    }
                }).show();
    }

    private void vinicio(){
        Intent Inicio = new Intent(this, MainActivity.class);
        startActivity(Inicio);
        overridePendingTransition(R.anim.fade_in, android.R.anim.fade_out);
    }

    public void guardarcalculo() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "BRecords", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        Cursor fila = BaseDeDatos.rawQuery("select cod from records", null);
        int cod = 1;
        if (fila.moveToFirst()) {
            do {
                cod++;
            } while (fila.moveToNext());
        }

        ContentValues registro = new ContentValues();
        registro.put("cod", cod);
        registro.put("nombre", nombrejugador);
        registro.put("puntuacion", puntuacion);

        BaseDeDatos.insert("records", null, registro);
        BaseDeDatos.close();
    }
}