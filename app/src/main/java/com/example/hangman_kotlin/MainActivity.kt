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
    lateinit var game: MainGame
    lateinit var hiddenWordText: TextView
    lateinit var guessInput: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        hiddenWordText = findViewById<TextView>(R.id.display_hidden_word)
        guessInput = findViewById<TextInputEditText>(R.id.guess_input)

        game = MainGame("bonjour")

        guessInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {

                val input = guessInput.text.toString()

                if (input.isNotEmpty()) {
                    val letter = input[0]
                    game.compare(letter)
                    hiddenWordText.text = game.getDisplay()

                    guessInput.text?.clear()
                }

                true
            } else {
                false
            }
        }

        hiddenWordText.text = game.getDisplay()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}