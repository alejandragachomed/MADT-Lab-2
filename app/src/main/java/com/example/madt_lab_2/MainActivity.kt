package com.example.madt_lab_2

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var chosenOpt = "Words" // Default to Words


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Counter and Analyzer instances are created
        val counter = Counter ()
        val analyzer= Analyzer ()



        val spinner = findViewById<Spinner>(R.id.spinner)
        val spinnerOptions = resources.getStringArray(R.array.spinner_options)


        //An adapter is set for the spinner to display the available options
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        val inputText= findViewById<EditText>(R.id.inputText)
        val counterTextView = findViewById<TextView>(R.id.textView)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //chosenOpt variable is updated to the selected option ( in this case, Words or Chars)
                chosenOpt = spinnerOptions[position]

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        //Text validation is set up for the input field. If the input is empty, an error message is displayed.
        inputText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val input = s.toString().trim()
                if (input.isEmpty()) {
                    inputText.error = "Field cannot be empty"
                } else {
                    inputText.error = null
                }
            }

        }
        )

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            spinner.isActivated
            if (chosenOpt == "Words") {
                val words = counter.countWords(inputText)
                val wordsResult = words.toString()
                counterTextView.text = wordsResult
            } else if (chosenOpt == "Chars") {
                val chars = counter.countChars(inputText)
                val charsResult = chars.toString()
                counterTextView.text = charsResult
            }
            Toast.makeText(applicationContext, "Button Clicked", Toast.LENGTH_SHORT).show()
        }

        //ADDITIONAL FEATURES ---- WORD AND CHARS ANALYZER

        val wordTV= findViewById<TextView>(R.id.WtextView)

        val wordAnalyzer = findViewById<Button>(R.id.Wanalyzer)
        wordAnalyzer.setOnClickListener {
            val input = inputText.text.toString() // Get the input text
            val topN = 10 // Number of top words to display
            val wordFrequency = analyzer.analyzeWordFrequency(input, topN)

            val resultText = wordFrequency.joinToString("\n") { "${it.key}: ${it.value}" }
            wordTV.text = resultText
        }


        val charTV= findViewById<TextView>(R.id.CtextView)
        val charAnalyzer = findViewById<Button>(R.id.Canalyzer)

        charAnalyzer.setOnClickListener {
            val input = inputText.text.toString()
            val topN = 10 // Number of top characters to display
            val charFrequency = analyzer.analyzeCharFrequency(input, topN)

            val resultText = charFrequency.joinToString("\n") { "${it.key}: ${it.value}" }
            charTV.text = resultText
        }


    }
}