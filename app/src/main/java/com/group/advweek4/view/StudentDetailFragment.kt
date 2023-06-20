package com.group.advweek4.view

import android.database.Observable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.group.advweek4.R
import com.group.advweek4.databinding.FragmentStudentDetailBinding
import com.group.advweek4.util.loadImage
import com.group.advweek4.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_student_detail.*
import java.util.concurrent.TimeUnit


class StudentDetailFragment : Fragment(), ButtonNotifClickListener, ButtonUpdateClickListener {
    private lateinit var viewModel: DetailViewModel
    private lateinit var dataBinding: FragmentStudentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_student_detail, container, false)
        dataBinding =DataBindingUtil.inflate<FragmentStudentDetailBinding>(
            inflater, R.layout.fragment_student_detail, container, false
        )
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null){
            val id = StudentDetailFragmentArgs.fromBundle(requireArguments()).id
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            viewModel.fetch(id)
        }
        dataBinding.listenerUpdate = this
        dataBinding.listenerNotif = this

        observeViewModel(view)
    }

    fun observeViewModel(view:View) {
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
//            view.findViewById<EditText>(R.id.txtID).setText(it.id)
//            view.findViewById<EditText>(R.id.txtName).setText(it.name)
//            view.findViewById<EditText>(R.id.txtBod).setText(it.bod)
//            view.findViewById<EditText>(R.id.txtPhone).setText(it.phone)
//            view.findViewById<ImageView>(R.id.imageView2).loadImage(it.photoUrl,
//                view.findViewById<ProgressBar>(R.id.progressBar2))
//
//            var student=it
//
//            btnNotif.setOnClickListener {
//                io.reactivex.rxjava3.core.Observable.timer(5, TimeUnit.SECONDS)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe {
//                        Log.d("Messages", "five seconds")
//                        MainActivity.showNotification(student.name.toString(),
//                            "A new notification created",
//                            R.drawable.ic_baseline_error_24)
//                    }
//            }
            dataBinding.student=it
        })
    }

    override fun onButtonUpdateClick(v: View) {
        Toast.makeText(this.context, "Updated:"+dataBinding.student?.name, Toast.LENGTH_SHORT).show()
    }

    override fun onButtonNotifClick(v: View) {
        io.reactivex.rxjava3.core.Observable.timer(5, TimeUnit.SECONDS)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
         .subscribe {
             Log.d("Messages", "five seconds")
             MainActivity.showNotification(dataBinding.student?.name.toString(),
                            "A new notification created",
                            R.drawable.ic_baseline_error_24)
         }
    }
}