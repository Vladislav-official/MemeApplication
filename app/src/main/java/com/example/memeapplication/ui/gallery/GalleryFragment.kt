package com.example.memeapplication.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.memeapplication.R
import com.example.memeapplication.databinding.FragmentGalleryBinding
import com.example.memeapplication.ui.work_with_api.NotifyWork
import com.example.memeapplication.ui.work_with_api.RetrofitBuild
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.notify
import javax.inject.Inject

@AndroidEntryPoint
class GalleryFragment : Fragment() {

    @Inject
    lateinit var retrofitBuild : RetrofitBuild

    private lateinit var galleryViewModel: GalleryViewModel
    private var _binding: FragmentGalleryBinding? = null
    private var command : String? = "ALL"
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val buttonChangeCommand get() = binding.buttonChangeComand

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("command", command)
        super.onSaveInstanceState(outState)

    }


    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        command = savedInstanceState?.getString("command")
        Toast.makeText(this.context, "YOOOOO", Toast.LENGTH_LONG).show()
        buttonChangeCommand.performClick()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)
        galleryViewModel.initRetrofit(retrofitBuild)
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)

        val root: View = binding.root
        val recycleView = binding.recycleView


        buttonChangeCommand.setOnClickListener { Unit ->
            val uploadWorkRequest: WorkRequest =
                OneTimeWorkRequestBuilder<NotifyWork>()
                    .build()
            this.context?.let {
                WorkManager
                    .getInstance(it)
                    .enqueue(uploadWorkRequest)
            }
            if (command == "ALL"){
                command = "RANDOM"
                galleryViewModel.getRandomApi()
            }
            else {
                command = "ALL"
                galleryViewModel.getAllAPI()
            }
        }

        recycleView.layoutManager = LinearLayoutManager(context)

        val adapter= EntrieAdapter(bind = {
            item, holder, itemCount ->
            with(holder.itemView){
                findViewById<TextView>(R.id.title_view).text = item.API
                findViewById<TextView>(R.id.description_view).text = item.Description
            }
        })

        recycleView.adapter = adapter

        galleryViewModel.entries.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}