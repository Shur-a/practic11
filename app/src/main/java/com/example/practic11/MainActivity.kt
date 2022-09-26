package com.example.practic11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var but: Button
    private lateinit var but2: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        but=findViewById(R.id.button)
        but2=findViewById(R.id.button2)
        but.setOnClickListener {
            val intent:Intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
        but2.setOnClickListener {
            val intent:Intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }

    }
}