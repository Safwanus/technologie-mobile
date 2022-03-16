package com.example.mytodolistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fm: FragmentManager = supportFragmentManager
        fm.beginTransaction().replace(R.id.fragment_list, FragmentList()).commit()
        fm.beginTransaction().replace(R.id.fragment_item, FragmentItem()).commit()
    }
}