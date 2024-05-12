package com.example.myapplication_test_2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class ResultActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val containerLayout = findViewById<LinearLayout>(R.id.questionsLayout)
        val scoreTextView = findViewById<TextView>(R.id.scoreTextView)

        val userAnswers = intent.getStringArrayExtra("userAnswers")
        val correctAnswers = intent.getStringArrayExtra("correctAnswers")

        var correctCount = 0

        if (userAnswers != null && correctAnswers != null) {
            for (i in userAnswers.indices) {
                val questionNumber = i + 1
                val userAnswer = userAnswers[i] ?: ""
                val correctAnswer = correctAnswers[i]

                val questionTextView = TextView(this)
                questionTextView.text = "Question $questionNumber:"
                containerLayout.addView(questionTextView)

                val userAnswerTextView = TextView(this)
                userAnswerTextView.text = "User Answer: $userAnswer"
                containerLayout.addView(userAnswerTextView)

                val correctAnswerTextView = TextView(this)
                correctAnswerTextView.text = "Correct Answer: $correctAnswer"
                containerLayout.addView(correctAnswerTextView)

                // Compare user answer with correct answer
                if (userAnswer == correctAnswer) {
                    // If answer is correct, increment correctCount
                    correctCount++
                    // Set correct answer text color to green
                    userAnswerTextView.setTextColor(resources.getColor(android.R.color.holo_green_dark))
                } else {
                    // Set incorrect answer text color to red
                    userAnswerTextView.setTextColor(resources.getColor(android.R.color.holo_red_dark))
                }
            }
        }

        // Calculate score and display
        val totalQuestions = userAnswers?.size ?: 0
        val score = (correctCount.toFloat() / totalQuestions.toFloat()) * 100
        scoreTextView.text = "Total Score: %.2f".format(score) // Display score with two decimal places
        scoreTextView.visibility = TextView.VISIBLE
    }
    fun goToMainActivity(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Close the current activity to prevent going back to it when pressing back
    }
}
