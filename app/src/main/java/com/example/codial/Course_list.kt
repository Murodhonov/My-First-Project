package com.example.codial

import Adapter.MyAdapter
import Cache.MySharedPreference
import Models.Mylist
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_course_list.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.listView

class Course_list : AppCompatActivity() {


    lateinit var myAdapter: MyAdapter
    var index = 0
    var className = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_list)
        supportActionBar?.hide()

        index = intent.getIntExtra("position",-1)
        className = intent.getStringExtra("courseName").toString()
        course_title.text = className

        add_new_classroom.setOnClickListener {
            val intent = Intent(this,Add_class::class.java)
            intent.putExtra("name",className)
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()

        MySharedPreference.init(this)
        val list = MySharedPreference.obektString
        val mySortList = ArrayList<Mylist>()

        for (i in list.indices) {
            if (list[i].className == className) {
                mySortList.add(list[i])
            }
        }

        myAdapter = MyAdapter(this,mySortList)

        listView2.adapter = myAdapter

        listView2.setOnItemClickListener{ parent, view, position,id ->
            Toast.makeText(this, "$position - position", Toast.LENGTH_SHORT).show()
        }

    }
}