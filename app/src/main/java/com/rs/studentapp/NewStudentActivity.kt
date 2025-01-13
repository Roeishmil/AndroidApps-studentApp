package com.rs.studentapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.rs.studentapp.StudentModel.StudentModel

class NewStudent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)

        val nameEditText: EditText = findViewById(R.id.editTextStudentName)
        val idEditText: EditText = findViewById(R.id.editTextStudentId)
        val phoneEditText: EditText = findViewById(R.id.editTextPhoneNumber)
        val addressEditText: EditText = findViewById(R.id.editTextAddress)

        val saveButton: Button = findViewById(R.id.buttonSaveStudent)
        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val id = idEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val address = addressEditText.text.toString()

            if (name.isNotBlank() && id.isNotBlank() && phone.isNotBlank() && address.isNotBlank()) {
                val newStudent = StudentModel(
                    id = id,
                    name = name,
                    phoneNumber = phone,
                    address = address,
                    avatarUrl = "https://example.com/default_avatar.jpg",
                    isChecked = false
                )
                StudentRepository.addStudent(newStudent) // הוספת הסטודנט ל-Repository
                finish()
            }
        }
    }
}
