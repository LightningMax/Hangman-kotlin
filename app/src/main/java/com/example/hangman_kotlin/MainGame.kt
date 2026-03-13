package com.example.hangman_kotlin

class MainGame(private val word: String) {
    private val display: CharArray = CharArray(word.length) { '_' }

    fun compare(guess: Char,) {
        for (i in word.indices) {
            if (word[i] == guess) {
                display[i] = guess
            }
        }
    }

    fun getDisplay(): String {
        return display.joinToString(" ")
    }
}