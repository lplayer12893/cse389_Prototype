package com.mnet.antivirus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.design.widget.Snackbar;
import android.view.inputmethod.InputMethodManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class scores extends AppCompatActivity {
    //TODO fix bug where listView isn't populated from on first start
    static DBAdapter antiDb;
    Cursor c;
    EditText name;
    Button submit;
    Button clear;
    Button retry;
    protected static long score = -1;//TODO fix scores bug

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        openDB();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        populateListView();
        c = antiDb.getMinScore();

        name = (EditText)findViewById(R.id.editText);
        submit = (Button)findViewById(R.id.submit);
        clear = (Button)findViewById(R.id.clear);
        retry = (Button)findViewById(R.id.retry);

        submit.setHapticFeedbackEnabled(true);
        clear.setHapticFeedbackEnabled(true);
        retry.setHapticFeedbackEnabled(true);
        setVisibility();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                onClick_submit();

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                name.setText("");
            }
        });

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retry.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                Intent game = new Intent(getApplicationContext(), AVgame.class);
                startActivity(game);
            }
        });
    }

    /**
     *  open Database for writing
     */
    private  void openDB() {
        antiDb = new DBAdapter(this);
        antiDb.open();
    }

    /**
     * Close database
     */
    private void closeDB() {
        antiDb.close();
    }

    /**
     * Populates the ListView on the
     */
    private void populateListView() {
        Cursor cursor = antiDb.getAllRows();
        if(cursor.getCount() == 0)
            prePopulate();

        while(cursor.getCount() > 10){
            antiDb.deleteRow(c.getInt(0));
        }

        String[] fromDB = new String[] {DBAdapter.KEY_NAME, DBAdapter.KEY_SCORE};
        int[] toViews = new int[] {R.id.playerName, R.id.playerScore};

        SimpleCursorAdapter Adapter;
        Adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.name_list, cursor, fromDB, toViews, 1);

        ListView list = (ListView)findViewById(R.id.leaderBoard);
        list.setAdapter(Adapter);
    }

    /**
     * Submits the data and updates the rows in the database
     */
    private void onClick_submit() {
        if(!TextUtils.isEmpty(name.getText().toString())) {
            antiDb.updateRow(c.getInt(0), name.getText().toString(), score);
            hideKeyBoard();
            name.setVisibility(View.GONE);
            submit.setVisibility(View.GONE);
            clear.setVisibility(View.GONE);
        }
        else {
            Snackbar.make(submit, "To submit your High Score please type in your name", Snackbar.LENGTH_LONG).show();
            return;
        }
        populateListView();
        closeDB();
    }

    /**
     * Hides the KeyBoard from the window
     */
    private void hideKeyBoard() {
        InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        keyboard.hideSoftInputFromWindow(name.getWindowToken(),0);
    }

    /**
     * Sets the visibility of UI elements accordingly, -1 means the Scores activity was started from
     * main_activity
     */
    private void setVisibility() {
        if( score == -1) {
            return;
        }
        if( score > c.getLong(1) ) {
            name.setVisibility(View.VISIBLE);
            submit.setVisibility(View.VISIBLE);
            clear.setVisibility(View.VISIBLE);
            retry.setVisibility(View.VISIBLE);
        }
        else {
            retry.setVisibility(View.VISIBLE);
        }
    }

    /**
     * pre-populates the database with info
     */
    private void prePopulate() {
        int i = 0;
        String[] names = {"Benjamin", "Joshua", "Lucas", "Manuel", "Ana", "Ariel", "Christian", "Calynn", "Kyle", "Cristal"};
        long[] scores = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};

        for (; i < 10; i++) {
                antiDb.insertRow(names[i], scores[i]);
        }
    }
}
