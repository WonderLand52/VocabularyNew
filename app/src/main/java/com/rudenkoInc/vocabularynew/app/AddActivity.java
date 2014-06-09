package com.rudenkoInc.vocabularynew.app;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends Activity implements View.OnClickListener{

    final String LOG_TAG = "my logs";

    EditText etOrigin, etTranslate;

    Button btnSave;

    DBHelper dbHelper;
    SQLiteDatabase dataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        etOrigin = (EditText)findViewById(R.id.etOrigin);
        etTranslate = (EditText)findViewById(R.id.etTranslate);

        btnSave = (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSave:
                ContentValues cv = new ContentValues();

                dataBase = dbHelper.getWritableDatabase();

                Log.d(LOG_TAG, "---Saving words: ---");

                cv.put("original", etOrigin.getText().toString());
                cv.put("translation", etTranslate.getText().toString());

                long rowID = dataBase.insert("myWords", null, cv);
                Log.d(LOG_TAG, "Row inserted, ID = " + rowID);

                Toast.makeText(this, "Word saved", Toast.LENGTH_SHORT).show();
        }

    }



}
