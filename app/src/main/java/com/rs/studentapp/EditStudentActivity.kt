package com.rs.studentapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class EditStudentActivity : AppCompatActivity() {

    private var studentId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            finish()
        }


        studentId = intent.getStringExtra("id")
        val student = StudentRepository.getAllStudents().find { it.id == studentId }

        val nameEditText: EditText = findViewById(R.id.editTextStudentName)
        val phoneEditText: EditText = findViewById(R.id.editTextPhoneNumber)
        val addressEditText: EditText = findViewById(R.id.editTextAddress)

        student?.let {
            nameEditText.setText(it.name)
            phoneEditText.setText(it.phoneNumber)
            addressEditText.setText(it.address)
        }

        val saveButton: Button = findViewById(R.id.buttonSaveStudent)
        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val address = addressEditText.text.toString()

            if (student != null && name.isNotBlank() && phone.isNotBlank() && address.isNotBlank()) {
                student.name = name
                student.phoneNumber = phone
                student.address = address
                finish()
            }
        }
    }
}
