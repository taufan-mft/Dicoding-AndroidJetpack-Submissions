package com.topanlabs.filmtopan.list.film

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.topanlabs.filmtopan.data.FilmModel
import com.topanlabs.filmtopan.databinding.ItemRowFilmBinding

/**
 * Created by taufan-mft on 4/19/2021.
 */
class FilmAdapter : RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {
    private val listFilms = ArrayList<FilmModel>()

    fun setData(listFilms: List<FilmModel>) {
        this.listFilms.clear()
        this.listFilms.addAll(listFilms)
        notifyDataSetChanged()
    }

    class FilmViewHolder(private val binding: ItemRowFilmBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(film: FilmModel) {
            with(binding) {
                tvTitle.text = film.name
                tvYear.text = film.year
                tvTag.text = film.tags
                imageView.setImageResource(film.picture)
                itemView.setOnClickListener {

                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val binding = ItemRowFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val film = listFilms[position]
        holder.bind(film)
    }

    override fun getItemCount(): Int {
        return listFilms.count()
    }
}