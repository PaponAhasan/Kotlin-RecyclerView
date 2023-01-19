package com.example.kotlinrecyclerview

import android.content.Context
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ContactsAdapter(private val context: Context, private val contacts: List<Contact>) :
    RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    // ... constructor and member variables
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(contact: Contact) {
            //...
            val nameTextView = itemView.findViewById<TextView>(R.id.tvName)
            nameTextView.text = contact.name
            //...
            val ageTextView = itemView.findViewById<TextView>(R.id.tvAge)
            ageTextView.text = "Age : ${contact.age}"
            //...
            val profileImageView = itemView.findViewById<ImageView>(R.id.ProfileIV)
            var imageUrl = contact.imageUrl
            if(context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
                imageUrl = contact.landscapeImageUrl
            }
            Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_baseline_image_not_supported_24)
                .into(profileImageView)
        }
    }

    // ... involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate the custom layout & Return a new holder instance
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false)
        )
    }

    // ... Involves populating data into the item through holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val contact = contacts[position]
        holder.bind(contact)
        // Set item views based on your views and data model
    }

    // ... Returns the total count of items in the list
    override fun getItemCount(): Int {
        return contacts.size
    }
}