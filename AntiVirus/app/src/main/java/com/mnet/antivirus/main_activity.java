package com.mnet.antivirus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class main_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        setTheme(R.style.AppTheme_NoActionBar);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        final Button start = (Button)findViewById(R.id.start_button);
        final Button high_score = (Button)findViewById(R.id.high_score_button);
        final Switch sound_selector = (Switch)findViewById(R.id.sound_switch);

        start.setHapticFeedbackEnabled(true);
        high_score.setHapticFeedbackEnabled(true);
        sound_selector.setHapticFeedbackEnabled(true);


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

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                Intent AVgame = new Intent(getApplicationContext(), AVgame.class);
                startActivity(AVgame);
            }
        });
    }

}
