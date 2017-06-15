package com.example.rafee.dictionary1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Rafee on 1/15/2017.
 */

public class DictionaryDatabase extends SQLiteAssetHelper{
    private static final String DATABASE_NAMES = "dictionaryDatabase";

    private static final int DATABASE_VERSION = 5;

    public DictionaryDatabase(Context context) {
        super(context, DATABASE_NAMES, null, DATABASE_VERSION);
    }
}
