package com.topanlabs.filmtopan.list.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.topanlabs.filmtopan.databinding.FragmentTvBinding
import com.topanlabs.filmtopan.list.ListViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel


class tvFragment : Fragment() {
    private lateinit var fragmentTvBinding: FragmentTvBinding
    val viewModel: ListViewModel by sharedViewModel()
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
            val tvs = viewModel.getTv()
            val adapter = TvAdapter()
            adapter.setData(tvs)
            with(fragmentTvBinding.recView) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }

}