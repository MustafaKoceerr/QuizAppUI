package com.android.example.quizappuimvvm.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.example.quizappuimvvm.R
import com.android.example.quizappuimvvm.adapter.LeadershipAdapter
import com.android.example.quizappuimvvm.databinding.ActivityLeaderBinding
import com.android.example.quizappuimvvm.domain.UserModel
import com.bumptech.glide.Glide

class LeaderActivity : AppCompatActivity() {
    lateinit var binding: ActivityLeaderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLeaderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        window.statusBarColor = ContextCompat.getColor(this@LeaderActivity, R.color.grey)

        val data = loadData()
        with(binding){
            txtScore1.text = data[0].score.toString()
            txtScore2.text = data[1].score.toString()
            txtScore3.text = data[2].score.toString()

            txtTitleTop1.text = data[0].name
            txtTitleTop2.text = data[1].name
            txtTitleTop3.text = data[2].name

            val resourceId1 = R.drawable.person1
            val resourceId2 = R.drawable.person2
            val resourceId3 = R.drawable.person3

            Glide.with(root.context)
                .load(resourceId1)
                .into(imgCircleFirst)

            Glide.with(root.context)
                .load(resourceId2)
                .into(imgCircleSecond)

            Glide.with(root.context)
                .load(resourceId3)
                .into(imgCircleThird)

            bottomMenu.setItemSelected(R.id.board)
            bottomMenu.setOnItemSelectedListener {
                if (it == R.id.home){
                    startActivity(Intent(this@LeaderActivity, MainActivity::class.java))
                }
            }

            data.removeAt(0)
            data.removeAt(1)
            data.removeAt(2)
            val leadershipAdapter = LeadershipAdapter(data)

            recyclerLeaderView.apply {
                layoutManager = LinearLayoutManager(this@LeaderActivity)
                adapter = leadershipAdapter
            }

        }


    }

    private fun loadData():MutableList<UserModel>{
        val users: MutableList<UserModel> = mutableListOf(
            UserModel(1,"Sophia","person1",4850),
            UserModel(2,"Daniel","person2",4560),
            UserModel(3,"James","person3",3873),
            UserModel(4,"John Smith","person4",3250),
            UserModel(5,"Emily Johnson","person5",3015),
            UserModel(6,"David Brown","person6",2970),
            UserModel(7,"Sarah Wilson","person7",2870),
            UserModel(8,"Micheal Davis","person8",2670),
            UserModel(9,"Annie Smith","person9",2380),
            UserModel(10,"Laura Crop","person9",2380)
        )

        return users
    }
}