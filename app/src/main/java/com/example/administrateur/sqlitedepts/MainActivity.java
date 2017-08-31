package com.example.administrateur.sqlitedepts;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import static com.example.administrateur.sqlitedepts.Tools.*;

import static com.example.administrateur.sqlitedepts.R.id.txtDateCreation;
import static com.example.administrateur.sqlitedepts.R.id.txtNoDept;
import static com.example.administrateur.sqlitedepts.R.id.txtNoRegion;
import static com.example.administrateur.sqlitedepts.R.id.txtNom;
import static com.example.administrateur.sqlitedepts.R.id.txtNomStd;
import static com.example.administrateur.sqlitedepts.R.id.txtSurface;
import static com.example.administrateur.sqlitedepts.R.id.txtUrlWiki;

public class MainActivity extends AppCompatActivity {
    Departement dept;

    private EditText txtSearch;
    private EditText txtNoDept;
    private EditText txtNoRegion;
    private EditText txtNom;
    private EditText txtNomStd;
    private EditText txtSurface;
    private EditText txtDateCreation;
    private EditText txtChefLieu;
    private EditText txtUrlWiki;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSearch = (EditText)  findViewById(R.id.txtSearch);
        txtNoDept = (EditText) findViewById(R.id.txtNoDept);
        txtNoRegion = (EditText) findViewById(R.id.txtNoRegion);
        txtNom = (EditText) findViewById(R.id.txtNom);
        txtNomStd = (EditText) findViewById(R.id.txtNomStd);
        txtSurface = (EditText) findViewById(R.id.txtSurface);
        txtDateCreation = (EditText) findViewById(R.id.txtDateCreation);
        txtChefLieu = (EditText) findViewById(R.id.txtChefLieu);
        txtUrlWiki = (EditText) findViewById(R.id.txtUrlWiki);

    }

    public void btnSearch(View v) {

        try {
            Departement c = new Departement(this);

            c.select(txtSearch.getText().toString());

            txtNoDept.setText(c.getNoDept());
            txtNoRegion.setText(String.valueOf(c.getNoRegion()));
            txtNom.setText(c.getNom());
            txtNomStd.setText(c.getNomStd());
            txtSurface.setText(String.valueOf(c.getSurface()));
            txtDateCreation.setText(dattostr(c.getDateCreation()));
            txtChefLieu.setText(c.getChefLieu());
            txtUrlWiki.setText(c.getUrlWiki());
            txtNoDept.setEnabled(false);

        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public void btnClear (View v) {

        txtSearch.setText("");
        txtNoDept.setText("");
        txtNoRegion.setText("");
        txtNom.setText("");
        txtNomStd.setText("");
        txtSurface.setText("");
        txtDateCreation.setText("");
        txtChefLieu.setText("");
        txtUrlWiki.setText("");

        txtNoDept.setEnabled(true);
    }

    public void btnDelete(View v) throws Exception {

        dept = new Departement(this);

        dept.setNoDept(txtNoDept.getText().toString());

        dept.delete();

        btnClear(v); // appelle la méthode clear

        Toast.makeText(this, "Departement Supprimé", Toast.LENGTH_SHORT).show();

    }

    public void btnSave(View v) throws Exception {

        Departement c = new Departement(this);

        c.setNoDept(txtNoDept.getText().toString());
        c.setChefLieu(txtChefLieu.getText().toString());
        c.setDateCreation(txtDateCreation.getText().toString());
        c.setNomStd(txtNomStd.getText().toString());
        c.setUrlWiki(txtUrlWiki.getText().toString());
        c.setNom(txtNom.getText().toString());
        c.setNoRegion(Integer.parseInt(txtNoRegion.getText().toString()));
        c.setSurface(Integer.parseInt(txtSurface.getText().toString()));

    try{

        Departement d2= new Departement(this,txtNoDept.getText().toString());
        c.update();
        Toast.makeText(this, "Departement Mis à jour", Toast.LENGTH_SHORT).show();

    }
            catch(Exception ex) {
            c.insert();
            Toast.makeText(this, "Departement Ajouté", Toast.LENGTH_LONG).show();

        }

        }

    }




