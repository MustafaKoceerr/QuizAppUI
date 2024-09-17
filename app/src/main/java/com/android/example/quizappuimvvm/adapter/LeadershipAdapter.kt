package com.android.example.quizappuimvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.example.quizappuimvvm.databinding.RowLeardersBinding
import com.android.example.quizappuimvvm.domain.UserModel
import com.bumptech.glide.Glide

class LeadershipAdapter(private val userList: List<UserModel>) :
    RecyclerView.Adapter<LeadershipAdapter.LeadershipViewHolder>() {
    inner class LeadershipViewHolder(binding: RowLeardersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.rowTxtTitle
        val rank: TextView = binding.rowTxtRank
        val score: TextView = binding.rowTxtScore
        val imgView: ImageView = binding.rowImgCircle

        fun bindData(userModel: UserModel, position: Int) {
            name.text = userModel.name
            score.text = userModel.score.toString()
            val realRank = position + 3
            rank.text = realRank.toString()

            val resourceId = binding.root.context.resources.getIdentifier(
                userModel.pic,
                "drawable",
                binding.root.context.packageName
            )

            Glide.with(binding.root.context)
                .load(resourceId)
                .into(imgView)

        }
    }

    private lateinit var binding: RowLeardersBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeadershipViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = RowLeardersBinding.inflate(inflater, parent, false)
        return LeadershipViewHolder(binding)
    }

    override fun getItemCount() = userList.size

    override fun onBindViewHolder(holder: LeadershipViewHolder, position: Int) {
        with(holder) {
            bindData(userList[position], position)
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<UserModel>() {
        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)
}