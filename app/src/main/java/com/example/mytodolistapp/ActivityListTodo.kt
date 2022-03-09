package com.example.mytodolistapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class ActivityListTodo : AppCompatActivity(), OnItemClickListener {
    private var data = ArrayList<ItemsViewModel>()
    private var adapter: CustomAdapter = CustomAdapter(data, this)
    private lateinit var recyclerview: RecyclerView
    private var mModificationPosition = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)

        recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

        val addItemButton: Button = findViewById(R.id.addItemButton)
        addItemButton.setOnClickListener {
            val intent = Intent(this, ActivityAddItem::class.java)
            startActivityForResult(intent, 200)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 200) {
            if (resultCode == Activity.RESULT_OK){
                val extras = data!!.extras

                if (extras != null) {
                    this.data.add(ItemsViewModel(extras.getString("status")!!,extras.getString("title")!!,extras.getString("description")!!, UUID.randomUUID().toString()))
                    this.adapter.notifyDataSetChanged()
                }
            }
        }
        if (requestCode == 300) {
            if (resultCode == Activity.RESULT_OK){
                val extras = data!!.extras

                if (extras != null) {
                    this.data[mModificationPosition].title = extras.getString("title")!!
                    this.data[mModificationPosition].status = extras.getString("status")!!
                    this.data[mModificationPosition].description = extras.getString("description")!!

                    this.adapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onItemClick(itemsViewModel: View) {
        mModificationPosition = recyclerview.layoutManager!!.getPosition(itemsViewModel)
        val bundle = Bundle()
        val intent = Intent(this, ActivityAddItem::class.java)
        bundle.putString("title",data[mModificationPosition].title)
        bundle.putString("description",data[mModificationPosition].description)
        bundle.putString("status",data[mModificationPosition].status)
        bundle.putString("uuid",data[mModificationPosition].uuid)
        intent.putExtras(bundle)
        startActivityForResult(intent,300)
    }
}