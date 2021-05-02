package com.topanlabs.filmtopan.list.film

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.topanlabs.filmtopan.data.Result
import com.topanlabs.filmtopan.databinding.FragmentFilmBinding
import com.topanlabs.filmtopan.list.ListViewModel
import com.topanlabs.filmtopan.utils.Status
import org.koin.android.viewmodel.ext.android.sharedViewModel


class filmFragment : Fragment() {
    private lateinit var fragmentFilmBinding: FragmentFilmBinding
    val viewModel: ListViewModel by sharedViewModel()
    var adapter = FilmAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentFilmBinding = FragmentFilmBinding.inflate(inflater, container, false)
        return fragmentFilmBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {


            with(fragmentFilmBinding.recView) {

                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(false)
                this.adapter = adapter
            }
        }
        setObservers()
    }

    private fun setObservers() {
        viewModel.getFilm().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        // recyclerView.visibility = View.VISIBLE
                        fragmentFilmBinding.progressBar.visibility = View.GONE
                        resource.data?.let { results -> updateData(results.results) }
                    }
                    Status.ERROR -> {
                        // recyclerView.visibility = View.VISIBLE
                        // progressBar.visibility = View.GONE
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        //  progressBar.visibility = View.VISIBLE
                        // recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun updateData(results: List<Result>) {
      fragmentFilmBinding.recView.adapter = adapter
        adapter.setData(results)
    }
}