package com.example.rafee.dictionary1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class FavouritesActivity extends AppCompatActivity {
    private Button button;
    DatabaseObject myDB;

    private ListView favouriteList;
    private static final String FAVOURITE_TABLE_NAME = "favourites";
    private ArrayAdapter<String> listAdapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_favourites);

        button = (Button) findViewById(R.id.clearfav_btn);

        myDB = new DatabaseObject(this);

        favouriteList = (ListView) findViewById(R.id.favrytlist);

        getWords();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = myDB.deleteAllData(FAVOURITE_TABLE_NAME);

                if(deletedRows > 0){
                    Toast.makeText(FavouritesActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();

                    getWords();
                }
                else
                    Toast.makeText(FavouritesActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();

            }
        });
    }
    public void getWords() {
        DatabaseRetrieve dbBackend = new DatabaseRetrieve(this);//

        String[] favrytWords = dbBackend.dictionaryWords(FAVOURITE_TABLE_NAME);//get all the words and save it in terms

        listAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, favrytWords);

        favouriteList.setAdapter(listAdapter3);

    }
}

