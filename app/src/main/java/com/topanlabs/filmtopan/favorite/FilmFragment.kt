package com.topanlabs.filmtopan.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.topanlabs.filmtopan.databinding.FragmentFilm2Binding
import com.topanlabs.filmtopan.detail.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class FilmFragment : Fragment() {
    private lateinit var binding: FragmentFilm2Binding
    val viewModel: DetailViewModel by sharedViewModel()
    val adapterFavorite = FavoriteAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFilm2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {


            with(binding.recView) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(false)
                this.adapter = adapterFavorite
            }
        }
        loadData()
    }

    private fun loadData() {
        viewModel.allLikedArts("film").observe(viewLifecycleOwner) { arts ->
            arts.let { adapterFavorite.setData(arts) }
            binding.progressBar.visibility = View.GONE
            if (arts.loadedCount == 0) {
                binding.recView.visibility = View.GONE
            } else {
                binding.recView.visibility = View.VISIBLE
            }
        }
    }

}