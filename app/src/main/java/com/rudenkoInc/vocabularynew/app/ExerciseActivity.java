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

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ExerciseActivity extends Activity implements View.OnClickListener {

    private final String LOG_TAG = "my logs";

    TextView tvNextWord;
    Button btnSubmit;
    EditText etTranslate;

    DBHelper dbHelper;
    SQLiteDatabase db;
    Cursor c;

    Random rand = new Random();
    int count = 0;

    static List<String> results;

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
        results = new LinkedList<String>();

        if(c != null){
            Log.d(LOG_TAG, String.valueOf(c.getCount()));
            if(c.moveToPosition(rand.nextInt(c.getCount()))){
                setTvNextWord(tvNextWord, c);
            }
        } else
            Log.d(LOG_TAG, "Cursor is null");
    }

    @Override
    public void onClick(View view) {
            if(c != null){
                if(c.moveToPosition(rand.nextInt(c.getCount()))){
                    if(count < c.getCount()) {
                        setTvNextWord(tvNextWord, c);
                        results.add(etTranslate.getText().toString());
                        count++;

                    } else {
                        Intent intent = new Intent(this, ResultsActivity.class);
                        startActivity(intent);
                    }
                }
            } else
                Toast.makeText(this, "Cursor is empty", Toast.LENGTH_SHORT).show();


    }

    private void setTvNextWord(TextView tv, Cursor c){
        String nextWord = c.getString(c.getColumnIndex("translation"));
        tv.setText(nextWord);
    }

    public List<String> getOriginals(){
        return results;
    }






    }
