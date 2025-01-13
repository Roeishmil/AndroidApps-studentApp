package com.rs.studentapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rs.studentapp.StudentModel.StudentModel

class StudentAdapter(
    private val students: List<StudentModel>,
    private val onStudentClicked: (StudentModel) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val studentImage: ImageView = view.findViewById(R.id.studentImage)
        val studentName: TextView = view.findViewById(R.id.studentName)
        val studentId: TextView = view.findViewById(R.id.studentId)
        val checkBox: CheckBox = view.findViewById(R.id.checkBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.studentName.text = student.name
        holder.studentId.text = "ID: ${student.id}"
        holder.checkBox.isChecked = student.isChecked

        // Set static image (update with real image logic if needed)
        holder.studentImage.setImageResource(R.drawable.student_avatar)

        // Handle clicks
        holder.itemView.setOnClickListener {
            onStudentClicked(student)
        }

        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            student.isChecked = isChecked
        }

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, StudentDetailsActivity::class.java)

            intent.putExtra("name", student.name)
            intent.putExtra("id", student.id)
            intent.putExtra("phoneNumber", student.phoneNumber)
            intent.putExtra("address", student.address)
            intent.putExtra("avatarUrl", student.avatarUrl)

            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = students.size
}
