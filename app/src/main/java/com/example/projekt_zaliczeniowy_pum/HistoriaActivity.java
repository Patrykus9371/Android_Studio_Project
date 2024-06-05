package com.example.projekt_zaliczeniowy_pum;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HistoriaActivity extends AppCompatActivity {

    private void openHistoryActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    private ListView historyListView;
    private ArrayAdapter<String> adapter;
    private List<String> historyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historia);

        historyListView = findViewById(R.id.history_list_view);
        historyList = loadHistory();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, historyList);
        historyListView.setAdapter(adapter);


        Button backButton = findViewById(R.id.button_back);
       backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHistoryActivity();
            }
        });
    }

    private List<String> loadHistory() {
        SharedPreferences prefs = getSharedPreferences("CalculatorHistory", MODE_PRIVATE);
        String historyString = prefs.getString("history", "");
        if (!historyString.isEmpty()) {
            String[] historyArray = historyString.split(";");
            return new ArrayList<>(Arrays.asList(historyArray));
        } else {
            return new ArrayList<>();
        }
    }
}