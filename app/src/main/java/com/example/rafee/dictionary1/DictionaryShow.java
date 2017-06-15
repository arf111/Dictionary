package com.example.rafee.dictionary1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Rafee on 1/16/2017.
 */

public class DictionaryShow extends AppCompatActivity {

    private TextView wordMeaning,word,synonymShow,antonymShow,sentenceShow;
    DatabaseObject myDB;
    private ImageButton favbutton;

    private static final String TABLE_NAME = "history";
    private static final String FAVOURITE_TABLE_NAME = "favourites";
    private static final String TABLE_ID_NAME = "history_id";
    private static final String FAVOURITE_TABLE_ID_NAME = "fav_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dictionary);

        favbutton = (ImageButton) findViewById(R.id.fav_btn);

        word = (TextView) findViewById(R.id.defineWord);

        wordMeaning = (TextView) findViewById(R.id.New);

        synonymShow = (TextView) findViewById(R.id.synshow_txt);
        antonymShow = (TextView) findViewById(R.id.antshow_txt);
        sentenceShow = (TextView) findViewById(R.id.sentence_txt);

        myDB = new DatabaseObject(this);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        //get selected word id.
        int dictionaryId = bundle.getInt("DICTIONARY_ID");

        final int id = dictionaryId + 1;

        //get word definition and meaning
        DatabaseRetrieve dbBackend = new DatabaseRetrieve(this);

        final DictionaryObject selectedWord = dbBackend.getWordById(id);

        //set word and meaning to layout
        word.setText(selectedWord.getWord());

        wordMeaning.setText(selectedWord.getMeaning());

        synonymShow.setText(selectedWord.getSynonym());
        antonymShow.setText(selectedWord.getAntonym());
        sentenceShow.setText(selectedWord.getSentence());

        //history update
        String query = "select * from history where history_id= "+id;

        Cursor cursor =  new DatabaseObject(this).getDbConnection().rawQuery(query, null);

        if(cursor.moveToFirst() == false){
            AddData(id,selectedWord.getWord(),selectedWord.getMeaning(),TABLE_NAME,TABLE_ID_NAME);
        }

        cursor.close();

        favbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDataToFavourites(id,selectedWord.getWord(),selectedWord.getMeaning());

                Log.d("Myapp",selectedWord.getWord());
            }
        });

    }

    public void addDataToFavourites(int id,String selectedWord,String wordMeaning){
        String query = "select * from favourites where fav_id= "+id;

        Cursor cursor =  new DatabaseObject(this).getDbConnection().rawQuery(query, null);

        if(cursor.moveToFirst() == false){
            AddData(id,selectedWord,wordMeaning,FAVOURITE_TABLE_NAME,FAVOURITE_TABLE_ID_NAME);
        }

        cursor.close();
    }

    public void AddData(int id,String word,String meaning,String TABLE_NAME,String ID_NAME){
        boolean isInserted = myDB.insertData(id,word,meaning,TABLE_NAME,ID_NAME);

        if(isInserted == true)
        {
            Toast.makeText(this,"Data Inserted",Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(this,"No data inserted",Toast.LENGTH_LONG).show();
    }
}
