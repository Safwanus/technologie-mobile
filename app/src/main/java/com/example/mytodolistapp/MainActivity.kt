package com.example.mytodolistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val uuid: String = ""
        val status: String = ""
        val title: String = ""
        val description: String = ""

        val fm: FragmentManager = supportFragmentManager
        fm.beginTransaction().replace(R.id.fragment_list, FragmentList.newInstance(uuid, status, title, description)).commit()
        fm.beginTransaction().replace(R.id.fragment_item, FragmentItem.newInstance(uuid, status, title, description)).commit()
    }
}