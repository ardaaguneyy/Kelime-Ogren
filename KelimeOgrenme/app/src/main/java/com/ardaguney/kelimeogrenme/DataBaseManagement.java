package com.ardaguney.kelimeogrenme;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

public class DataBaseManagement {
    SQLiteDatabase database;
    public void OpenDatabase(Context context){
       database  = context.openOrCreateDatabase("Database",Context.MODE_PRIVATE,null);
    }
    public void AddDatabase(String language , String word ,  String word2){
        try {
            String sqlString = "CREATE TABLE IF NOT EXISTS " + language +" (id INTEGER PRIMARY KEY, word VARCHAR, word2 VARCHAR)";
            database.execSQL(sqlString);
            String sqlitestring = "INSERT INTO "+ language  +" (word , word2) VALUES (?,?)";
            SQLiteStatement sqLiteStatement = database.compileStatement(sqlitestring);
            sqLiteStatement.bindString(1,word );
            sqLiteStatement.bindString(2,word2);
            sqLiteStatement.execute();

        }catch (Exception e){
            e.printStackTrace();

        }


    }
    public void deletedatabase(String language, String word){
        try {
            String sqlitestringdelete = "DELETE FROM " + language + " WHERE word = ? ";
            SQLiteStatement statement = database.compileStatement(sqlitestringdelete);
            statement.bindString(1,word);
            statement.execute();
        }catch (Exception e){

        }

    }
}
