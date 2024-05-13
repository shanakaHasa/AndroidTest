package com.example.myapplication_test_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val questions = arrayOf(
        "What is the result of 20 + 77?",
        "What is the result of 77 - 20?",
        "What is the result of 15 * 6?",
        "What is the result of 80 / 4?",
        "What is the result of 45 + 63?",
        "What is the result of 102 - 57?",
        "What is the result of 25 * 3?",
        "What is the result of 90 / 3?",
        "What is the result of 35 + 82?",
        "What is the result of 144 - 72?"
    )

    private val correctAnswers = arrayOf("97", "57", "90", "20", "108", "45", "75", "30", "117", "72"s)

    private val userAnswers = arrayOfNulls<String>(questions.size)

    private var currentQuestionIndex = 0

    private lateinit var questionTextView: TextView
    private lateinit var answerEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var showResultButton: Button
    private lateinit var bannerTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionTextView = findViewById(R.id.questionTextView)
        answerEditText = findViewById(R.id.answerEditText)
        submitButton = findViewById(R.id.submitButton)
        showResultButton = findViewById(R.id.showResultButton)
        bannerTextView = findViewById(R.id.bannerTextView)

        displayQuestion()

        val resetButton: Button = findViewById(R.id.resetButton)
        resetButton.setOnClickListener {
            resetAnswer()
            displayQuestion()
        }

        showResultButton.setOnClickListener {
            showResult()
        }
    }

    private fun displayQuestion() {
        if (currentQuestionIndex < questions.size) {
            questionTextView.text = questions[currentQuestionIndex]
            answerEditText.setText("")
            answerEditText.isEnabled = true
            submitButton.isEnabled = true
            bannerTextView.visibility = View.GONE
        } else {
            answerEditText.visibility = View.GONE
            submitButton.visibility = View.GONE
            showResultButton.visibility = View.VISIBLE
            bannerTextView.visibility = View.VISIBLE
        }
    }

    fun submitAnswer(view: View) {
        val userAnswer = answerEditText.text.toString().trim()
        userAnswers[currentQuestionIndex] = userAnswer
        currentQuestionIndex++
        if (currentQuestionIndex >= questions.size) {
            displayQuestion()
        } else {
            displayQuestion()
        }
    }
    // reset the answer
    private fun resetAnswer() {
        userAnswers[currentQuestionIndex] = null
        answerEditText.setText("")
        currentQuestionIndex = 0 // Reset currentQuestionIndex to 0
    }


    private fun showResult() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("userAnswers", userAnswers)
        intent.putExtra("correctAnswers", correctAnswers)
        intent.putExtra("score", calculateScore())
        startActivity(intent)
    }

    private fun calculateScore(): Int {
        var correctCount = 0
        for (i in questions.indices) {
            if (userAnswers[i] == correctAnswers[i]) {
                correctCount++
            }
        }
        return (correctCount.toDouble() / questions.size.toDouble() * 100).toInt()
    }
}
