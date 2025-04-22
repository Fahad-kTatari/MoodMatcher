package com.fahad.moodmatcher

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    private lateinit var moodInput: EditText
    private lateinit var calculateButton: Button
    private lateinit var moodResult: TextView
    private lateinit var modalButton: Button

    private lateinit var symptomTired: CheckBox
    private lateinit var symptomRestless: CheckBox
    private lateinit var symptomStressed: CheckBox
    private lateinit var symptomSad: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI elements
        moodInput = findViewById(R.id.moodInput)
        calculateButton = findViewById(R.id.calculateButton)
        moodResult = findViewById(R.id.moodResult)
        modalButton = findViewById(R.id.modalButton)

        // Initialize symptoms CheckBoxes
        symptomTired = findViewById(R.id.symptomTired)
        symptomRestless = findViewById(R.id.symptomRestless)
        symptomStressed = findViewById(R.id.symptomStressed)
        symptomSad = findViewById(R.id.symptomSad)

        // Set click listener for the calculate button
        calculateButton.setOnClickListener {
            val moodText = moodInput.text.toString()

            if (moodText.isEmpty() && !symptomTired.isChecked && !symptomRestless.isChecked && !symptomStressed.isChecked && !symptomSad.isChecked) {
                Toast.makeText(this, "Please provide mood or select symptoms", Toast.LENGTH_SHORT).show()
            } else {
                calculateMood(moodText)
            }
        }

        // Set click listener for the modal button
        modalButton.setOnClickListener {
            showModal()
        }
    }

    private fun calculateMood(inputText: String) {
        var mood = "Neutral"
        var emoji = "🤔"
        var quote = "Let's figure this out together."

        // Mood based on input text
        if (inputText.contains("happy", true)) {
            mood = "Happy"
            emoji = "😄"
            quote = "Keep shining like the sun!"
        } else if (inputText.contains("sad", true)) {
            mood = "Sad"
            emoji = "😢"
            quote = "It’s okay to feel down. Tomorrow is a new day."
        } else if (inputText.contains("angry", true)) {
            mood = "Angry"
            emoji = "😠"
            quote = "Take a deep breath. You've got this."
        }

        // Add symptoms effect
        if (symptomTired.isChecked) mood = "Tired"
        if (symptomRestless.isChecked) mood = "Restless"
        if (symptomStressed.isChecked) mood = "Stressed"
        if (symptomSad.isChecked) mood = "Sad"

        // Set the results based on calculated mood
        moodResult.text = "$emoji $mood\n$quote"
    }

    private fun showModal() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("About Mood Matcher")
            .setMessage("This app calculates your emotional state based on your typed input and symptoms you select.")
            .setPositiveButton("Got it") { dialog, _ ->
                dialog.dismiss()
            }
        builder.create().show()
    }
}
