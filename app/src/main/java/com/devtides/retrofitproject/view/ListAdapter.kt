package com.devtides.retrofitproject.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devtides.retrofitproject.databinding.CategoryLayoutBinding
import com.devtides.retrofitproject.databinding.ItemLayoutBinding
import com.devtides.retrofitproject.model.Item
import com.devtides.retrofitproject.model.TYPE_ITEM
import java.util.*

class ListAdapter(val items: ArrayList<Item>) :
    RecyclerView.Adapter<ListAdapter.AdapterViewHolder>() {

    fun updateItems(newItems: List<Item>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        return if (viewType == TYPE_ITEM)

            ItemViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        else
            CategoryViewHolder(CategoryLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.bind(items[position])
    }

    abstract class AdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(item: Item)
    }

    class ItemViewHolder(val binding: ItemLayoutBinding) : AdapterViewHolder(binding.root) {
        private val title = binding.itemTitle
        private val text = binding.itemText
        override fun bind(item: Item) {
            title.text = item.key
            text.text = item.value
        }
    }

    class CategoryViewHolder(binding: CategoryLayoutBinding) : AdapterViewHolder(binding.root) {
        private val categoryTitle = binding.categoryTitle
        override fun bind(item: Item) {
            categoryTitle.text = item.key.uppercase(Locale.getDefault())
        }
    }
}