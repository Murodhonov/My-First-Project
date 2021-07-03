package com.example.codial

import Adapter.MyAdapter
import Cache.MySharedPreference
import Models.Mylist
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        btn_add_class.setOnClickListener {
            startActivity(Intent(this,Add_course::class.java))
        }


    }

    override fun onStart() {
        super.onStart()

        MySharedPreference.init(this)
        val list = MySharedPreference.obektString
        val mySortList = ArrayList<Mylist>()

        for (i in list.indices) {
            if (list[i].className == "null") {
                mySortList.add(list[i])
            }
        }

        myAdapter = MyAdapter(this,mySortList)

        listView.adapter = myAdapter

        listView.setOnItemClickListener{ parent, view, position,id ->
            val intent = Intent(this,Course_list::class.java)
            intent.putExtra("position",position)
            intent.putExtra("courseName",mySortList[position].courseName)
            startActivity(intent)
        }

    }

/*    private fun popupMenu(){
        val popupMenu  = PopupMenu(this,more_first)
        popupMenu.inflate(R.menu.popup_menu)
        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.editor ->{
                    Toast.makeText(this, "Editor", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.delete ->{
                    Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.send_sms ->{
                    Toast.makeText(this, "send sms", Toast.LENGTH_SHORT).show()
                    true
                }
                else-> true
            }
        }

        more_first.setOnClickListener {
            try {
                val popup = PopupMenu::class.java.getDeclaredField("mPopup")
                popup.isAccessible = true
                val menu = popup.get(popup)
                menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                    .invoke(menu, true)
            }catch (e:Exception){
                e.printStackTrace()
            }
            finally {
                popupMenu.show()
            }
        }
    }*/

}
