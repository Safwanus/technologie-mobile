package com.example.mytodolistapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentItem.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentItem : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.requireActivity().setContentView(R.layout.fragment_item)
        val bundle = Bundle()

        val finishAddingButton: Button = this.requireActivity().findViewById(R.id.finishAddingButton)
        val titleEditText: EditText = this.requireActivity().findViewById(R.id.editTextTitle)
        val descriptionEditText: EditText = this.requireActivity().findViewById(R.id.editTextDescription)
        val extras = this.requireActivity().intent.extras

        if (extras != null) {
            if(!extras!!.getString("uuid").isNullOrEmpty()) {
                titleEditText.setText(extras!!.getString("title"))
                descriptionEditText.setText(extras!!.getString("description"))
                finishAddingButton.text = "Modify"
            }}

        finishAddingButton.setOnClickListener {
            val intent = Intent(context, ActivityListTodo::class.java)
            bundle.putString("status", "TODO")
            bundle.putString("title", titleEditText.text.toString())
            bundle.putString("description", descriptionEditText.text.toString())
            intent.putExtras(bundle)
            this.requireActivity().setResult(Activity.RESULT_OK, intent)
            this.requireActivity().finish()
        }
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
        fun newInstance(param1: String, param2: String) =
            FragmentList().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}