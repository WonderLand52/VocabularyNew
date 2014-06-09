package com.rudenkoInc.vocabularynew.app;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class ExerciseActivity extends Activity implements View.OnClickListener {

    private final String LOG_TAG = "my logs";

    TextView tvNextWord;

    Button btnSubmit;

    EditText etTranslate;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise);

        tvNextWord = (TextView)findViewById(R.id.tvNextWord);
        etTranslate = (EditText)findViewById(R.id.etTranslate);

        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("myWords", null, null, null, null, null, null);

        Random rand = new Random();
        if(c != null){
            Log.d(LOG_TAG, String.valueOf(c.getCount()));

            if(c.moveToPosition(rand.nextInt(c.getCount()))){
                String nextWord = c.getString(c.getColumnIndex("translation"));
                tvNextWord.setText(nextWord);
            }
            c.close();
        } else
            Log.d(LOG_TAG, "Cursor is null");
    }

    @Override
    public void onClick(View view) {

    }
}
