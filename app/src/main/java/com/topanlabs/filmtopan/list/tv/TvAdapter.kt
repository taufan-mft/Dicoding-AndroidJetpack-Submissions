package com.topanlabs.filmtopan.list.tv

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.topanlabs.filmtopan.data.TvModel
import com.topanlabs.filmtopan.databinding.ItemRowFilm2Binding
import com.topanlabs.filmtopan.detail.DetailActivity

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
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.TYPE_TAG, DetailActivity.ID_TV)
                    intent.putExtra(DetailActivity.ID_TAG, tv.name)
                    itemView.context.startActivity(intent)
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