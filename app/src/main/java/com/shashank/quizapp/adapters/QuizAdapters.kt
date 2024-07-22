package com.shashank.quizapp.adapters

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.shashank.quizapp.activities.QuestionActivity
import com.shashank.quizapp.databinding.QuizItemBinding
import com.shashank.quizapp.models.Quiz
import com.shashank.quizapp.utils.ColorPicker
import com.shashank.quizapp.utils.IconPicker

class QuizAdapters(private val quizList : List<Quiz>) :RecyclerView.Adapter<QuizAdapters.QuizViewHolder>(){

     class QuizViewHolder(val binding : QuizItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view = QuizItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return QuizViewHolder(view)
    }

    override fun getItemCount(): Int {
        return quizList.size
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
       val currentQuiz = quizList[position]
        holder.binding.textQuizTitle.text = currentQuiz.title
        holder.binding.textQuizDate.text = currentQuiz.id
        holder.binding.cardQuiz.setCardBackgroundColor(Color.parseColor(ColorPicker.nextColor()))
        holder.binding.iconQuiz.setImageResource(IconPicker.getIcon())
        holder.binding.root.setOnClickListener{
            Toast.makeText(holder.binding.root.context,"You clicked on ${currentQuiz.title}",Toast.LENGTH_SHORT).show()
            val intent = Intent(holder.binding.root.context, QuestionActivity::class.java)
            holder.binding.root.context.startActivity(intent)
        }
    }


}