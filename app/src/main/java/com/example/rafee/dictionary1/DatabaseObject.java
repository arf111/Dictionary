package com.example.rafee.dictionary1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

/**
 * Created by Rafee on 1/15/2017.
 */

public class DatabaseObject {
    private static DictionaryDatabase dbHelper;
    private SQLiteDatabase db;
    private SQLiteDatabase db1;

    public DatabaseObject(Context context) {
        dbHelper = new DictionaryDatabase(context);
        this.db = dbHelper.getReadableDatabase();
    }

    public SQLiteDatabase getDbConnection() {
        return this.db;
    }

    public SQLiteDatabase getDb1Connection() {
        this.db1 = dbHelper.getWritableDatabase();
        return this.db1;
    }

    public void closeDbconnection() {
        if (this.db != null) {
            this.db.close();
        }
    }

    public void closeDb1connection() {
        if (this.db1 != null) {
            this.db1.close();
        }
    }

    public boolean insertData(int id, String word, String meaning,String TABLE_NAME,String id_name) {
        SQLiteDatabase setData = this.getDb1Connection();
        ContentValues contentValues = new ContentValues();

        contentValues.put(id_name, id);
        contentValues.put("word", word);
        contentValues.put("meaning", meaning);

        long result = setData.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;

    }

    public Integer deleteAllData(String TABLE_NAME){
        SQLiteDatabase del = this.getDb1Connection();

        return del.delete(TABLE_NAME,null,null);
    }

}
