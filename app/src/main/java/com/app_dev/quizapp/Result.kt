package com.app_dev.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class Result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val username=intent.getStringExtra(Constants.USER_NAME)
        tv_name.text=username
        val correctAns=intent.getIntExtra(Constants.CORRECT_ANSWERS,0)
        val totalQues=intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        tv_score.text="Your score is ${correctAns.toString()} out of ${totalQues.toString()}"

        btnFinish.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}