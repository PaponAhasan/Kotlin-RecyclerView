package com.example.kotlinrecyclerview

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinrecyclerview.databinding.ActivityAddPersonBinding


class AddPersonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPersonBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPersonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPersonData.setOnClickListener {
            val personName = binding.etPersonName.text.toString()
            val personAge = binding.etPersonAge.text.toString().toInt()
            val person = Contact(personName, personAge)

            val intent = Intent()
            intent.putExtra("result", person)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}