package com.example.madt_lab_2;

import android.widget.TextView;
public class Counter {

    public String convert ( TextView textView){
        return textView.getText().toString();
    }

    public int countWords ( TextView textView) {
        String inputText= convert(textView);
        String separators = "[\\s,.]+";
        String[] words = inputText.split(separators);
        return words.length;
    }

    public int countChars ( TextView textView){
        String inputText= convert (textView);
        return inputText.length();
    }






}
