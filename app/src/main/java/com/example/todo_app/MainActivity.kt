package com.example.todo_app

import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editButton = findViewById<Button>(R.id.edit)
        val addButton = findViewById<Button>(R.id.add)
        val editText = findViewById<EditText>(R.id.editText)
        val listView = findViewById<ListView>(R.id.listView)
        val deleteButton = findViewById<Button>(R.id.delete)



        val itemList = arrayListOf<String>()
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_multiple_choice,
            itemList
        )



        addButton.setOnClickListener {
            itemList.add(editText.text.toString())
            listView.adapter = adapter
            adapter.notifyDataSetChanged()
            editText.text.clear()
        }

        
        editButton.setOnClickListener {
            val selectedItemPositions = listView.checkedItemPositions
            val itemCount = listView.count
            for (i in 0 until itemCount) {
                if (selectedItemPositions.get(i)) {
                    val editedTodo = editText.text.toString()
                    if (editedTodo.isNotEmpty()) {
                        itemList[i] = editedTodo
                    }
                }
            }
            adapter.notifyDataSetChanged()
            editText.text.clear()
            listView.clearChoices()
        }


        deleteButton.setOnClickListener {
            val position: SparseBooleanArray = listView.checkedItemPositions
            val count = listView.count
            var item = count - 1
            while (item >= 0) {
                if (position.get(item)) {
                    adapter.remove(itemList[item])
                }
                item--
            }
            position.clear()
            adapter.notifyDataSetChanged()
        }
    }
}
