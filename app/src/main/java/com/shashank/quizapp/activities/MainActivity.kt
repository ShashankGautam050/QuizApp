package com.shashank.quizapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.firestore.FirebaseFirestore
import com.shashank.quizapp.R
import com.shashank.quizapp.adapters.QuizAdapters
import com.shashank.quizapp.databinding.ActivityMainBinding
import com.shashank.quizapp.models.Quiz

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var quizAdapter: QuizAdapters
    private var quizList = mutableListOf<Quiz>()
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        // Handle window insets
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }
        setUpViews()
    }

//    private fun populateQuiz() {
//        quizList.add(Quiz(1.toString(),"Basics of Kotlin"))
//        quizList.add(Quiz(2.toString(),"Basics of Java"))
//        quizList.add(Quiz(3.toString(),"Basics of C++"))
//        quizList.add(Quiz(4.toString(),"Basics of Python"))
//        quizList.add(Quiz(5.toString(),"Basics of C"))
//        quizList.add(Quiz(6.toString(),"Basics of C#"))
//        quizList.add(Quiz(7.toString(),"Basics of JavaScript"))
//        quizList.add(Quiz(8.toString(),"Basics of PHP"))
//        quizList.add(Quiz(9.toString(),"Basics of Ruby"))
//        quizList.add(Quiz(10.toString(),"Basics of Swift"))
//    }

    private fun setUpViews() {
        setUpDrawerLayout()
        setUpRecyclerView()
        setUpFirestone()
        setUpDatePicker()
    }

    private fun setUpDatePicker() {
        binding.dateBtn.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager, "DatePicker")
            datePicker.addOnPositiveButtonClickListener {
                val date = datePicker.headerText
                Toast.makeText(this, date, Toast.LENGTH_SHORT).show()
                val intent = Intent(this, QuestionActivity::class.java)
                intent.putExtra("DATE", date)
                startActivity(intent)
            }
            datePicker.addOnNegativeButtonClickListener {
                Toast.makeText(this, "Date is not selected", Toast.LENGTH_SHORT).show()
            }
            datePicker.addOnCancelListener {
                Toast.makeText(this, "Date Picker Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setUpFirestone(){
        firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("quizzes")
        collectionReference.addSnapshotListener{
            value, error ->
            if (value == null || error != null){
                Toast.makeText(this,"Error fetching data", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            quizList.clear()
            val data = value.toObjects(Quiz::class.java)
            quizList.addAll(data)
            quizAdapter.notifyDataSetChanged()
        }
    }

    private fun setUpRecyclerView() {
       quizAdapter = QuizAdapters(quizList)
        binding.quizRecyclerView.adapter = quizAdapter
        binding.quizRecyclerView.layoutManager = GridLayoutManager(this,2)
    }

    private fun setUpDrawerLayout() {
        setSupportActionBar(binding.appbarLayout)
        actionBarDrawerToggle = ActionBarDrawerToggle(this,binding.drawerLayout,binding.appbarLayout,
            R.string.app_name,
            R.string.app_name
        )
        actionBarDrawerToggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}