package com.example.rafee.dictionary1;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    DatabaseObject myDB;
    private TextView scoreCard;
    private TextView mQuestionView;
    private TextView mMeaningView;

    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonChoice4;

    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;

    private static final String HISTORY_TABLE_NAME = "history";

    private ArrayList<String> quizWords,quizMeanings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        scoreCard = (TextView) findViewById(R.id.score_txt);

        mButtonChoice1 = (Button) findViewById(R.id.opt_1);
        mButtonChoice2 = (Button) findViewById(R.id.opt_2);
        mButtonChoice3 = (Button) findViewById(R.id.opt_3);
        mButtonChoice4 = (Button) findViewById(R.id.opt_4);

        mMeaningView = (TextView) findViewById(R.id.meaning_txt);

        myDB = new DatabaseObject(this);

        DatabaseRetrieve dbBackend = new DatabaseRetrieve(this);

        ArrayList<Integer> allID = dbBackend.getAllID(HISTORY_TABLE_NAME);

        quizWords = new ArrayList<String>();
        quizMeanings = new ArrayList<String>();

        for(Integer indx : allID)
        {
            Log.d("Myapp",indx.toString());

            String query = "select * from history where history_id= " + indx;

            Cursor cursor = myDB.getDbConnection().rawQuery(query, null);

            if(cursor.moveToFirst() == true){
                    String word = cursor.getString(cursor.getColumnIndexOrThrow("word"));

                    String meaning = cursor.getString(cursor.getColumnIndexOrThrow("meaning"));

                    quizWords.add(word);
                    quizMeanings.add(meaning);
            }
            cursor.close();
        }

        updateQuestion();

        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mButtonChoice1.getText() == mAnswer ){
                    mScore += 1;
                    updateScore(mScore);

                    updateQuestion();

                    if(mQuestionNumber<=4)
                        Toast.makeText(QuizActivity.this,"correct",Toast.LENGTH_LONG).show();
                }else{
                    if(mQuestionNumber<=4)
                      Toast.makeText(QuizActivity.this,"wrong",Toast.LENGTH_LONG).show();
                    updateQuestion();
                }

            }
        });

        mButtonChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mButtonChoice2.getText() == mAnswer){
                    mScore += 1;
                    updateScore(mScore);

                    updateQuestion();

                    if(mQuestionNumber<=4)
                        Toast.makeText(QuizActivity.this,"correct",Toast.LENGTH_LONG).show();
                }else{
                    if(mQuestionNumber<=4)
                        Toast.makeText(QuizActivity.this,"wrong",Toast.LENGTH_LONG).show();
                    updateQuestion();
                }

            }
        });
        mButtonChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mButtonChoice3.getText() == mAnswer){
                    mScore += 1;
                    updateScore(mScore);

                    updateQuestion();

                    if(mQuestionNumber<=4)
                        Toast.makeText(QuizActivity.this,"correct",Toast.LENGTH_LONG).show();
                }else{
                    if(mQuestionNumber<=4)
                        Toast.makeText(QuizActivity.this,"wrong",Toast.LENGTH_LONG).show();
                    updateQuestion();
                }

            }
        });
        mButtonChoice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mButtonChoice4.getText() == mAnswer){
                    mScore += 1;
                    updateScore(mScore);

                    updateQuestion();

                    if(mQuestionNumber<=4)
                        Toast.makeText(QuizActivity.this,"correct",Toast.LENGTH_LONG).show();
                }
                else{
                    if(mQuestionNumber<=4)
                        Toast.makeText(QuizActivity.this,"wrong",Toast.LENGTH_LONG).show();
                    updateQuestion();
                }

            }
        });
    }
    private void updateQuestion(){
             int id1 = 0,id2=1,id3=2,id4=3;

             if(mQuestionNumber == 0)
             {
                 id1 = 0;id2 = 1;id3 = 2;id4 = 3;
             }
             else if(mQuestionNumber == 1)
             {
                 id1 = 3;id2 = 1;id3 = 2;id4 = 4;
             }
             else if(mQuestionNumber == 2)
             {
                 id1 = 1;id2 = 3;id3 = 2;id4 = 4;
             }
             else if(mQuestionNumber == 3)
             {
                 id1 = 1;id2 = 3;id3 = 2;id4 = 4;
             }
             else if(mQuestionNumber == 4)
             {
                 id1 = 0;id2 = 1;id3 = 2;id4 = 4;
             }

             mButtonChoice1.setText(quizWords.get(id1));
             mButtonChoice2.setText(quizWords.get(id2));
             mButtonChoice3.setText(quizWords.get(id3));
             mButtonChoice4.setText(quizWords.get(id4));

        if(mQuestionNumber <= 4)
        {
            mMeaningView.setText(quizMeanings.get(mQuestionNumber));
            mAnswer = quizWords.get(mQuestionNumber);
            mQuestionNumber++;
        }
        else if(mQuestionNumber >=5)
            Toast.makeText(QuizActivity.this,"Total Score "+mScore,Toast.LENGTH_LONG).show();

    }

    private void updateScore(int point){
        scoreCard.setText("" + point);
    }
}
