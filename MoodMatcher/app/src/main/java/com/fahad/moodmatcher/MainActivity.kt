package com.fahad.moodmatcher

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var moodInput: EditText
    private lateinit var calculateButton: Button
    private lateinit var moodResult: TextView
    private lateinit var modalButton: Button

    private lateinit var symptomTired: CheckBox
    private lateinit var symptomRestless: CheckBox
    private lateinit var symptomStressed: CheckBox
    private lateinit var symptomSad: CheckBox

    private val moodScores = mutableListOf<Int>() // List to store all mood scores

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        moodInput = findViewById(R.id.moodInput)
        calculateButton = findViewById(R.id.calculateButton)
        moodResult = findViewById(R.id.moodResult)
        modalButton = findViewById(R.id.modalButton)

        symptomTired = findViewById(R.id.symptomTired)
        symptomRestless = findViewById(R.id.symptomRestless)
        symptomStressed = findViewById(R.id.symptomStressed)
        symptomSad = findViewById(R.id.symptomSad)

        calculateButton.setOnClickListener {
            val moodText = moodInput.text.toString()

            if (moodText.isEmpty() && !symptomTired.isChecked && !symptomRestless.isChecked && !symptomStressed.isChecked && !symptomSad.isChecked) {
                Toast.makeText(this, "Please provide mood or select symptoms", Toast.LENGTH_SHORT).show()
            } else {
                calculateMood(moodText)
            }
        }

        modalButton.setOnClickListener {
            showModal()
        }
    }

    private fun calculateMood(inputText: String) {
        var mood = "Neutral"
        var emoji = "🤔"
        var quote = "Let's figure this out together."
        var moodScore = 3 // Default score for Neutral mood

        // Mood detection based on input text
        if (inputText.contains("happy", true)) {
            mood = "Happy"
            emoji = "😄"
            quote = "Keep shining like the sun!"
            moodScore = 5
        } else if (inputText.contains("sad", true)) {
            mood = "Sad"
            emoji = "😢"
            quote = "It’s okay to feel down. Tomorrow is a new day."
            moodScore = 1
        } else if (inputText.contains("angry", true)) {
            mood = "Angry"
            emoji = "😠"
            quote = "Take a deep breath. You've got this."
            moodScore = 1
        }

        // Mood override if symptoms are selected
        if (symptomTired.isChecked) {
            mood = "Tired"
            emoji = "😴"
            quote = "Rest is important. Take it easy."
            moodScore = 2
        }
        if (symptomRestless.isChecked) {
            mood = "Restless"
            emoji = "😵‍💫"
            quote = "Try some calming activities today."
            moodScore = 2
        }
        if (symptomStressed.isChecked) {
            mood = "Stressed"
            emoji = "😖"
            quote = "Deep breaths. One step at a time."
            moodScore = 1
        }
        if (symptomSad.isChecked) {
            mood = "Sad"
            emoji = "😢"
            quote = "It's okay to not be okay."
            moodScore = 1
        }

        // Add the current mood's score to the list
        moodScores.add(moodScore)

        // Calculate the average mood
        val averageMood = moodScores.average()

        // Display result with mood and mood average
        moodResult.text = "$emoji $mood\n$quote\n\nMood Average: %.2f".format(averageMood)
    }

    private fun showModal() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("About Mood Matcher")
            .setMessage("This app calculates your emotional state based on your typed input and symptoms you select.\n\nIt now also shows your average mood score over time!")
            .setPositiveButton("Got it") { dialog, _ ->
                dialog.dismiss()
            }
        builder.create().show()
    }
}
