package com.topanlabs.filmtopan.list.film

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.topanlabs.filmtopan.databinding.FragmentFilmBinding
import com.topanlabs.filmtopan.list.ListViewModel


class filmFragment : Fragment() {
    lateinit var fragmentFilmBinding: FragmentFilmBinding
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
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[ListViewModel::class.java]
            val films = viewModel.getFilm()
            val adapter = FilmAdapter()
            adapter.setData(films)
            with(fragmentFilmBinding.recView) {

                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }

}