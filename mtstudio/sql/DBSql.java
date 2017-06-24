package mtstudio.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import mtstudio.acs.ac_main;


/**
 * Created by mt on 6/5/17.
 */
public class DBSql extends SQLiteOpenHelper {
    public static final String dbname = "mt.db";
    public static final int vers=1;

    public DBSql(ac_main context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, version);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists tb_snake(id integer primary key autoincrement ,len_sw text,len_xw text,len_height text,len_width text,len_x text)");
        db.execSQL("create table if not exists tb_cuan(id integer primary key autoincrement ,c1 text,c2 text,c3 text,c4 text,c5 text,c6 text,c7 text,c8 text,c9 text)");
        db.execSQL("create table if not exists tb_lefte(id integer primary key autoincrement ,le1 text,le2 text,le3 text,le4 text,le5 text,le6 text)");
        db.execSQL("create table if not exists tb_righte(id integer primary key autoincrement ,re1 text,re2 text,re3 text,re4 text,re5 text,re6 text)");
        db.execSQL("create table if not exists tb_tl(id integer primary key autoincrement ,tl1 text,tl2 text,tl3 text)");
        db.execSQL("create table if not exists tb_tu(id integer primary key autoincrement ,tu1 text,tu2 text,tu3 text,tu4 text)");
        db.execSQL("create table if not exists tb_tz(id integer primary key autoincrement ,tz1 text,tz2 text,tz3 text,tz4 text)");

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
