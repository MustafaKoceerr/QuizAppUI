package com.android.example.quizappuimvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.example.quizappuimvvm.databinding.RowQuestionsBinding

class QuestionAdapter(
    val correctAnswer:String,
    val users:MutableList<String> = mutableListOf(),
    val returnScore: Score
): RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    interface Score{
        fun amount(number:Int, clickedAnswer:String)
    }
    inner class QuestionViewHolder(binding: RowQuestionsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(){

        }

    }

    private lateinit var binding: RowQuestionsBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = RowQuestionsBinding.inflate(inflater, parent, false)
        return QuestionViewHolder(binding)
    }

    override fun getItemCount(): Int {
        TODO()
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
    }

}
