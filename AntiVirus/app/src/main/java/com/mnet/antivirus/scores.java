package com.mnet.antivirus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
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

    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        populateListView();

        //TODO add conditionals to reveal or hide UI elements
        //TODO add a score counter to input actual scores
        //TODO find a way to limit database to only ten elements and reuse slots
        //TODO order elements in listview by in descending order by scores

        name = (EditText)findViewById(R.id.editText);
        final Button submit = (Button)findViewById(R.id.submit);
        final Button retry = (Button)findViewById(R.id.retry);

        submit.setHapticFeedbackEnabled(true);
        retry.setHapticFeedbackEnabled(true);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                onClick_submit();
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

    private void populateListView() {
        Cursor cursor = main_activity.antiDb.getAllRows();

        String[] fromDB = new String[] {DBAdapter.KEY_NAME, DBAdapter.KEY_SCORE};
        int[] toViews = new int[] {R.id.playerName, R.id.playerScore};

        SimpleCursorAdapter Adapter;
        Adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.name_list, cursor, fromDB, toViews, 1);

        ListView list = (ListView)findViewById(R.id.leaderBoard);
        list.setAdapter(Adapter);
    }

    private void onClick_submit() {
        if(!TextUtils.isEmpty(name.getText().toString())) {
            main_activity.antiDb.insertRow(name.getText().toString(), 1000);
            System.out.println("submitted new score to db");
        }
        populateListView();
    }
}
