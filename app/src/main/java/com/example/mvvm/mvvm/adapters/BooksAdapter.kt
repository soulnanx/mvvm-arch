package com.example.mvvm.mvvm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.model.Book
import com.example.mvvm.model.Country

class BooksAdapter(
    val onClick: (Book) -> Unit
) : RecyclerView.Adapter<BooksAdapter.ViewHolder>(){

    private var books: MutableList<Book> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return ViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books[position]
        holder.bind(book)
    }

    fun addAll(books: MutableList<Book>){
        this.books.clear()
        this.books = books
        notifyDataSetChanged()
    }

    fun add(book: Book){
        this.books.add(book)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) :  RecyclerView.ViewHolder(view){

        private val titleTv: TextView = view.findViewById(R.id.titleTv)
        private val idTv: TextView = view.findViewById(R.id.idTv)

        fun bind(book: Book){
            titleTv.text = book.title
            idTv.text = book.id.toString()
        }

    }

}