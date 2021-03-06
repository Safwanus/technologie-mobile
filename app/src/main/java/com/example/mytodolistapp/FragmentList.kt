package com.example.mytodolistapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

private const val uuid: String = ""
private const val status: String = ""
private const val title: String = ""
private const val description: String = ""
private val data = ArrayList<ItemsViewModel>()
private const val mModificationPosition = -1

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentItem.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentList : Fragment(), OnItemClickListener {
    // TODO: Rename and change types of parameters
    private lateinit var adapter: CustomAdapter
    private lateinit var recyclerview: RecyclerView
    private var data = ArrayList<ItemsViewModel>()
    private var mModificationPosition = -1
    private var uuid: String? = null
    private var status: String? = null
    private var title: String? = null
    private var description: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            uuid = it.getString(uuid)
            status = it.getString(status)
            title = it.getString(title)
            description = it.getString(description)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.requireActivity().setContentView(R.layout.fragment_list)

        adapter =  CustomAdapter(data, this)

        recyclerview = this.requireActivity().findViewById(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(context)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

        val addItemButton: Button = this.requireActivity().findViewById(R.id.addItemButton)
        addItemButton.setOnClickListener {
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
        bundle.putString("title",data[mModificationPosition].title)
        bundle.putString("description",data[mModificationPosition].description)
        bundle.putString("status",data[mModificationPosition].status)
        bundle.putString("uuid",data[mModificationPosition].uuid)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(uuid: String, status: String, title: String, description: String) =
            FragmentList().apply {
                arguments = Bundle().apply {
                    putString(uuid, uuid)
                    putString(status, status)
                    putString(title, title)
                    putString(description, description)
                }
            }
    }
}