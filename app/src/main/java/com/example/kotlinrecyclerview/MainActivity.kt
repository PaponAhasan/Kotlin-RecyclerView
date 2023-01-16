package com.example.kotlinrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val contacts = createContacts(150)
        //val contacts = Contact.createContactsList(150)

        binding.rvContacts.adapter = ContactsAdapter(this, contacts)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.scrollToPosition(0)
        binding.rvContacts.layoutManager = layoutManager
    }

    private fun createContacts(numContacts : Int) : List<Contact>{
        val contacts = mutableListOf<Contact>()
        for (i in 1..numContacts){
            contacts.add(Contact("Person #$i", i))
        }
        return contacts
    }
}