package Adapter

import Cache.MySharedPreference
import Models.Mylist
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import com.example.codial.R
import kotlinx.android.synthetic.main.delete_msg.view.*
import kotlinx.android.synthetic.main.edit_msg.view.*
import kotlinx.android.synthetic.main.item.view.*
import java.lang.reflect.Field

class MyAdapter(context: Context, private var Mylist: ArrayList<Mylist>) :
    ArrayAdapter<Mylist>(context, R.layout.item, Mylist) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val itemView: View =
            convertView ?: LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)

        itemView.txt_name.text = Mylist[position].courseName

        itemView.more_first.setOnClickListener {
            val popupMenu = PopupMenu(context, itemView.more_first)
            popupMenu.inflate(R.menu.popup_menu)
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.editor -> {
                        val view = View.inflate(context, R.layout.edit_msg, null)
                        val builder = AlertDialog.Builder(context)
                        builder.setView(view)
                        val dialog = builder.create()
                        dialog.show()
                        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                        view.btn_save_edit.setOnClickListener {

                            var oldName = view.editoriz1.text.toString().trim()
                            var oldDescription = view.editoriz2.text.toString().trim()

                            if (view.editoriz1.text.toString().trim() != ""){
                                if (view.editoriz2.text.toString().trim() != ""){
                                    if (view.editoriz1.text.toString().trim() != Mylist[position].courseName){
                                        if (view.editoriz2.text.toString().trim() != Mylist[position].description){
                                            val newName = view.editoriz1.text.toString().trim()
                                            val newDescription = view.editoriz2.text.toString().trim()

                                            val list = MySharedPreference.obektString

                                            for (i in list.indices){
                                                if(list[i].courseName == oldName && list[i].description == oldDescription){
                                                    list[i].courseName = newName
                                                    list[i].description = newDescription
                                                    if (Mylist[position].className != "null"){
                                                        list[i].className = Mylist[position].className
                                                    }
                                                }
                                            }

                                            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                                            dialog.hide()
                                        }
                                    }
                                }else{
                                    Toast.makeText(context, "Empty", Toast.LENGTH_SHORT).show()
                                }
                            }else{
                                Toast.makeText(context, "Empty", Toast.LENGTH_SHORT).show()
                            }
                        }

                        true
                    }
                    R.id.delete -> {
                        val view = View.inflate(context, R.layout.delete_msg, null)
                        val builder = AlertDialog.Builder(context)
                        builder.setView(view)
                        val dialog = builder.create()
                        dialog.show()
                        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                        view.btn_yes.setOnClickListener {
                            Mylist.removeAt(position)
                            var list = MySharedPreference.obektString
                            list = Mylist
                            MySharedPreference.obektString = list
                        }
                        true
                    }
                    R.id.send_sms -> {
                        Toast.makeText(context, "coming soon", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> true
                }
            }

            itemView.more_first.setOnClickListener {
                try {
                    val popup: Field = PopupMenu::class.java.getDeclaredField("mPopup")
                    popup.isAccessible = true
                    val menu = popup.get(popupMenu)
                    menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                        .invoke(menu, true)
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    popupMenu.show()
                }
            }
        }

        return itemView
    }


}