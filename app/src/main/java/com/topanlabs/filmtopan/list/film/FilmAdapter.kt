package com.topanlabs.filmtopan.list.film

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.topanlabs.filmtopan.data.FilmModel
import com.topanlabs.filmtopan.databinding.ItemRowFilm2Binding
import com.topanlabs.filmtopan.detail.DetailActivity

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

    class FilmViewHolder(private val binding: ItemRowFilm2Binding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(film: FilmModel) {
            with(binding) {
                tvYear.text = film.year
                imgView.setImageResource(film.picture)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.TYPE_TAG, DetailActivity.ID_FILM)
                    intent.putExtra(DetailActivity.ID_TAG, film.name)
                    itemView.context.startActivity(intent)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val binding = ItemRowFilm2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
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