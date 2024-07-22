package com.shashank.quizapp.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.shashank.quizapp.R
import com.shashank.quizapp.adapters.OptionAdapter
import com.shashank.quizapp.adapters.QuizAdapters
import com.shashank.quizapp.databinding.ActivityQuestionBinding
import com.shashank.quizapp.models.Questions
import com.shashank.quizapp.models.Quiz

class QuestionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuestionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        bindViews()
        setUpFireStore()
    }

    private fun setUpFireStore() {
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("quizzes").whereEqualTo("id","11-09-2021")
            .get()
            .addOnSuccessListener {
                it.toObjects(Questions::class.java)
            }
    }

    private fun bindViews() {
        val question = Questions("Q1. What is the capital of India?","New Delhi","Mumbai","Kolkata","Delhi","Delhi","")

        binding.textDescription.text = question.questions
        val optionAdapter = OptionAdapter(question)
        binding.optionRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.optionRecyclerView.adapter = optionAdapter
        binding.optionRecyclerView.setHasFixedSize(true)

    }


}