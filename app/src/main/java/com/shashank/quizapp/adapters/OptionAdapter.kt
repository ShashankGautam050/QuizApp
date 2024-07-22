package com.shashank.quizapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shashank.quizapp.R
import com.shashank.quizapp.databinding.OptionItemBinding
import com.shashank.quizapp.databinding.QuizItemBinding
import com.shashank.quizapp.models.Questions

class OptionAdapter(private val question : Questions) : RecyclerView.Adapter<OptionAdapter.OptionViewHolder>() {

    private var options : List<String> = listOf(question.options1,question.options2,question.options3,question.options4)

    inner class OptionViewHolder(val binding : OptionItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val view = OptionItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return OptionViewHolder(view)
    }

    override fun getItemCount(): Int {
       return options.size
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        holder.binding.quizOption.text = options[position]
        holder.binding.root.setOnClickListener{
            question.userAnswer = options[position]
            notifyDataSetChanged()
        }
        if(question.userAnswer != options[position]){
            holder.binding.root.setBackgroundResource(R.drawable.option_bg)
        }
        else{
            holder.binding.root.setBackgroundResource(R.drawable.option_selected_bg)
        }
    }
}