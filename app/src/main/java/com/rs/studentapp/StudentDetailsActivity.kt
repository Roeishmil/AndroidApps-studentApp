package com.rs.studentapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class StudentDetailsActivity : AppCompatActivity() {

    private lateinit var studentId: String
    private lateinit var nameTextView: TextView
    private lateinit var idTextView: TextView
    private lateinit var phoneNumberTextView: TextView
    private lateinit var addressTextView: TextView


    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        studentId = intent.getStringExtra("id") ?: return

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        nameTextView = findViewById(R.id.textViewStudentName)
        idTextView = findViewById(R.id.textViewStudentId)
        phoneNumberTextView = findViewById(R.id.phoneNumberTextView)
        addressTextView = findViewById(R.id.addressTextView)

        val editButton: Button = findViewById(R.id.buttonEdit)
        editButton.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("id", studentId)
            startActivity(intent)
        }

        refreshStudentDetails()
    }

    override fun onResume() {
        super.onResume()
        refreshStudentDetails()
    }

    private fun refreshStudentDetails() {
        val student = StudentRepository.getAllStudents().find { it.id == studentId }
        student?.let {
            nameTextView.text = "Name: ${it.name}"
            idTextView.text = "ID: ${it.id}"
            phoneNumberTextView.text = "Phone Number: ${it.phoneNumber}"
            addressTextView.text = "Address: ${it.address}"
        }
    }
}
