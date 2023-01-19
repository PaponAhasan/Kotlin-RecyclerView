package com.example.kotlinrecyclerview

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.os.postDelayed
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private const val TAG = "MainActivityViewModel"

class MainActivityViewModel : ViewModel() {
    private val contactLiveData: MutableLiveData<MutableList<Contact>>
    private val isRefreshingLiveData: MutableLiveData<Boolean>

    init {
        Log.e(TAG, "init")
        contactLiveData = MutableLiveData()
        contactLiveData.value = createContacts()

        isRefreshingLiveData = MutableLiveData()
        isRefreshingLiveData.value = false
    }

    fun getContacts(): LiveData<MutableList<Contact>> {
        Log.e(TAG, "getContacts")
        return contactLiveData
    }

    /* Remember that ViewModel hold all UI data.*/
    private fun createContacts(): MutableList<Contact> {
        val contacts = mutableListOf<Contact>()
        for (i in 1..200) {
            contacts.add(Contact("Person #$i", i))
        }
        return contacts
    }

    fun getIsRefreshing(): LiveData<Boolean> {
        Log.e(TAG, "getIsRefreshing")
        return isRefreshingLiveData
    }

    fun fetchNewContact() {
        Log.e(TAG, "fetchNewContact")
        // indicated that we're in refreshing state
        isRefreshingLiveData.value = true

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            Log.e(TAG, "Handler(Looper")
            // add the new contact
            val contacts = contactLiveData.value
            contacts?.add(0, Contact("Papon", 24))
            contactLiveData.value = contacts
            isRefreshingLiveData.value = false
        }, 1_000)
    }
}