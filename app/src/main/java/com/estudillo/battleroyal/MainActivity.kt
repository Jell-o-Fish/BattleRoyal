package com.estudillo.battleroyal

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.ToggleButton
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    private var round = 99
    private var score = 0
    private lateinit var main: View
    private lateinit var scoreText: TextView
    private lateinit var roundLabel: TextView
    private lateinit var slotOne: TextView
    private lateinit var slotTwo: TextView
    private lateinit var slotThree: TextView
    private lateinit var enemySlot: TextView
    private lateinit var toggleMusic: ToggleButton
    private lateinit var restartGameButton: ImageButton
    private lateinit var respinOneButton: ImageButton
    private lateinit var respinTwoButton: ImageButton
    private lateinit var respinThreeButton: ImageButton
    private lateinit var respineEnemyButton: ImageButton
    private var player1: Int = (0..100).random()
    private var player2: Int = (0..100).random()
    private var player3: Int = (0..100).random()
    private var enemy: Int = (0..100).random()
    var mMediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        slotOne = findViewById(R.id.pOne_Health)
        slotTwo = findViewById(R.id.pTwo_Health)
        slotThree = findViewById(R.id.pThree_Health)
        enemySlot = findViewById(R.id.enemyHealth)
        roundLabel = findViewById(R.id.levelNumber)
        restartGameButton = findViewById(R.id.restartButton)
        toggleMusic = findViewById(R.id.audioButton)
        respinOneButton = findViewById(R.id.retryOne)
        scoreText = findViewById(R.id.scoreLabel)
        main = findViewById(R.id.mainView)

        spinEverything()
        playSound(toggleMusic)

        respinOneButton.setOnClickListener {
            respinOne()
        }

        toggleMusic.setOnClickListener {
            playSound(toggleMusic)
            changeToggle()
        }

        restartGameButton.setOnClickListener {
            restartGame()
        }

        slotOne.setOnClickListener {
            gameRuleForOne()
            spinOne()
            roundAlert()
        }

        slotTwo.setOnClickListener {
            gameRuleForTwo()
            spinTwo()
            roundAlert()
        }

        slotThree.setOnClickListener {
            gameRuleForThree()
            spinThree()
            roundAlert()
        }
    }

    private fun spinOne() {
        player1 = (0..100).random()
        slotOne.text = player1.toString()

    }

    private fun spinTwo() {
        player2 = (0..100).random()
        slotTwo.text = player2.toString()

    }

    private fun spinThree() {
        player3 = (0..100).random()
        slotThree.text = player3.toString()

    }

    private fun spinEnemy() {
        enemy = (0..100).random()
        enemySlot.text = enemy.toString()

    }


    private fun restartGame() {
        score = 0
        scoreText.text = "Score: $score"
        round = 99
      roundLabel.text = round.toString()
        spinEverything()
        main.setBackgroundResource(R.drawable.gradient_background)
    }

    private fun nextRound() {
        round--
        roundLabel.text = round.toString()
        score++
        scoreText.text = "Score: $score"
        spinEverything()
    }

    private fun spinEverything() {
        spinOne()
        spinTwo()
        spinThree()
        spinEnemy()
    }

    private fun gameRuleForOne() {
        if (player1 >= enemy) {
            nextRound()
        } else if (player1 < enemy) {
            restartGame()
        }
    }

    private fun gameRuleForTwo() {
        if (player2 >= enemy) {
            nextRound()
        } else {
            restartGame()
        }
    }

    private fun gameRuleForThree() {
        if (player3 >= enemy) {
            nextRound()
        } else {
            restartGame()
        }
    }

    private fun playSound(view: View) {
        if (mMediaPlayer == null) {
            mMediaPlayer= MediaPlayer.create(this, R.raw.kirby)
            mMediaPlayer!!.isLooping = true
            mMediaPlayer!!.start()
        } else if (mMediaPlayer != null && mMediaPlayer!!.isPlaying) {
            mMediaPlayer!!.pause()
        } else mMediaPlayer!!.start()
    }

    private fun changeToggle(){
        if (mMediaPlayer != null && mMediaPlayer!!.isPlaying) {
            toggleMusic.setBackgroundResource(R.drawable.ic_baseline_volume_up_24)
        } else {
            toggleMusic.setBackgroundResource(R.drawable.ic_baseline_volume_off_24)
        }
    }

    private fun roundAlert() {
        if (round == 75) {
            AlertDialog.Builder(this)
                .setTitle("Great Job!")
                .setMessage("A Quarter are out!")
                .setPositiveButton("Continue") { _, _ ->
                    main.setBackgroundResource(R.drawable.gradient_background_two)
                }
                .setCancelable(false)
                .show()
        }

        if (round == 50) {
            AlertDialog.Builder(this)
                .setTitle("Amazing!")
                .setMessage("Another Quarter are out!")
                .setPositiveButton("Continue") { _, _ ->
                    main.setBackgroundResource(R.drawable.gradient_background_three)
                }
                .setCancelable(false)
                .show()
        }

        if (round == 10) {
            AlertDialog.Builder(this)
                .setTitle("Final Five!!")
                .setMessage("This is it! Die or win.")
                .setPositiveButton("Continue") { _, _ ->
                    main.setBackgroundResource(R.drawable.gradient_background_final)
                }
                .setCancelable(false)
                .show()
        }

        if (round == 1) {
            AlertDialog.Builder(this)
                .setTitle("You did it!!")
                .setMessage("Victory Royal")
                .setPositiveButton("New Game") { _, _ ->
                    main.setBackgroundResource(R.drawable.gradient_background)
                }
                .setCancelable(false)
                .show()
        }
    }

    private fun respinOne() {
        if (score >= 3) {
            respinOneButton.setBackgroundResource(R.drawable.ic_baseline_monetization_on_24)
            spinOne()
        } else if ( score < 3) {
            respinOneButton.setBackgroundResource(R.drawable.ic_baseline_rotate_right_blank_24)
        } else {
            respinOneButton.setBackgroundResource(R.drawable.ic_baseline_rotate_right_blank_24)
        }
    }

}