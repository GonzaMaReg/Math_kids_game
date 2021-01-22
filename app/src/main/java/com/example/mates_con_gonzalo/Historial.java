package com.example.mates_con_gonzalo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.PixelCopy;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Historial extends AppCompatActivity {

    private ListView LVpuntuacion;
    private ArrayList<String> Puntuaciones, codCalculos;
    String codcalcu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        getSupportActionBar().hide();

        LVpuntuacion = findViewById(R.id.listapuntuaciones);
        Puntuaciones = new ArrayList();
        codCalculos = new ArrayList();

        RellenarArray();
    }

    public void RellenarArray() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "BRecords", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        Cursor fila = BaseDeDatos.rawQuery("select puntuacion,cod,nombre from records", null);

        if (fila.moveToFirst()) {
            do {
                Puntuaciones.add(fila.getString(2) + " got: " + fila.getString(0) + " points ");
                codCalculos.add(fila.getString(1));
            } while (fila.moveToNext());
        }

        BaseDeDatos.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Puntuaciones);
        LVpuntuacion.setAdapter(adapter);

        LVpuntuacion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                codcalcu = codCalculos.get(position);
                DialogoBorrar();
            }
        });
    }

    private void DialogoBorrar() {
        new AlertDialog.Builder(this).setTitle("Delete rocord").setMessage("do you realy want to delete this record?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        borrar();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Historial.this, "", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    private void borrar() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "BRecords", null, 1);
        SQLiteDatabase BaseDatabase = admin.getWritableDatabase();

        int cantidad = BaseDatabase.delete("records", "cod='" + codcalcu + "'", null);
        BaseDatabase.close();

        if (cantidad == 1) {
            Toast.makeText(Historial.this, "Record Borrado", Toast.LENGTH_SHORT).show();
            codCalculos.clear();
            Puntuaciones.clear();
            RellenarArray();
        }
    }
}