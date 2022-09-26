package com.example.practic11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.edit
import com.example.practic11.databinding.ActivityMain2Binding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity2 : AppCompatActivity() {
    private var contactList: MutableList<contact> = mutableListOf()
    private lateinit var binding: ActivityMain2Binding
    private var ch: Int = 0
    private var posi: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val name = findViewById<EditText>(R.id.name)
        val surname = findViewById<EditText>(R.id.surname)
        val n_telefon = findViewById<EditText>(R.id.telefon)
        val job = findViewById<EditText>(R.id.job)
        getContacts()
        binding.button3.setOnClickListener {
            if (name.text.toString() != "" && surname.text.toString() != ""&& n_telefon.text.toString() != ""&& job.text.toString() != "") {
                if(ch==0){
                    contact(name.text.toString(), surname.text.toString(),n_telefon.text.toString(),job.text.toString())
                    addcontact(name.text.toString(), surname.text.toString(),n_telefon.text.toString(),job.text.toString())
                    Toast.makeText(this, "Контакт добавлен", Toast.LENGTH_SHORT).show()
                }else{
                    contactList[posi]= contact(name.text.toString(), surname.text.toString(),n_telefon.text.toString(),job.text.toString())
                    val preferences=getSharedPreferences("pref", MODE_PRIVATE)
                    preferences.edit(){
                        this.putString("json",Gson().toJson(contactList).toString())
                    }
                    val w = contactList[posi]
                    Toast.makeText(this,"$w",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Пустая строка!!!", Toast.LENGTH_SHORT).show()
            }

        }

    }

    override fun onResume() {
        super.onResume()
        val name = findViewById<EditText>(R.id.name)
        val surname = findViewById<EditText>(R.id.surname)
        val n_telefon = findViewById<EditText>(R.id.telefon)
        val job = findViewById<EditText>(R.id.job)
        posi=intent.getIntExtra("num",-1)
        if (posi!=-1){
            name.setText(contactList[posi].name)
            surname.setText(contactList[posi].surname)
            n_telefon.setText(contactList[posi].n_telefon)
            job.setText(contactList[posi].job)
            ch=1
            binding.button3.text="Изменить"
        }else{
            ch=0
            binding.button3.text="Добавить"
        }
    }
    private fun getContacts(){
        val preferences = getSharedPreferences("pref", MODE_PRIVATE)
        val json: String
        if (!preferences.contains("json")){
            return
        } else {
            json = preferences.getString("json", "NOT_JSON").toString()
        }
        val temp = Gson().fromJson<List<contact>>(json, object: TypeToken<List<contact>>(){}.type)
        contactList.addAll(temp)
    }
    private fun addcontact(name:String, surname:String,n_telefon:String,job:String){
        val contact = contact(name, surname, n_telefon,job)
        contactList.add(contact)
        val preferences = getSharedPreferences("pref", MODE_PRIVATE)
        preferences.edit {
            this.putString("json", Gson().toJson(contactList).toString())
        }
    }
}

