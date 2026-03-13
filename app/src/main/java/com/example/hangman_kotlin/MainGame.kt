package com.example.hangman_kotlin

class MainGame(private val word: String) {
    private val display: CharArray = CharArray(word.length) { '_' }
    private var lives: Int = 6
    private val guessed: MutableSet<Char> = mutableSetOf()
    private var hasWon: Boolean = false
    private  var status: String = "Playing"
    fun compare(guess: Char) {
        if (status != "Playing") return

        if (!guessed.add(guess)) {
            // already guessed
            return
        }

        if (word.contains(guess)) {
            for (i in word.indices) {
                if (word[i] == guess) {
                    display[i] = guess
                }
            }
        } else {
            loseLife()
        }

        if (!display.contains('_')) {
            status = "you win"
        } else if (lives <= 0) {
            status = "you lose"
            lives = 0
        }
    }

    fun loseLife() {
        lives--
    }

    fun resetLives() {
        lives = 6
    }

    fun resetGame() {
        for (i in display.indices) display[i] = '_'
        guessed.clear()
        lives = 6
        status = "playing"
    }

    fun getDisplay(): String {
        return display.joinToString(" ")
    }

    fun getLives(): Int {
        return lives
    }

    fun getStatus(): String {
        return status
    }
}