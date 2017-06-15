package com.example.rafee.dictionary1;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Rafee on 1/15/2017.
 */

public class DatabaseRetrieve extends DatabaseObject {

    public DatabaseRetrieve(Context context) {
        super(context);
    }

    //get only word from database table
    public String[] dictionaryWords(String TABLE_NAME){

        String query = "Select * from "+TABLE_NAME;

        Cursor cursor = this.getDbConnection().rawQuery(query, null);//Cursor is the database loop

        ArrayList<String> wordTerms = new ArrayList<String>();

        if(cursor.moveToFirst()){
            do{
                String word = cursor.getString(cursor.getColumnIndexOrThrow("word"));

                wordTerms.add(word);
            }while(cursor.moveToNext());
        }

        cursor.close();

        String[] dictionaryWords = new String[ wordTerms.size() ];

        dictionaryWords = wordTerms.toArray(dictionaryWords);

        return dictionaryWords;
    }

    //get word + meaning from database table
    public DictionaryObject getWordById(int id){

        DictionaryObject wordObject = null;//create DictionaryObject class's object

        String query = "select * from dictionary where id = " + id;

        Cursor cursor = this.getDbConnection().rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                String word = cursor.getString(cursor.getColumnIndexOrThrow("word"));

                String meaning = cursor.getString(cursor.getColumnIndexOrThrow("meaning"));

                String synonym = cursor.getString(cursor.getColumnIndexOrThrow("synonym"));

                String antonym = cursor.getString(cursor.getColumnIndexOrThrow("antonym"));

                String sentence = cursor.getString(cursor.getColumnIndexOrThrow("sentence"));

                wordObject = new DictionaryObject(word, meaning,synonym,antonym,sentence);//set word and meaning in the DictionaryObject class's object
            }while(cursor.moveToNext());
        }

        cursor.close();

        return wordObject;
    }

    public ArrayList<Integer> getAllID(String TABLE_NAME){
        String query = "Select * from "+TABLE_NAME;

        Cursor cursor = this.getDbConnection().rawQuery(query, null);//Cursor is the database loop

        ArrayList<Integer> idTerms = new ArrayList<Integer>();

        if(cursor.moveToFirst()){
            do{
                int idall = cursor.getInt(cursor.getColumnIndexOrThrow("history_id"));

                idTerms.add(idall);
            }while(cursor.moveToNext());
        }

        cursor.close();

        return idTerms;
    }

}
