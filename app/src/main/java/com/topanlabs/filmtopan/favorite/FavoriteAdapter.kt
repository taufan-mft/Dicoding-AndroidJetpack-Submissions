package com.topanlabs.filmtopan.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.topanlabs.filmtopan.databinding.ItemRowFilm2Binding
import com.topanlabs.filmtopan.db.ArtEntity
import com.topanlabs.filmtopan.detail.DetailActivity

/**
 * Created by taufan-mft on 5/8/2021.
 */
class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    private val list = ArrayList<ArtEntity>()

    fun setData(list: List<ArtEntity>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    class FavoriteViewHolder(private val binding: ItemRowFilm2Binding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ArtEntity) {
            with(binding) {
                tvYear.text = item.year
                Glide
                    .with(imgView.context)
                    .load(item.photo)
                    .into(imgView)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    if (item.type == "tv") {
                        intent.putExtra(DetailActivity.TYPE_TAG, DetailActivity.ID_TV)
                    } else {
                        intent.putExtra(DetailActivity.TYPE_TAG, DetailActivity.ID_FILM)
                    }
                    intent.putExtra(DetailActivity.ID_TAG, item.id)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteAdapter.FavoriteViewHolder {
        val binding =
            ItemRowFilm2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteAdapter.FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.FavoriteViewHolder, position: Int) {
        val art = list[position]
        holder.bind(art)
    }

    override fun getItemCount(): Int {
        return list.count()
    }
}