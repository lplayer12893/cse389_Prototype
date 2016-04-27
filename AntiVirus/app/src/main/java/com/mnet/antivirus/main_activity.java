package com.mnet.antivirus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

public class main_activity extends AppCompatActivity {

    static DBAdapter antiDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Artificial Delay
        //TO BE DELETED
        long total = 4000; // 4 sec.
        long timestampStart = System.currentTimeMillis();
        // TODO: Do extra stuff here
        long elapsed = System.currentTimeMillis() - timestampStart;
        long remaining = total - elapsed;

        try {
            Thread.sleep(remaining, 0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //CODE Starts HERE

        //Things to initialize while loading UI
        openDB();
        //Main program Starts here
        setTheme(R.style.AppTheme_NoActionBar);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        final Button start = (Button)findViewById(R.id.start_button);
        final Button high_score = (Button)findViewById(R.id.high_score_button);
        final Switch sound_selector = (Switch)findViewById(R.id.sound_switch);
        final ImageButton help = (ImageButton)findViewById(R.id.help_button);

        start.setHapticFeedbackEnabled(true);
        high_score.setHapticFeedbackEnabled(true);
        sound_selector.setHapticFeedbackEnabled(true);
        help.setHapticFeedbackEnabled(true);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                Intent game = new Intent(getApplicationContext(), AVgame.class);
                startActivity(game);
            }
        });

        high_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                high_score.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                Intent scores = new Intent(getApplicationContext(), scores.class);
                startActivity(scores);
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                help.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                //TODO code that calls the help view/activity
            }
        });
        sound_selector.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sound_selector.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                if (isChecked) {
                    // TODO: 3/19/16 enabled
                } else {
                    // TODO: 3/19/16  disabled 
                }
            }
        });

    }

    private  void openDB() {
        antiDb = new DBAdapter(this);
        antiDb.open();
    }

}
