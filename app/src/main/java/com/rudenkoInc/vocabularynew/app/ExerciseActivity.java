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
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ExerciseActivity extends Activity implements View.OnClickListener {

    private final String LOG_TAG = "my logs";

    private TextView tvNextWord;
    Button btnSubmit;
    EditText etTranslate;

    DBHelper dbHelper;
    SQLiteDatabase db;
    Cursor c;

    Random rand = new Random();

    static int wordsCount;
    int count = 1;

    static List<String> results;
    static List<String> dbTranslation;
    List<Integer> indexes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise);

        tvNextWord = (TextView)findViewById(R.id.tvNextWord);
        etTranslate = (EditText)findViewById(R.id.etTranslate);

        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);

        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        c = db.query("myWords", null, null, null, null, null, null);

        wordsCount = c.getCount();

        dbTranslation = new ArrayList<String>();
        results = new LinkedList<String>();

        if(c != null){
            if(c.moveToFirst()) {
                do {
                    String str = c.getString(c.getColumnIndex("translation"));
                    dbTranslation.add(str);
                } while (c.moveToNext());
                c.close();
            }
        }

        indexes = new ArrayList<Integer>();
        for(int i = 0; i < wordsCount; i++){
            indexes.add(i);
        }

        int randomIndexOnCreate = rand.nextInt(indexes.size());
        tvNextWord.setText(dbTranslation.get(randomIndexOnCreate));
        indexes.remove(randomIndexOnCreate);
        Log.d(LOG_TAG, String.valueOf(indexes.size()));
    }

    @Override
    public void onClick(View view) {
        int randomIndex;
            if(etTranslate.getText() != null){
                if(count < wordsCount) {
                    randomIndex = rand.nextInt(indexes.size());
                    String nextWord = dbTranslation.get(randomIndex);
                    tvNextWord.setText(nextWord);
                    results.add(etTranslate.getText().toString());
                    Log.d(LOG_TAG, String.valueOf(results.size()));
                    dbTranslation.remove(randomIndex);
                    indexes.remove(randomIndex);
                    count++;
                } else {
                   Intent intent = new Intent(this, ResultsActivity.class);
                   startActivity(intent);
                }
            } else
                Toast.makeText(this, "Fill translation!", Toast.LENGTH_SHORT).show();

        for(String result: results){
            Log.d(LOG_TAG, result);
        }

        }
    }
