package com.example.rafee.dictionary1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    DatabaseObject myDb;
    private ArrayAdapter<String> listAdapter;
    private Button button;
    private ListView listView;
    private static final String TABLE_NAME = "history";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_layout);

        listView = (ListView) findViewById(R.id.historylist);
        button = (Button) findViewById(R.id.clearhistory_btn);

        myDb = new DatabaseObject(this);

        getWords();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = myDb.deleteAllData(TABLE_NAME);

                if(deletedRows > 0){
                    Toast.makeText(HistoryActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();

                    getWords();
                }
                else
                    Toast.makeText(HistoryActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getWords(){
        DatabaseRetrieve dbBackend = new DatabaseRetrieve(this);

        String[] terms = dbBackend.dictionaryWords(TABLE_NAME);

        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, terms);

        listView.setAdapter(listAdapter);
    }
}
