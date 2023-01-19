package com.example.kotlinrecyclerview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object{
        private const val TAG = "MainActivity"
    }
    private lateinit var binding: ActivityMainBinding

    private var contactsList = mutableListOf<Contact>()
    private lateinit var contactsAdapter: ContactsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.e(TAG, "onCreate")

        //contactsList = mutableListOf()<Contact>()
        //val contacts = Contact.createContactsList(150)

        /* 01. Adapter For Recycler View*/
        contactsAdapter = ContactsAdapter(this, contactsList)
        binding.rvContacts.adapter = contactsAdapter

        val layoutManager = LinearLayoutManager(this)
        // Optionally customize the position you want to default scroll to
        layoutManager.scrollToPosition(0)
        // Attach layout manager to the RecyclerView
        binding.rvContacts.layoutManager = layoutManager

        /* 02. ViewModel for screen rotation problem solved*/
        val model = ViewModelProvider(this)[MainActivityViewModel::class.java]
        model.getContacts().observe(this, Observer { contactSnapshot ->
            // Update the UI
            Log.e(TAG, "received contacts from the view model")
            contactsList.clear()
            contactsList.addAll(contactSnapshot)
            contactsAdapter.notifyDataSetChanged()
        })

        /* 03. swipe refresh layout*/
        // Set the color scheme for the swipe refresh layout
        binding.swipeContainer.setColorSchemeColors(
            ContextCompat.getColor(this, R.color.purple_200),
            ContextCompat.getColor(this, R.color.purple_500),
            ContextCompat.getColor(this, R.color.purple_700)
        )

        model.getIsRefreshing().observe(this, Observer { isRefreshing ->
            Log.i(TAG, "Received isRefreshing from view model")
            binding.swipeContainer.isRefreshing = isRefreshing
        })

        binding.swipeContainer.setOnRefreshListener {
            // Show the Refreshing UI and fetch the new data
            Log.e(TAG, "swipeContainer")
            model.fetchNewContact()
        }

    }

    /* 04. Menu and Intent*/
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_layout, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.plusIcon -> {
                Log.e(TAG, "onOptionsItemSelected")
                val intent = Intent(this, AddPersonActivity::class.java)
                getResult.launch(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private var getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data = result.data?.getSerializableExtra("result") as Contact
            contactsList.add(5, data)
            contactsAdapter.notifyDataSetChanged()
        }
    }

}