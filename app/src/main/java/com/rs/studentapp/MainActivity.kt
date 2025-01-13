package com.rs.studentapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rs.studentapp.StudentModel.StudentModel

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.main_toolbar)
        toolbar.setBackgroundColor(Color.parseColor("#333333"))
        setSupportActionBar(toolbar)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewStudents)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = StudentAdapter(StudentRepository.getAllStudents()) { student ->
            val intent = Intent(this, StudentDetailsActivity::class.java)
            intent.putExtra("id", student.id)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        val button: Button = findViewById(R.id.buttonNavigate)
        button.setOnClickListener {
            val intent = Intent(this, NewStudent::class.java)
            startActivityForResult(intent, REQUEST_ADD_STUDENT)
        }

        if (StudentRepository.getAllStudents().isEmpty()) {
            StudentRepository.addStudent(
                StudentModel("1", "John Doe", "050505050", "Address 1", "https://example.com/avatar1.jpg", false)
            )
            StudentRepository.addStudent(
                StudentModel("2", "Jane Smith", "050505051", "Address 2", "https://example.com/avatar2.jpg", false)
            )
        }
        adapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    companion object {
        private const val REQUEST_ADD_STUDENT = 1
    }
}
