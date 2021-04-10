package com.app_dev.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class QuizQuestions : AppCompatActivity() ,View.OnClickListener{

    private var mCurrentPosition: Int = 1
    private var btn_times: Int=0
    private var numOfCorrectAns=0
    private var mQuesList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mUserName:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName=intent.getStringExtra(Constants.USER_NAME)

        mQuesList = Constants.getQuestions()
        setQuestion()

        tv_optionOne.setOnClickListener(this)
        tv_optionTwo.setOnClickListener(this)
        tv_optionThree.setOnClickListener(this)
        tv_optionFour.setOnClickListener(this)
        submitQues.setOnClickListener(this)
    }

    private fun setQuestion() {
        btn_times=0
        val ques = mQuesList!![mCurrentPosition - 1]


        defaultOptionsView()
        if(mCurrentPosition==mQuesList!!.size)
            submitQues.text="FINISH"
        else if(mCurrentPosition<mQuesList!!.size || mSelectedOptionPosition==0)
            submitQues.text="SUBMIT"



        progressBar.progress = mCurrentPosition
        progressText.text = "$mCurrentPosition" + "/" + progressBar.max

        tv_ques.text = ques!!.question
        // ques is the current question and "question" is the string type variable in data class
        iv_image.setImageResource(ques.image)
        tv_optionOne.text = ques.optionOne
        tv_optionTwo.text = ques.optionTwo
        tv_optionThree.text = ques.optionThree
        tv_optionFour.text = ques.optionFour
    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        options.add(0,tv_optionOne)
        options.add(1,tv_optionTwo)
        options.add(2,tv_optionThree)
        options.add(3,tv_optionFour)

        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface= Typeface.DEFAULT
            option.background=ContextCompat.getDrawable(
                    this,
                    R.drawable.border_bg
            )
        }

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_optionOne ->{
                selectedOptionView(tv_optionOne,1)
            }
            R.id.tv_optionTwo ->{
                selectedOptionView(tv_optionTwo,2)
            }
            R.id.tv_optionThree ->{
                selectedOptionView(tv_optionThree,3)
            }
            R.id.tv_optionFour ->{
                selectedOptionView(tv_optionFour,4)
            }
            R.id.submitQues ->{

                if(mSelectedOptionPosition==0){
                    mCurrentPosition++

                 when{
                     mCurrentPosition<=mQuesList!!.size->{
                         setQuestion()
                     }else->{
                         val intent=Intent(this,Result::class.java)
                         intent.putExtra(Constants.USER_NAME,mUserName)
                     intent.putExtra(Constants.CORRECT_ANSWERS,numOfCorrectAns)
                     intent.putExtra(Constants.TOTAL_QUESTIONS,mQuesList!!.size)
                     startActivity(intent)
                     finish()
                     }
                 }
                }
                else{

                    val Ques = mQuesList!![mCurrentPosition - 1]
                    if(Ques!!.correctOption!=mSelectedOptionPosition){
                        Answer(mSelectedOptionPosition,R.drawable.wrong_ans)
                    }else {
                        numOfCorrectAns++
                    }
                    Answer(Ques.correctOption,R.drawable.correct_ans)
                    btn_times++
                }

                if(mCurrentPosition==mQuesList!!.size)
                    submitQues.text="FINISH"
                else if(mCurrentPosition<mQuesList!!.size && mSelectedOptionPosition!=0)
                    submitQues.text="GO TO NEXT QUESTION"
                mSelectedOptionPosition=0

            }
        }
    }

    private fun Answer(answer:Int,drawableView:Int) {
        if (btn_times == 0) {
            when (answer) {
                1 -> {
                    tv_optionOne.background = ContextCompat.getDrawable(
                        this,
                        drawableView
                    )
                }
                2 -> {
                    tv_optionTwo.background = ContextCompat.getDrawable(
                        this,
                        drawableView
                    )
                }
                3 -> {
                    tv_optionThree.background = ContextCompat.getDrawable(
                        this,
                        drawableView
                    )
                }
                4 -> {
                    tv_optionFour.background = ContextCompat.getDrawable(
                        this,
                        drawableView
                    )
                }

            }
        }
    }

    private fun selectedOptionView(tv:TextView,selectedOptionNum: Int) {
        if (btn_times == 0) {
            defaultOptionsView()

            mSelectedOptionPosition = selectedOptionNum
            tv.setTextColor(Color.parseColor("#363A43"))
            tv.setTypeface(tv.typeface, Typeface.BOLD)
            tv.background = ContextCompat.getDrawable(
                this,
                R.drawable.selected_borderbg
            )
        }
    }


}