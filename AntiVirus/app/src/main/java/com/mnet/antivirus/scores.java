package com.mnet.antivirus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class scores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        populateListView();
    }

    private void populateListView() {
        String[] playerList = {"Mike", "John", "Brandon", "Joshua", "Sofia", "Ben", "Keri"};
        Integer[] scoreList = {10, 10, 9, 4, 5, 7, 9};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(), R.layout.name_list, R.id.playerName, playerList);
        ArrayAdapter<Integer> adapera = new ArrayAdapter<>(getBaseContext(), R.layout.name_list, R.id.playerScore, scoreList);
        ListView list = (ListView)findViewById(R.id.leaderBoard);
        list.setAdapter(adapter);
    }
}
