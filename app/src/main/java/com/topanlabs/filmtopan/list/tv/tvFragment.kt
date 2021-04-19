package com.topanlabs.filmtopan.list.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.topanlabs.filmtopan.databinding.FragmentTvBinding
import com.topanlabs.filmtopan.list.ListViewModel


class tvFragment : Fragment() {
    lateinit var fragmentTvBinding: FragmentTvBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        fragmentTvBinding = FragmentTvBinding.inflate(inflater, container, false)
        return fragmentTvBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[ListViewModel::class.java]
            val tvs = viewModel.getTv()
            val adapter = TvAdapter()
            adapter.setData(tvs)
            with(fragmentTvBinding.recView) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }

}