package com.example.administrateur.sqlitedepts;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrateur on 31/08/2017.
 */

public class Tools {

    public static Date sqlTodat (String value) throws Exception{

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.parse(value);

    }
    public static String DatTosql (Date value) throws Exception{

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(value);

    }

    public static Date strTodat (String value) throws Exception{

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.parse(value);


    }
    public static String dattostr (Date value) throws Exception{

        DateFormat df = new SimpleDateFormat("dd/MM/YYYY");
        return df.format(value);

    }
}
