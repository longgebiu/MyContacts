package com.example.administrator.mycontacts;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Leung1 on 2016/10/24.
 */
public class MyDB extends SQLiteOpenHelper {
    private static String DB_NAME="My_DB.db";
    private  static  int DB_VERSION=1;
    private SQLiteDatabase db;
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public MyDB(AddContactsActivity context){
        super(context,DB_NAME,null,DB_VERSION);
        db = getWritableDatabase();

    }
    public SQLiteDatabase openConnection(){
        if(!db.isOpen()) {
            db = getWritableDatabase();
        }

        return db;

    }
    public void closeConnection(){
        try {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public boolean creatrTable(String createTableSql){
        try{
            openConnection();
            db.execSQL(createTableSql);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally {
            closeConnection();;
        }
        return true;
    }
    public boolean save(String tableName, ContentValues values){
        try{
            openConnection();
            db.insert(tableName,null,values);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean isTableExits(String tableName){
        try{
            openConnection();
            String str="select count(*)xcount from "+tableName;
            db.rawQuery(str,null).close();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally {
            closeConnection();
        }
        return true;

    }
    public Cursor find(String findSql,String obj[]){
        try{
            openConnection();
            Cursor cursor = db.rawQuery(findSql,obj);
            return cursor;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public boolean update(String table,ContentValues values,String whereClause,String[] whereArgs ){
        try{
            openConnection();
            db.update(table,values,whereClause,whereArgs);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally {
            {
                closeConnection();
            }
            return true;
        }
    }
    public boolean delete(String table,String deleteSql,String obj[]){
        try{
            openConnection();
            db.delete(table,deleteSql,obj);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally {
            closeConnection();
        }
        return  true;
    }

}
