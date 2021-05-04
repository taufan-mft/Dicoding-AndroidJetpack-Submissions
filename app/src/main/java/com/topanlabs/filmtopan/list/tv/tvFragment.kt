package com.topanlabs.filmtopan.list.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.topanlabs.filmtopan.data.ResultX
import com.topanlabs.filmtopan.data.TmTvHead
import com.topanlabs.filmtopan.databinding.FragmentTvBinding
import com.topanlabs.filmtopan.list.ListViewModel
import com.topanlabs.filmtopan.utils.Status
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class tvFragment : Fragment() {
    private lateinit var fragmentTvBinding: FragmentTvBinding
    val viewModel: ListViewModel by sharedViewModel()
    val adapter = TvAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentTvBinding = FragmentTvBinding.inflate(inflater, container, false)
        return fragmentTvBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            with(fragmentTvBinding.recView) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
        setObservers()
    }

    private fun setObservers() {
        viewModel.getTvku()
        viewModel.tvs.observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        // recyclerView.visibility = View.VISIBLE
                        fragmentTvBinding.progressBar.visibility = View.GONE
                        resource.data?.let { results ->
                            results as TmTvHead
                            updateData(results.results)
                        }
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

    private fun updateData(results: List<ResultX>) {
        fragmentTvBinding.recView.adapter = adapter
        adapter.setData(results)
    }

}