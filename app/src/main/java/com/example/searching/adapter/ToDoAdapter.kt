package com.example.searching.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.searching.R
import com.example.searching.model.ToDo

class ToDoAdapter: ListAdapter<ToDo, ToDoAdapter.ViewHolder>(ITEM_DIF),Filterable {

    companion object{
        private val ITEM_DIF = object :DiffUtil.ItemCallback<ToDo>(){

            override fun areItemsTheSame(oldItem: ToDo, newItem: ToDo): Boolean =
                oldItem.title == newItem.title
            override fun areContentsTheSame(oldItem: ToDo, newItem: ToDo): Boolean =
                oldItem.title == newItem.title
        }
    }

    var todolist :List<ToDo> = ArrayList()

    inner class ViewHolder(var view:View):RecyclerView.ViewHolder(view){
        fun bind(item: ToDo) {
            var title = view.findViewById<TextView>(R.id.title)
            title.text = item.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)= holder.bind(getItem(position))

    fun setData (list:List<ToDo>){
        this.todolist = list
        submitList(list)
    }

    override fun getFilter(): Filter {
        return customFilter
    }

    private val customFilter = object :Filter(){
        override fun performFiltering(p0: CharSequence?): FilterResults {
            val filterableList = mutableListOf<ToDo>()
            if (p0 == null || p0.isEmpty()){
                filterableList.addAll(todolist)
            }else{
                for (item in todolist){
                    if (item.title.lowercase().contains(p0.toString().lowercase())){
                        filterableList.add(item)
                    }
                }
            }
            val result = FilterResults()
            result.values = filterableList
            return result
        }


        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
            submitList(p1?.values as MutableList<ToDo>)
        }

    }
}