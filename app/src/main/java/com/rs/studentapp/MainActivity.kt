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

        // הגדרת ה-Toolbar
        val toolbar: Toolbar = findViewById(R.id.main_toolbar)
        toolbar.setBackgroundColor(Color.parseColor("#333333"))
        setSupportActionBar(toolbar)

        // הגדרת RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewStudents)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // יצירת Adapter עם נתוני ה-Repository
        adapter = StudentAdapter(StudentRepository.getAllStudents()) { student ->
            // לחיצה על סטודנט
        }
        recyclerView.adapter = adapter

        // כפתור הוספת סטודנט חדש
        val button: Button = findViewById(R.id.buttonNavigate)
        button.setOnClickListener {
            val intent = Intent(this, NewStudent::class.java)
            startActivityForResult(intent, REQUEST_ADD_STUDENT)
        }

        // הוספת נתונים ראשוניים אם הרשימה ריקה
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

    // עדכון הרשימה בעת חזרה ממסך הוספת סטודנט
    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    companion object {
        private const val REQUEST_ADD_STUDENT = 1
    }
}
