package com.rs.studentapp

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.content.Intent
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rs.studentapp.R
import com.rs.studentapp.StudentAdapter
import com.rs.studentapp.StudentModel.StudentModel



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set up the toolbar.
        val toolbar: Toolbar = findViewById(R.id.main_toolbar)
        toolbar.setBackgroundColor(Color.parseColor("#333333"))
        setSupportActionBar(toolbar)

        val button: Button = findViewById(R.id.buttonNavigate)
        button.setOnClickListener {
            val intent = Intent(this, NewStudent::class.java)
            startActivity(intent)
        }



        val students = mutableListOf(
            StudentModel(
                "1",
                "John Doe",
                "050505050",
                "Address 1",
                "https://example.com/avatar1.jpg",
                false
            ),
            StudentModel(
                "2",
                "Jane Smith",
                "050505050",
                "Address 2",
                "https://example.com/avatar2.jpg",
                false
            ),
            StudentModel(
                "3",
                "Alice Brown",
                "050505050",
                "Address 3",
                "https://example.com/avatar3.jpg",
                false
            )
        )

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewStudents)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = StudentAdapter(students) { StudentModel ->
            // Handle click on student
            // For example, navigate to student details


        }
    }
}
