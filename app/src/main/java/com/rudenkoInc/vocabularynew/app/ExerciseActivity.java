package com.rudenkoInc.vocabularynew.app;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExerciseActivity extends Activity implements View.OnClickListener {

    private final String LOG_TAG = "my logs";

    private TextView tvNextWord;
    private EditText etTranslate;

    private Random rand = new Random();

    private int wordsCount;
    private int count = 0;

    private List<String> results;

    private List<String> dbTranslation;
    private List<Integer> indexes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise);

        DBHelper dbHelper;
        SQLiteDatabase db;
        Cursor c;

        Button btnSubmit;

        tvNextWord = (TextView)findViewById(R.id.tvNextWord);
        etTranslate = (EditText)findViewById(R.id.etTranslate);

        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);

        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        c = db.query("myWords", null, null, null, null, null, null);

        dbTranslation = new ArrayList<String>();
        results = new ArrayList<String>();

        if(c != null){
            if(c.moveToFirst()) {
                do {
                    String str = c.getString(c.getColumnIndex("translation"));
                    dbTranslation.add(str);
                } while (c.moveToNext());
                wordsCount = c.getCount();
                Log.d(LOG_TAG, String.valueOf(wordsCount));
                c.close();
            }
        }

        indexes = new ArrayList<Integer>();
        for(int i = 0; i < wordsCount + 1; i++){
            indexes.add(i);
        }

        int randomIndexOnCreate = rand.nextInt(indexes.size());
        tvNextWord.setText(dbTranslation.get(randomIndexOnCreate));
        indexes.remove(randomIndexOnCreate);



    }

    @Override
    public void onClick(View view) {
        int randomIndexOnClick;
            if(etTranslate.getText() != null){
                if(count < wordsCount) {
                    Log.d(LOG_TAG, "count = " + count + ", wordsCount = " + wordsCount);
                    randomIndexOnClick = rand.nextInt(indexes.size());
                    Log.d(LOG_TAG, "indexes.size() = " + indexes.size());
                    String nextWord = dbTranslation.get(randomIndexOnClick);
                    tvNextWord.setText(nextWord);
                    results.add(etTranslate.getText().toString());
                    dbTranslation.remove(randomIndexOnClick);
                    indexes.remove(randomIndexOnClick);
                    ++count;
                } else {
                   Intent intent = new Intent(this, ResultsActivity.class);
                    intent.putStringArrayListExtra("results", (ArrayList<String>) getResults());
                    intent.putExtra("wordsCount", getWordsCount());
                   startActivity(intent);
                }
            } else
                Toast.makeText(this, "Fill translation!", Toast.LENGTH_SHORT).show();
        etTranslate.setText("");
        }

    public List<String> getResults(){
        return results;
    }

    public int getWordsCount(){
        return wordsCount;
    }


    }
