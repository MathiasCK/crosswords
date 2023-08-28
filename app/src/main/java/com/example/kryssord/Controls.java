package com.example.kryssord;

import java.util.Arrays;
import java.util.Random;

public class Controls {
    public static void deleteText() {
        String currentText = MainActivity.text.getText().toString();
        
        if (currentText.isEmpty()) {
            messageToast("Select " + MainActivity.difficulty + " letters");
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
        messageToast(hint.substring(0, hint.length() - 1) + "*");
    }
    
    private static void messageToast(String toastMessage) {
        MainActivity.toast.setText(toastMessage);
    }
    
    public static void check() {
        String inputText = MainActivity.text.getText().toString();
        MainActivity.toast.setText("");
    
        if (inputText.length() < MainActivity.difficulty) {
            messageToast("Word must contain " + MainActivity.difficulty + " letters");
            return;
        }
        
        if (!inputText.contains("A")) {
            messageToast("Word must contain the letter A");
            return;
        }
    
        if (MainActivity.usedWords.contains(inputText)) {
            messageToast("Word is already used");
            return;
        }
    
        if (!Arrays.asList(MainActivity.correctWords).contains(inputText)) {
            messageToast("Wrong word");
            return;
        }
    
        messageToast("Correct!");
        MainActivity.usedWords.add(inputText);
    
        int used = MainActivity.usedWords.size();
        int correct = MainActivity.correctWords.length;
        MainActivity.progress.setText(used + "/" + correct);
        MainActivity.numCompleted.append(inputText + " ");
    
        MainActivity.text.setText("");
    }
    
}
