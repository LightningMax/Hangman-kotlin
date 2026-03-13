package com.example.hangman_kotlin

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var hiddenWordText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        hiddenWordText = findViewById<TextView>(R.id.display_hidden_word)

        val text = "bonjour"
        var hiddenWord = generateDisplay(text)
        val letter = 'o'
        hiddenWord = compare(text, letter, hiddenWord)

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