package com.topanlabs.filmtopan.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.topanlabs.filmtopan.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    companion object {
        const val TYPE_TAG = "type_tag"
        const val ID_TAG = "id_tag"
        const val ID_FILM = "film"
        const val ID_TV = "tv"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]
        supportActionBar?.title = "Detail"
        val type = intent.getStringExtra(TYPE_TAG)
        if (type == ID_FILM) {
            val name = intent.getStringExtra(ID_TAG)
            viewModel.getFilm(name!!).apply {
                binding.imageView2.setImageResource(picture)
                binding.tvTitle.text = this.name
                binding.tvTags.text = tags
                binding.tvYear.text = "$year - $runtime"
                binding.tvLang.text = language
                binding.tvAge.text = ageRating
            }
        }
    }
}