package com.rudenkoInc.vocabularynew.app;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ResultsActivity extends Activity implements View.OnClickListener {

    private final String LOG_TAG = "my logs";
    private static final int DEFAULT_VALUE = 0;

    TextView tvPercent;
    int count = 0;
    int countResult;

    DBHelper dbHelper = new DBHelper(this);
    SQLiteDatabase db;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);

        tvPercent = (TextView)findViewById(R.id.tvPercent);

        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        c = db.query("myWords", null, null, null, null, null, null);

        Intent intent = getIntent();

        countResult = intent.getIntExtra("wordsCount", DEFAULT_VALUE);

        if(c != null){
            if(c.moveToFirst()){
                do{
                    String dbOriginal = c.getString(c.getColumnIndex("original"));
                    for(String result: intent.getStringArrayListExtra("results")){
                        if(dbOriginal.equalsIgnoreCase(result))
                            count++;
                    }
                }while (c.moveToNext());
                c.close();
            }
        } else
            Toast.makeText(this, "Cursor is null", Toast.LENGTH_SHORT).show();

        tvPercent.setText(String.valueOf(countResult(count, countResult)));
    }

    double countResult(int count, int countResult){
        Log.d(LOG_TAG, String.valueOf(count));
        Log.d(LOG_TAG, String.valueOf(countResult));
        return count * 100/countResult;
    }

    @Override
    public void onClick(View v) {

    }
}
