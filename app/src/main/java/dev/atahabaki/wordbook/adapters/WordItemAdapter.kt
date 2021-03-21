package dev.atahabaki.wordbook.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.atahabaki.wordbook.R
import dev.atahabaki.wordbook.data.entities.WordItem
import dev.atahabaki.wordbook.ui.viewmodels.WordBookViewModel

class WordItemAdapter(
    var items: List<WordItem>,
    private val viewModel: WordBookViewModel
): RecyclerView.Adapter<WordItemAdapter.WordViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.word_item, parent, false)
        return WordViewHolder(view)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val currWordItemView = items[position]
        holder.itemView.findViewById<TextView>(R.id.word_item_content).text = currWordItemView.content
        holder.itemView.findViewById<TextView>(R.id.word_item_meaning).text = currWordItemView.meaning
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun getWordAtPosition(position: Int): WordItem {
        return items[position]
    }

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}