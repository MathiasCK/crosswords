package com.example.kryssord;

import java.util.Arrays;
import java.util.Random;

public class Controls {
    public static void deleteText() {
        String currentText = MainActivity.text.getText().toString();
        
        if (currentText.isEmpty()) {
            MainActivity.toast.setText("Select " + MainActivity.difficulty + " letters");
            return;
        }

        String updatedText = currentText.substring(0, currentText.length() - 1);
        MainActivity.text.setText(updatedText);
    }
    
    public static void generateHint() {
        Random generator = new Random();
        int randomIndex;
    
        do {
            randomIndex = generator.nextInt(MainActivity.correctWords.length);
        } while (MainActivity.usedWords.contains(MainActivity.correctWords[randomIndex]));
    
        String hint = MainActivity.correctWords[randomIndex];
        MainActivity.toast.setText(hint.substring(0, hint.length() - 1) + "*");
    }
    
    public static void check() {
        String inputText = MainActivity.text.getText().toString();
        MainActivity.toast.setText("");
    
        if (inputText.length() < MainActivity.difficulty || !inputText.contains("A")) {
            String toastMessage = "Word must contain " + MainActivity.difficulty + " letters and the letter A";
            MainActivity.toast.setText(toastMessage);
            return;
        }
    
        if (MainActivity.usedWords.contains(inputText)) {
            MainActivity.toast.setText("Word is already used");
            return;
        }
    
        if (!Arrays.asList(MainActivity.correctWords).contains(inputText)) {
            MainActivity.toast.setText("Wrong word");
            return;
        }
    
        MainActivity.toast.setText("Correct!");
        MainActivity.usedWords.add(inputText);
    
        int used = MainActivity.usedWords.size();
        int correct = MainActivity.correctWords.length;
        MainActivity.progress.setText(used + "/" + correct);
        MainActivity.numCompleted.append(inputText + " ");
    
        MainActivity.text.setText("");
    }
}
