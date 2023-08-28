package com.example.kryssord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView text;
    TextView toast;
    TextView progress;
    TextView answerBox;
    TextView numCompleted;
    
    @Override
    protected void onSaveInstanceState(Bundle outstate) {
        super.onSaveInstanceState(outstate);
        
        TextView saveText = (TextView) text;
        outstate.putString("text",text.getText().toString());
        
        TextView saveToast = (TextView) toast;
        outstate.putString("toast",toast.getText().toString());
        
        TextView saveProgress = (TextView) progress;
        outstate.putString("progress",progress.getText().toString());
        
        TextView saveAnswer = (TextView) answerBox;
        outstate.putString("answerBox",answerBox.getText().toString());
        
        TextView saveNumCompleted = (TextView) numCompleted;
        outstate.putString("numCompleted",numCompleted.getText().toString());
    }
    
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        
        TextView saveText = (TextView) text;
        saveText.setText(savedInstanceState.getString("text"));
        
        TextView saveToast = (TextView) toast;
        saveToast.setText(savedInstanceState.getString("toast"));
        
        TextView saveProgress = (TextView) progress;
        saveProgress.setText(savedInstanceState.getString("progress"));
        
        TextView saveAnswer = (TextView) answerBox;
        saveAnswer.setText(savedInstanceState.getString("answer"));
        
        TextView saveUsed = (TextView) numCompleted;
        saveUsed.setText(savedInstanceState.getString("used"));
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.welcome);
    
        Button button_start = (Button)findViewById(R.id.button_start_game);
    
        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });
        
    }
    
    private void startGame() {
    
        setContentView(R.layout.activity_main);
        
        Resources res = getResources();
        
        String [] letters = res.getStringArray(R.array.letters);
        String [] correctWords = res.getStringArray(R.array.words);
        ArrayList <String> usedWords = new ArrayList<>();
    
        Button button_left_bottom = (Button)findViewById(R.id.button_left_bottom);
        Button button_left_top = (Button)findViewById(R.id.button_left_top);
        Button button_middle = (Button)findViewById(R.id.button_middle);
        Button button_top = (Button)findViewById(R.id.button_top);
        Button button_bottom = (Button)findViewById(R.id.button_bottom);
        Button button_right_bottom = (Button)findViewById(R.id.button_right_bottom);
        Button button_right_top = (Button)findViewById(R.id.button_right_top);
        Button button_delete = (Button)findViewById(R.id.button_delete);
        Button button_answer = (Button)findViewById(R.id.button_answer);
        Button button_check = (Button)findViewById(R.id.button_check);
        Button button_hint = (Button)findViewById(R.id.button_hint);
    
        text = (TextView) findViewById(R.id.txt_answer);
        toast = (TextView) findViewById(R.id.error);
        answerBox = (TextView) findViewById(R.id.answerBox);
        numCompleted = (TextView) findViewById(R.id.numCompleted);
        progress = (TextView) findViewById(R.id.progress);
    
        progress.setText(usedWords.size() +"/"+correctWords.length);
    
        Button[] buttons = { button_middle, button_left_bottom, button_left_top, button_top, button_bottom, button_right_bottom, button_right_top };
    
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText(letters[i]);
        }
    
        button_left_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast.setText("");
                text.append(button_left_bottom.getText());
            }
        });
    
        button_left_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast.setText("");
                text.append(button_left_top.getText());
            }
        });
    
        button_middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast.setText("");
                text.append(button_middle.getText());
            }
        });
    
        button_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast.setText("");
                text.append(button_top.getText());
            }
        });
    
        button_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast.setText("");
                text.append(button_bottom.getText());
            }
        });
    
        button_right_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast.setText("");
                text.append(button_right_bottom.getText());
            }
        });
    
        button_right_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast.setText("");
                text.append(button_right_top.getText());
            }
        });
    
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentText = text.getText().toString();
    
                if (!currentText.isEmpty()) {
                    String updatedText = currentText.substring(0, currentText.length() - 1);
                    text.setText(updatedText);
                }
            }
        });
    
        button_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = text.getText().toString();
                toast.setText("");
                
                if (inputText.length() < 4 || !inputText.contains("A")) {
                    toast.setText("Word must contain 4 letters and the letter A");
                    return;
                }
                
                if (usedWords.contains(inputText)) {
                    toast.setText("Word is already used");
                    return;
                }
                
                if (!Arrays.asList(correctWords).contains(inputText)) {
                    toast.setText("Wrong word");
                    return;
                }
                
                toast.setText("Correct!");
                usedWords.add(inputText);
    
                int used = usedWords.size();
                int correct = correctWords.length;
                progress.setText(used + "/" + correct);
                numCompleted.append(inputText + " ");
    
                text.setText("");
            }
        });
    
        button_hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
    
                Random generator = new Random();
                int randomIndex;
                
                do {
                    randomIndex = generator.nextInt(correctWords.length);
                } while (usedWords.contains(correctWords[randomIndex]));
    
                String hint = correctWords[randomIndex];
                toast.setText(hint.substring(0, hint.length() - 1) + "*");
            }
        });
    
        button_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerBox.setText("");
                
                for (String i : correctWords) {
                    answerBox.append(i + ",");
                }
            }
        });
    }
}