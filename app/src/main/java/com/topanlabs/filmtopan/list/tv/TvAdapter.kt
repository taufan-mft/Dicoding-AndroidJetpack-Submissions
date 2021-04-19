package com.topanlabs.filmtopan.list.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.topanlabs.filmtopan.data.TvModel
import com.topanlabs.filmtopan.databinding.ItemRowFilmBinding

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

    class TvViewHolder(private val binding: ItemRowFilmBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: TvModel) {
            with(binding) {
                tvTitle.text = tv.name
                tvYear.text = tv.year
                tvTag.text = tv.tags
                imageView.setImageResource(tv.picture)
                itemView.setOnClickListener {

                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val binding = ItemRowFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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