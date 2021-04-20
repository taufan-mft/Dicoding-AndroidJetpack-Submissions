package com.topanlabs.filmtopan.list.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.topanlabs.filmtopan.data.TvModel
import com.topanlabs.filmtopan.databinding.ItemRowFilm2Binding

/**
 * Created by taufan-mft on 4/19/2021.
 */
class TvAdapter : RecyclerView.Adapter<TvAdapter.TvViewHolder>() {
    private val listFilms = ArrayList<TvModel>()

    fun setData(listFilms: List<TvModel>) {
        this.listFilms.clear()
        this.listFilms.addAll(listFilms)
        notifyDataSetChanged()
    }

    class TvViewHolder(private val binding: ItemRowFilm2Binding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: TvModel) {
            with(binding) {
                tvYear.text = tv.year
                imgView.setImageResource(tv.picture)
                itemView.setOnClickListener {

                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val binding = ItemRowFilm2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val film = listFilms[position]
        holder.bind(film)
    }

    override fun getItemCount(): Int {
        return listFilms.count()
    }
}