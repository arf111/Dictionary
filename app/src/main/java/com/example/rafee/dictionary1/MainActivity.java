package com.example.rafee.dictionary1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText filterText;
    private Button buttonHistory, buttonFavourite,buttonQuiz;
    private ListView itemList;

    private static final String TABLE_NAME = "dictionary";
    private static final String HISTORY_TABLE_NAME = "history";

    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        buttonHistory = (Button) findViewById(R.id.history_btn);

        buttonFavourite = (Button) findViewById(R.id.favourites_btn);

        buttonQuiz = (Button) findViewById(R.id.quiz_btn);

        filterText = (EditText) findViewById(R.id.searchView1);

        itemList = (ListView) findViewById(R.id.listViewWords);

        DatabaseRetrieve dbBackend = new DatabaseRetrieve(this);//

        final String[] terms = dbBackend.dictionaryWords(TABLE_NAME);//get all the words and save it in terms

        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, terms);

        itemList.setAdapter(listAdapter);

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "Position " + position, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainActivity.this, DictionaryShow.class);

                String selectedFromList = (String) itemList.getItemAtPosition(position);

                int index = 0;

                for(int i=0; i<terms.length; i++)
                {
                    if (terms[i].equals(selectedFromList)) {
                        index = i;
                        break;
                    }
                }

                intent.putExtra("DICTIONARY_ID",index);

                startActivity(intent);
            }
        });

        buttonHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);

                startActivity(intent);
            }
        });

        buttonFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavouritesActivity.class);

                startActivity(intent);
            }
        });

        buttonQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseRetrieve dbBackend = new DatabaseRetrieve(MainActivity.this);

                ArrayList<Integer> allID = dbBackend.getAllID(HISTORY_TABLE_NAME);

                if(allID.size() < 5){
                   Toast.makeText(getApplicationContext(),"Learn atleast 5 words",Toast.LENGTH_LONG).show();
                }
                else{
                    Intent intent = new Intent(MainActivity.this, QuizActivity.class);

                    startActivity(intent);
                }
            }
        });

        //searching for word
        filterText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.this.listAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
