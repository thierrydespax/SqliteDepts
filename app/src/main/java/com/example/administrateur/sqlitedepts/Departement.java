package com.example.administrateur.sqlitedepts;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaCodec;
import android.test.suitebuilder.TestSuiteBuilder;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.example.administrateur.sqlitedepts.Tools.*;

/**
 * Created by Administrateur on 29/08/2017.
 */

public class Departement {

    private int noRegion, surface;
    private String noDept, nom, nomStd,  chefLieu, urlWiki;
    private Date dateCreation;


    private final String TABLE_NAME = "departements";

    private Context ctxt;
    private SQLiteDatabase db;
    private EditText txtSearch;

    public Departement(Context ctxt) {
        this.ctxt = ctxt;

        //Acceder à la base de donnees
        DbInit initDb = DbInit.getInstance(ctxt);
        db =initDb.getWritableDatabase();
    }

    public Departement(Context ctxt, String no)throws Exception{

            this(ctxt);
            select(no);

    }

    public int getNoRegion() {
        return noRegion;
    }

    public void setNoRegion(int noRegion) {
        this.noRegion = noRegion;
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    public String getNoDept() {
        return noDept;
    }

    public void setNoDept(String noDept) throws DbException
        {
            if (noDept.equals("")) {
                throw new DbException(ctxt, R.string.errBadNOept);
            }

            Pattern pattern = Pattern.compile ("[0-9][0-9AB][0-9]?");
            Matcher matcher= pattern.matcher(noDept);
            if(!matcher.find()){
                throw new DbException((ctxt.getString(R.string.errBadNOept)));
            }

          this.noDept = noDept;

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomStd(){
        return nomStd;
    }

    public void setNomStd(String nomStd) {
        this.nomStd = nomStd;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) throws Exception {
        this.dateCreation =  strTodat(dateCreation);
    }
    public String getChefLieu() {
        return chefLieu;
    }

    public void setChefLieu(String chefLieu) {
        this.chefLieu = chefLieu;
    }

    public String getUrlWiki() {
        return urlWiki;
    }

    public void setUrlWiki(String urlWiki) {
        this.urlWiki = urlWiki;
    }





    public void select(String no) throws Exception {


        //definir le critère
        String where = "no_dept='" + no + "'";

        //charger les valeurs depuis la BD
        String[] cols = {"no_dept", "no_region", "nom", "nom_std", "surface", "date_creation", "chef_lieu", "url_Wiki"};
        Cursor curs = db.query(false, TABLE_NAME, cols, where, null, "", "", "", "");

        if (curs.moveToFirst()) {

            this.noDept=curs.getString(0);
            this.noRegion=curs.getInt(1); //on recupere les valeurs pour les stocker dans les proprietes (variables privées)
            this.nom=curs.getString(2);
            this.nomStd=curs.getString(3);
            this.surface=curs.getInt(4);
            this.dateCreation=sqlTodat(curs.getString(5));
            this.chefLieu= curs.getString(6);
            this.urlWiki=curs.getString(7);


        } else {
            throw new DbDeptNotFoundException();
        }

    }

    public void delete() throws Exception {

        String where = "no_dept='"+ this.noDept+"'";
        db.delete(TABLE_NAME,where,null);

    }

    public void insert() throws Exception {
        ContentValues values = new ContentValues();

        values.put("no_dept",this.noDept);
        values.put("no_region",this.noRegion);
        values.put("nom",this.nom);
        values.put("nom_std",this.nomStd);
        values.put("surface",this.surface);
        values.put("date_creation",DatTosql( this.dateCreation));
        values.put("chef_lieu",this.chefLieu);
        values.put("url_wiki",this.urlWiki);

        db.insert(TABLE_NAME,null,values);
    }

    public void update() throws Exception {

        String where= "no_dept='"+ this.noDept+"'";

        ContentValues values = new ContentValues();

        values.put("no_dept",this.noDept);
        values.put("no_region",this.noRegion);
        values.put("nom",this.nom);
        values.put("nom_std",this.nomStd);
        values.put("surface",this.surface);
        values.put("date_creation",DatTosql( this.dateCreation));
        values.put("chef_lieu",this.chefLieu);
        values.put("url_wiki",this.urlWiki);

        db.update(TABLE_NAME,values,where,null);

    }
        public class DbException extends Exception {
            public DbException(String msg){
                super (msg);
            }
            public DbException(Context ctxt, int stringid ) {
                super(ctxt.getString(stringid));
            }

        }
    public class DbDeptNotFoundException extends DbException{

        public DbDeptNotFoundException(){
            super(ctxt, R.string.errDeptNotFound);
        }
    }

}





