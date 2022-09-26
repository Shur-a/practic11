package com.example.practic11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity3 : AppCompatActivity() {
    private val contactList: MutableList<contact> = mutableListOf()
    private lateinit var rv: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        getContacts()
        val adapter = ContactRVAdapter(this, contactList)
        val rvListener = object : ContactRVAdapter.ItemClickListener{
            override fun onItemClick(view: View?, position: Int) {
                val intent = Intent(this@MainActivity3, MainActivity2::class.java)
                intent.putExtra("num", position)
                var indexChanged = position
                startActivity(intent)
                Toast.makeText(this@MainActivity3, "position: $position", Toast.LENGTH_SHORT).show()
            }
        }
        adapter.seClickListener(rvListener)
        rv = findViewById(R.id.res)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)
    }

    private fun getContacts(){
        val preferences = getSharedPreferences("pref", MODE_PRIVATE)
        var json: String
        if (!preferences.contains("json")){
            return
        } else {
            json = preferences.getString("json", "NOT_JSON").toString()
        }
        val tempList = Gson().fromJson<List<contact>>(json, object: TypeToken<List<contact>>(){}.type)
        contactList.addAll(tempList)
    }
}


