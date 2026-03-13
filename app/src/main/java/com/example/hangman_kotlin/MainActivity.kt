package com.example.hangman_kotlin

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    lateinit var hiddenWordText: TextView
    lateinit var guessInput: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        hiddenWordText = findViewById<TextView>(R.id.display_hidden_word)
        guessInput = findViewById<TextInputEditText>(R.id.guess_input)

        val text = "bonjour"
        var hiddenWord = generateDisplay(text)

        guessInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {

                val input = guessInput.text.toString()

                if (input.isNotEmpty()) {
                    val letter = input[0]
                    hiddenWord = compare(text, letter, hiddenWord)
                    hiddenWordText.text = hiddenWord
                }

                true
            } else {
                false
            }
        }

        hiddenWordText.text = hiddenWord

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun generateDisplay(word: String): String {
        var generatedWord = ""

        for(i in word.indices) {
            generatedWord += "_ "
        }

        return  generatedWord.trim()
    }

    fun compare(word: String, guess: Char, currentDisplay: String): String {
        var newDisplay = ""

        for (i in word.indices) {
            newDisplay += if (word[i] == guess) {
                "$guess "
            } else {
                "${currentDisplay[i*2]} " // keep previous guess or "_"
            }
        }

        return newDisplay.trim()
    }
}