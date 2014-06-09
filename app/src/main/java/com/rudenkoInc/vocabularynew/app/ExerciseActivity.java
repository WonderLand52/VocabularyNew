package com.rudenkoInc.vocabularynew.app;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ExerciseActivity extends Activity implements View.OnClickListener {

    TextView tvNextWord;

    Button btnSubmit;

    EditText etTranslate;

    AddActivity aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise);

        tvNextWord = (TextView)findViewById(R.id.tvNextWord);
        etTranslate = (EditText)findViewById(R.id.etTranslate);

        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);

        SQLiteDatabase db = aa.dbHelper.getWritableDatabase();

        Cursor c = db.query("myTable", null, null, null, null, null, null);
        String nextWord = c.getString(c.getColumnIndex("translation"));
        tvNextWord.setText(nextWord);

    }

    @Override
    public void onClick(View view) {

    }
}
