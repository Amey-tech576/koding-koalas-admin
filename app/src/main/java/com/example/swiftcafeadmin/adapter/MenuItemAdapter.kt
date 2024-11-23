package com.example.swiftcafeadmin.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.swiftcafeadmin.databinding.ItemItemBinding
import com.example.swiftcafeadmin.model.AllMenu
import com.google.firebase.database.DatabaseReference

class MenuItemAdapter(
    private val context: Context,
    private val menuList: ArrayList<AllMenu>,
    private val databaseReference: DatabaseReference,
    private val onDeleteClickListener: (position: Int) -> Unit
) : RecyclerView.Adapter<MenuItemAdapter.AddItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddItemViewHolder {
        val binding = ItemItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddItemViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = menuList.size

    inner class AddItemViewHolder(private val binding: ItemItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val menuItem = menuList[position]
            val uriString = menuItem.foodImage
            val uri = Uri.parse(uriString)

            binding.apply {
                // Set data to views
                foodNameTextView.text = menuItem.foodName
                priceTextView.text = menuItem.foodPrice
                priceTextView.text = "₹ ${menuItem.foodPrice}"
                Glide.with(context).load(uri).into(foodImageView)

                // Setup delete button click listener
                deleteButton.setOnClickListener {
                    onDeleteClickListener(position)
                }
            }
        }
    }
}
