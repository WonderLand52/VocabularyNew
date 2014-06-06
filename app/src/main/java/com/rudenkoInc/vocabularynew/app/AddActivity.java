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

public class AddActivity extends Activity implements View.OnClickListener{

    final String LOG_TAG = "my logs";

    EditText etOrigin, etTranslate;

    Button btnSave;

    DBHelper dbHelper;

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
        ContentValues cv = new ContentValues();

        SQLiteDatabase dataBase = dbHelper.getReadableDatabase();

        switch (view.getId()){
            case R.id.btnSave:
                Log.d(LOG_TAG, "---Saving words: ---");
                Log.d(LOG_TAG, "HELLO WORLD!");
                Log.d(LOG_TAG, "HELLO WAR!");


                cv.put("original", etOrigin.getText().toString());
                cv.put("translation", etTranslate.getText().toString());


                long rowID = dataBase.insert("myWords", null, cv);
                Log.d(LOG_TAG, "Row inserted, ID = " + rowID);
                break;


        }
    }

    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, "myWords", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("blya");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

        }
    }
}
