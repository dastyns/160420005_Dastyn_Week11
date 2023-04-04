package com.group.advweek4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.group.advweek4.R
import com.group.advweek4.util.loadImage
import com.group.advweek4.viewmodel.DetailViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class StudentDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null){
            val id = StudentDetailFragmentArgs.fromBundle(requireArguments()).id
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            viewModel.fetch(id)
        }

        observeViewModel(view)
    }

    fun observeViewModel(view:View) {
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            view.findViewById<EditText>(R.id.txtID).setText(it.id)
            view.findViewById<EditText>(R.id.txtName).setText(it.name)
            view.findViewById<EditText>(R.id.txtBod).setText(it.bod)
            view.findViewById<EditText>(R.id.txtPhone).setText(it.phone)
            view.findViewById<ImageView>(R.id.imageView2).loadImage(it.photoUrl,
                view.findViewById<ProgressBar>(R.id.progressBar2))
        })
    }
}