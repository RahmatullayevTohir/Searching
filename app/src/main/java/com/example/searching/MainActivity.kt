package com.example.searching

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.searching.adapter.ToDoAdapter
import com.example.searching.model.ToDo

class MainActivity : AppCompatActivity() {

    private val adapter by lazy {ToDoAdapter()}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

        setData()

    }

    private fun initViews() {
        var searchET = findViewById<EditText>(R.id.et_search)
        var todoRV = findViewById<RecyclerView>(R.id.rv_todo)
        todoRV.adapter = adapter

        searchET.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                adapter.filter.filter(p0)
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    private fun setData() {
        var todoList: ArrayList<ToDo> = ArrayList()
        todoList.add(ToDo("samo"))
        todoList.add(ToDo("ra"))
        todoList.add(ToDo("title"))
        todoList.add(ToDo("lazy"))
        todoList.add(ToDo("kom"))
        todoList.add(ToDo("last"))
        todoList.add(ToDo("Sarvar"))
        todoList.add(ToDo("Harry"))
        todoList.add(ToDo("Madina"))
        todoList.add(ToDo("Jack"))
        todoList.add(ToDo("low"))
        todoList.add(ToDo("go"))
        todoList.add(ToDo("Hrry"))
        todoList.add(ToDo("Hay"))
        todoList.add(ToDo("Ha"))
        todoList.add(ToDo("add"))
        todoList.add(ToDo("Hello"))
        adapter.setData(todoList)
    }
}