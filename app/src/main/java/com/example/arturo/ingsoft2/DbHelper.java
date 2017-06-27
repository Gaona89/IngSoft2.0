package com.example.arturo.ingsoft2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Arturo on 18/03/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    String sqlCreateHijo = "CREATE TABLE HIJO (" +
            "ci INTEGER,"+
            "nombre TEXT,"+
            "apellido TEXT,"+
            "email TEXT,"+
            "fecha_nac TEXT)";

    String sqlCreateVacuna = "CREATE TABLE vacuna (" +
            "id INTEGER,"+
            "nombre TEXT)";

    String sqlCreateRegistroVacuna = "CREATE TABLE registro_vacuna (" +
            "id INTEGER,"+
            "ci_hijo INTEGER,"+
            "id_aplicacion INTEGER,"+
            "fecha_aplicacion TEXT)";

    String sqlCreateAplicacion = "CREATE TABLE aplicacion (" +
            "id INTEGER,"+
            "vacuna INTEGER,"+
            "dosis INTEGER,"+
            "tiempo_aplicacion INTEGER)";

    public DbHelper (Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateHijo);
        db.execSQL(sqlCreateVacuna);
        db.execSQL(sqlCreateRegistroVacuna);
        db.execSQL(sqlCreateAplicacion);
       /*Datos precargados*/
        db.execSQL("INSERT INTO HIJO(ci,nombre,apellido,email,fecha_nac)"+
                " VALUES(5555,'juan','perez','gaona.arturo89@gmail.com','2015-05-16')");

        db.execSQL("INSERT INTO HIJO(ci,nombre,apellido,email,fecha_nac)"+
                " VALUES(4444,'jose','perez','gaona.arturo89@gmail.com','2015-04-20')");

        db.execSQL("INSERT INTO HIJO(ci,nombre,apellido,email,fecha_nac)"+
                " VALUES(4444,'jose','fulano','gaona.arturo89@gmail.com','2016-03-12')");

        db.execSQL("INSERT INTO registro_vacuna(id,ci_hijo,id_aplicacion,fecha_aplicacion)"+
                " VALUES(1,5555,1,'2016-03-12')");

        db.execSQL("insert into vacuna(id,nombre) values(1,'BCG')");
        db.execSQL("insert into vacuna(id,nombre) values(2,'ROTAVIRUS')");
        db.execSQL("insert into vacuna(id,nombre) values(3,'IPV/OPV')");
        db.execSQL("insert into vacuna(id,nombre) values(4,'PENTAVALENTE')");
        db.execSQL("insert into vacuna(id,nombre) values(5,'PCV10')");
        db.execSQL("insert into vacuna(id,nombre) values(6,'SPR')");
        db.execSQL("insert into vacuna(id,nombre) values(7,'ANTIVARICELA')");
        db.execSQL("insert into vacuna(id,nombre) values(8,'HEPAT A')");
        db.execSQL("insert into vacuna(id,nombre) values(9,'AA')");
        db.execSQL("insert into vacuna(id,nombre) values(10,'DTP1')");
        db.execSQL("insert into vacuna(id,nombre) values(11,'INFLUENZA')");
        db.execSQL("insert into vacuna(id,nombre) values(12,'VPH')");
        db.execSQL("insert into vacuna(id,nombre) values(13,'Tdpa')");

        db.execSQL("insert into APLICACION(id,vacuna,dosis,tiempo_aplicacion) values(1,1,1,0)");
        db.execSQL("insert into APLICACION(id,vacuna,dosis,tiempo_aplicacion) values(2,2,1,2)");
        db.execSQL("insert into APLICACION(id,vacuna,dosis,tiempo_aplicacion) values(3,3,1,2)");
        db.execSQL("insert into APLICACION(id,vacuna,dosis,tiempo_aplicacion) values(4,4,1,2)");
        db.execSQL("insert into APLICACION(id,vacuna,dosis,tiempo_aplicacion) values(5,5,1,2)");

        db.execSQL("insert into APLICACION(id,vacuna,dosis,tiempo_aplicacion) values(6,2,2,4)");
        db.execSQL("insert into APLICACION(id,vacuna,dosis,tiempo_aplicacion) values(7,3,2,4)");
        db.execSQL("insert into APLICACION(id,vacuna,dosis,tiempo_aplicacion) values(8,4,2,4)");
        db.execSQL("insert into APLICACION(id,vacuna,dosis,tiempo_aplicacion) values(9,5,2,4)");

        db.execSQL("insert into APLICACION(id,vacuna,dosis,tiempo_aplicacion) values(10,3,3,6)");
        db.execSQL("insert into APLICACION(id,vacuna,dosis,tiempo_aplicacion) values(11,4,3,6)");
        db.execSQL("insert into APLICACION(id,vacuna,dosis,tiempo_aplicacion) values(12,11,1,6)");
        db.execSQL("insert into APLICACION(id,vacuna,dosis,tiempo_aplicacion) values(13,11,2,6)");

        db.execSQL("insert into APLICACION(id,vacuna,dosis,tiempo_aplicacion) values(14,6,1,12)");
        db.execSQL("insert into APLICACION(id,vacuna,dosis,tiempo_aplicacion) values(15,5,3,12)");
        db.execSQL("insert into APLICACION(id,vacuna,dosis,tiempo_aplicacion) values(16,9,1,12)");
        db.execSQL("insert into APLICACION(id,vacuna,dosis,tiempo_aplicacion) values(17,11,3,12)");

        db.execSQL("insert into APLICACION(id,vacuna,dosis,tiempo_aplicacion) values(18,7,1,15)");
        db.execSQL("insert into APLICACION(id,vacuna,dosis,tiempo_aplicacion) values(19,8,1,15)");

        db.execSQL("insert into APLICACION(id,vacuna,dosis,tiempo_aplicacion) values(20,3,4,18)");
        db.execSQL("insert into APLICACION(id,vacuna,dosis,tiempo_aplicacion) values(21,10,1,18)");

        db.execSQL("insert into APLICACION(id,vacuna,dosis,tiempo_aplicacion) values(22,3,5,48)");
        db.execSQL("insert into APLICACION(id,vacuna,dosis,tiempo_aplicacion) values(23,10,2,48)");
        db.execSQL("insert into APLICACION(id,vacuna,dosis,tiempo_aplicacion) values(24,6,2,48)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS HIJO");
        db.execSQL("DROP TABLE IF EXISTS VACUNA");
        db.execSQL("DROP TABLE IF EXISTS Aplicacion");
        db.execSQL("DROP TABLE IF EXISTS registro_vacuna");

        db.execSQL(sqlCreateHijo);
        db.execSQL(sqlCreateVacuna);
        db.execSQL(sqlCreateRegistroVacuna);
        db.execSQL(sqlCreateAplicacion);
    }

    public ArrayList<Hijo> obtener_lista_hijos(String email) {
        ArrayList<Hijo> lista = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT ci, nombre, apellido, email, fecha_nac FROM HIJO where email=? order by fecha_nac desc", new String[]{email});
        c.getCount();
        if (c.moveToFirst()) {
            do {
                int ci = c.getInt(0);
                String nombre = c.getString(1);
                String apellido = c.getString(2);
                String email_ = c.getString(3);
                String fecha_nac = c.getString(4);
                lista.add(new Hijo(ci,nombre,apellido,email_,fecha_nac));
            } while(c.moveToNext());

        }

        return lista;
    }

    public ArrayList<Historial> historial_paciente (String ci,String campo) {
        ArrayList<Historial> lista = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select v.nombre||' '|| a.dosis, date((select fecha_nac from hijo where ci=?),'+'||a.tiempo_aplicacion||' month'),'No' "+
                        " from aplicacion a, vacuna v "+
                        " where a.vacuna = v.id and a.id not in (select id_aplicacion from registro_vacuna where ci_hijo = ?) "+


                        " union "+

                        " select v.nombre ||' '|| a.dosis, r.fecha_aplicacion, 'Si' "+
                        " from aplicacion a,  registro_vacuna r, vacuna v "+
                        " where a.vacuna = v.id and r.id_aplicacion = a.id and r.ci_hijo = ? "+
                        "order by "+campo

                , new String[]{ci,ci,ci});

        if (c.moveToFirst()) {
            do {

                String nombre = c.getString(0);
                String fecha = c.getString(1);
                String ap = c.getString(2);
                lista.add(new Historial(nombre,fecha,ap));

            } while(c.moveToNext());

        }

        return lista;
    }
}
