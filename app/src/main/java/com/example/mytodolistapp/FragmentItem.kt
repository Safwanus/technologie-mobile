package com.example.mytodolistapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

private const val uuid: String = ""
private const val status: String = ""
private const val title: String = ""
private const val description: String = ""

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentItem.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentItem : Fragment() {
    // TODO: Rename and change types of parameters
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
        this.requireActivity().setContentView(R.layout.fragment_item)

        val finishAddingButton: Button = this.requireActivity().findViewById(R.id.finishAddingButton)
        val titleEditText: EditText = this.requireActivity().findViewById(R.id.editTextTitle)
        val descriptionEditText: EditText = this.requireActivity().findViewById(R.id.editTextDescription)

        if(uuid.isNullOrEmpty()) {
            titleEditText.setText(title)
            descriptionEditText.setText(description)
            finishAddingButton.text = "Modify"
        }

        finishAddingButton.setOnClickListener {
            //TODO: Send data to Activity
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