package com.example.codial

import Cache.MySharedPreference
import Models.Mylist
import Utils.MyData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_course.*

class Add_course : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_course)
        supportActionBar?.hide()

        btn_save.setOnClickListener {

            val courseName = edt_1.text.toString().trim()
            val description = edt_2.text.toString().trim()

            if(courseName != "" && description != ""){
                MyData.userList.add(Mylist(courseName,description))
                val list = MySharedPreference.obektString
                list.add(Mylist(courseName,description))
                MySharedPreference.obektString = list
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this, "Fields empty", Toast.LENGTH_SHORT).show()
            }


        }

    }
}