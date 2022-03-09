package com.example.mytodolistapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class ActivityAddItem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)
        val bundle = Bundle()

        val finishAddingButton: Button = findViewById(R.id.finishAddingButton)
        val titleEditText: EditText = findViewById(R.id.editTextTitle)
        val descriptionEditText: EditText = findViewById(R.id.editTextDescription)
        val extras = intent.extras

        if (extras != null) {
        if(!extras!!.getString("uuid").isNullOrEmpty()) {
            titleEditText.setText(extras!!.getString("title"))
            descriptionEditText.setText(extras!!.getString("description"))
            finishAddingButton.text = "Modify"
        }}

        finishAddingButton.setOnClickListener {
            val intent = Intent(this, ActivityListTodo::class.java)
            bundle.putString("status", "TODO")
            bundle.putString("title", titleEditText.text.toString())
            bundle.putString("description", descriptionEditText.text.toString())
            intent.putExtras(bundle)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}