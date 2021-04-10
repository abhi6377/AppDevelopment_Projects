package com.app_dev.quizapp

object Constants{

    const val USER_NAME :String="user_name"
    const val TOTAL_QUESTIONS:String="total_questions"
    const val CORRECT_ANSWERS:String="correct_answers"

    fun getQuestions() : ArrayList<Question>{
        val questionList= ArrayList<Question>()
        val ques1=Question(
            1,
            "What country does this flag belong to ?",
            R.drawable.australia,
            "Australia",
            "Belize",
            "Armenia",
            "Denmark",
            1
        )

        val ques2=Question(
            2,
            "What country does this flag belong to ?",
            R.drawable.denmark,
            "Barbados",
            "Fiji",
            "Denmark",
            "Gabon",
            3
        )

        val ques3=Question(
            3,
            "What country does this flag belong to ?",
            R.drawable.colombia,
            "Finland",
            "Colombia",
            "Poland",
            "France",
            2
        )

        val ques4=Question(
            4,
            "What country does this flag belong to ?",
            R.drawable.lebanon,
            "Lebanon",
            "Greece",
            "Bahamas",
            "Tuvalu",
            1)

        val ques5=Question(
            5,
            "What country does this flag belong to ?",
            R.drawable.india,
            "Russia",
            "India",
            "Jordan",
            "Denmark",
            2)

        val ques6=Question(
            6,
            "What country does this flag belong to ?",
            R.drawable.brazil,
            "Kuwait",
            "Armenia",
            "New Zealand",
            "Brazil",
            4)


        questionList.add(ques1)
        questionList.add(ques2)
        questionList.add(ques3)
        questionList.add(ques4)
        questionList.add(ques5)
        questionList.add(ques6)
        return questionList
    }
}