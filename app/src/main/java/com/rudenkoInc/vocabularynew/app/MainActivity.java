package com.rudenkoInc.vocabularynew.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity implements View.OnClickListener{



    Button btnAdd, btnTraining;

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnTraining = (Button)findViewById(R.id.btnTraining);
        btnTraining.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                intent = new Intent(this, AddActivity.class);
                startActivity(intent);
                break;

            case R.id.btnTraining:
                intent = new Intent(this, ExerciseActivity.class);
                startActivity(intent);
                break;
        }
    }
}
