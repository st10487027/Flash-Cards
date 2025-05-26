package vcmsa.ci.flashcards

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    val questions = arrayOf(
        "South Africa hosted the soccer world cup in the year 2010",
        "Mark Zuckerbeg is not the owner of social media app,Facebook",
        "The first black president in South Africa was Nelson Mandela",
        "Cyril Ramaphosa is the second black president of South Africa",
        "The Major event that took place around the world in 2020 is Covid-19 pandemic"
    )

    val answers = booleanArrayOf(true, false, true, false, true)

    var index = 0
    var score = 0

    lateinit var questionText: TextView
    lateinit var trueBtn: Button
    lateinit var falseBtn: Button
    lateinit var nextBtn: Button
    lateinit var feedbackText: TextView
    lateinit var scoreText: TextView
    lateinit var reviewText: TextView
    lateinit var reviewBtn: Button
    lateinit var exitBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // connect to UI
        questionText = findViewById(R.id.questionText)
        trueBtn = findViewById(R.id.trueBtn)
        falseBtn = findViewById(R.id.falseBtn)
        nextBtn = findViewById(R.id.nextBtn)
        feedbackText = findViewById(R.id.feedbackText)
        scoreText = findViewById(R.id.scoreText)
        reviewText = findViewById(R.id.reviewText)
        reviewBtn = findViewById(R.id.reviewBtn)
        exitBtn = findViewById(R.id.exitBtn)

        showQuestion()

        trueBtn.setOnClickListener {
            checkAnswer(true)
        }

        falseBtn.setOnClickListener {
            checkAnswer(false)
        }

        nextBtn.setOnClickListener {
            index++
            if (index < questions.size) {
                showQuestion()
                feedbackText.text = ""
            } else {
                showScore()
            }
        }

        reviewBtn.setOnClickListener {
            var review = ""
            for (i in questions.indices) {
                review += "${i + 1}. ${questions[i]} - Answer: ${answers[i]}\n"
            }
            reviewText.text = review
        }

        exitBtn.setOnClickListener {
            finish()
        }
    }

    fun showQuestion() {
        questionText.text = questions[index]
    }

    fun checkAnswer(userAnswer: Boolean) {
        if (userAnswer == answers[index]) {
            feedbackText.text = "Correct!"
            score++
        } else {
            feedbackText.text = "Incorrect!"
        }
    }

    fun showScore() {
        questionText.text = "Quiz Complete!"
        scoreText.text = "Your score: $score out of ${questions.size}"

        trueBtn.isEnabled = false
        falseBtn.isEnabled = false
        nextBtn.isEnabled = false
    }
}
