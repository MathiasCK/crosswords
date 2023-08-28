package com.example.kryssord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static TextView text;
    static TextView toast;
    static TextView progress;
    static TextView numCompleted;
    static int difficulty;
    static String [] letters;
    static String [] correctWords;
    static ArrayList<String> usedWords = new ArrayList<>();
    int[] buttonIds = { R.id.button_A, R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4, R.id.button_5, R.id.button_6 };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.welcome);
    
        Button button_start = (Button)findViewById(R.id.button_start_game);
    
        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectDifficulty();
            }
        });
        
    }
    
    private void selectDifficulty() {
        setContentView(R.layout.settings);
        
        Resources res = getResources();
        letters = res.getStringArray(R.array.letters);
        
        setupDifficultyButton(R.id.button_3_letters, R.array.words_3_letters, 3);
        setupDifficultyButton(R.id.button_4_letters, R.array.words_4_letters, 4);
    }
    
    private void setupDifficultyButton(int buttonId, int wordsArrayId, final int difficultyLevel) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                difficulty = difficultyLevel;
                correctWords = getResources().getStringArray(wordsArrayId);
                startGame();
            }
        });
    }
    
    private void initButtons() {
        for (int i = 0; i < buttonIds.length; i++) {
            setupButtonClick(buttonIds[i], i);
        }
    }
    
    private void disableButtons() {
        for (int i = 0; i < buttonIds.length; i++) {
            disableButtonClick(buttonIds[i]);
        }
    }
    
    private void disableButtonClick(int buttonId) {
        Button button = findViewById(buttonId);

        button.setEnabled(false);
    }
    
    private void setupButtonClick(int buttonId, int i) {
        Button button = findViewById(buttonId);
    
        button.setText(letters[i]);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast.setText("");
                text.append(button.getText());
            }
        });
    }
    
    private void initControls() {
        Button button_delete = (Button)findViewById(R.id.button_delete);
        Button button_check = (Button)findViewById(R.id.button_check);
        Button button_hint = (Button)findViewById(R.id.button_hint);
    
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Controls.deleteText();
            }
        });
    
        button_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Controls.check();
                
                if (usedWords.size() == correctWords.length) {
                    toast.setText("Game finished");
                    disableButtons();
                }
            }
        });
        button_hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Controls.generateHint();
            }
        });
    }
    private void startGame() {
    
        setContentView(R.layout.activity_main);
    
        text = (TextView) findViewById(R.id.txt_letters);
        toast = (TextView) findViewById(R.id.txt_toast);
        numCompleted = (TextView) findViewById(R.id.txt_completed);
        progress = (TextView) findViewById(R.id.txt_progress);
    
        progress.setText(usedWords.size() + "/" + correctWords.length);
        toast.setText("Select " + difficulty + " letters");
    
        initButtons();
        initControls();
    
    }
}