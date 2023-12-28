package com.example.kamusindonesiaminang.database;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.kamusindonesiaminang.model.ModelKamus;

import java.util.ArrayList;



public class DatabaseAccess {
    private final SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    private DatabaseAccess(Context context){
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context){
        if(instance == null){
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open(){
        this.database = openHelper.getWritableDatabase();
    }

    public void close(){
        if(database != null){
            this.database.close();
        }
    }

    public ArrayList<ModelKamus> getKamusMinang(){
        ArrayList<ModelKamus> modelKamusArrayList = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM KamusMinangIndonesia", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            ModelKamus modelKamus = new ModelKamus();
            modelKamus.setStrKata(cursor.getString(1));
            modelKamusArrayList.add(modelKamus);
            cursor.moveToNext();
        }
        cursor.close();
        return modelKamusArrayList;
    }

    public ArrayList<ModelKamus> getKamusIndonesia(){
        ArrayList<ModelKamus> modelKamusArrayList = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM KamusMinangIndonesia ORDER BY indonesia ASC", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            ModelKamus modelKamus = new ModelKamus();
            modelKamus.setStrKata(cursor.getString(2));
            modelKamusArrayList.add(modelKamus);
            cursor.moveToNext();
        }
        cursor.close();
        return modelKamusArrayList;
    }

    public ArrayList<ModelKamus> getSearchMinang(String keyword){
        ArrayList<ModelKamus> modelKamusArrayList = new ArrayList<>();
        String queryString = "SELECT * FROM KamusMinangIndonesia WHERE minang LIKE '%" + keyword +"%' ORDER BY minang ASC";
        Cursor cursor = database.rawQuery(queryString, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            ModelKamus modelKamus = new ModelKamus();
            modelKamus.setStrKata(cursor.getString(1));
            modelKamusArrayList.add(modelKamus);
            cursor.moveToNext();
        }
        cursor.close();
        return modelKamusArrayList;
    }

    public ArrayList<ModelKamus> getSearchIndonesia(String keyword){
        ArrayList<ModelKamus> modelKamusArrayList = new ArrayList<>();
        String queryString = "SELECT * FROM KamusMinangIndonesia WHERE indonesia LIKE '%" + keyword +"%' ORDER BY indonesia ASC";
        Cursor cursor = database.rawQuery(queryString, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            ModelKamus modelKamus = new ModelKamus();
            modelKamus.setStrKata(cursor.getString(2));
            modelKamusArrayList.add(modelKamus);
            cursor.moveToNext();
        }
        cursor.close();
        return modelKamusArrayList;
    }

    public String getSelectedMinang(String kataMinang){
        String queryString = "SELECT * FROM KamusMinangIndonesia WHERE minang='"+ kataMinang +"'";
        Cursor cursor = database.rawQuery(queryString, null);
        cursor.moveToFirst();
        String arti = cursor.getString(2);
        cursor.close();
        return arti;
    }

    public String getSelectedIndonesia(String kataIndonesia){
        String queryString = "SELECT * FROM KamusMinangIndonesia WHERE indonesia='"+ kataIndonesia +"'";
        Cursor cursor = database.rawQuery(queryString, null);
        cursor.moveToFirst();
        String arti = cursor.getString(1);
        cursor.close();
        return arti;
    }

}
