package com.rs.studentapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


class StudentDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        val studentName = intent.getStringExtra("name")
        val studentId = intent.getStringExtra("id")
        val studentPhoneNumber = intent.getStringExtra("phoneNumber")
        val studentAddress = intent.getStringExtra("address")
        val studentAvatarUrl = intent.getStringExtra("avatarUrl")

        val nameTextView: TextView = findViewById(R.id.textViewStudentName)
        val idTextView: TextView = findViewById(R.id.textViewStudentId)
        val phoneNumberTextView: TextView = findViewById(R.id.phoneNumberTextView)
        val addressTextView: TextView = findViewById(R.id.addressTextView)
        val avatarImageView: ImageView = findViewById(R.id.studentAvatarUrl)

        nameTextView.text = "Name: $studentName"
        idTextView.text = "ID: $studentId"
        phoneNumberTextView.text = "Phone Number: $studentPhoneNumber"
        addressTextView.text = "Address: $studentAddress"

        val backButton: Button = findViewById(R.id.buttonBack)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
