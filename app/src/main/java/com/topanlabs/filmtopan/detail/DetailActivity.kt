package com.topanlabs.filmtopan.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.topanlabs.filmtopan.data.TvDetailData
import com.topanlabs.filmtopan.databinding.ActivityDetailBinding
import com.topanlabs.filmtopan.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    companion object {
        const val TYPE_TAG = "type_tag"
        const val ID_TAG = "id_tag"
        const val ID_FILM = "film"
        const val ID_TV = "tv"
    }

    private lateinit var binding: ActivityDetailBinding
    val viewModel: DetailViewModel by viewModel()
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        val type = intent.getStringExtra(TYPE_TAG)
        if (type == ID_FILM) {
            supportActionBar?.title = "Detail Film"
            val name = intent.getStringExtra(ID_TAG)
            viewModel.getFilm(name!!).apply {
                binding.imageView2.setImageResource(picture)
                binding.tvTitle.text = this.name
                binding.tvTags.text = tags
                binding.tvYear.text = "$year - $runtime"
                binding.tvLang.text = language
                binding.tvAge.text = ageRating
                binding.tvStatus.text = status
                binding.tvScore.text = "${userScore}%"
                binding.tvOverview.text = shortDesc

            }
        } else {
            supportActionBar?.title = "Detail TV Show"
            intent.getIntExtra(ID_TAG, 0).let {
                lifecycleScope.launch(Dispatchers.Main) {
                    val film = viewModel.getTvDetail(it)
                    when (film.status) {
                        Status.SUCCESS -> {
                            val data = film.data as TvDetailData
                            binding.tvTitle.text = data.originalName
                            Glide
                                .with(applicationContext)
                                .load("https://image.tmdb.org/t/p/original/${data.posterPath}")
                                .into(binding.imageView2)
                            val builder = StringBuilder()
                            val iterator = data.genres.iterator()
                            while (iterator.hasNext()) {
                                val obj = iterator.next()
                                if (iterator.hasNext()) {
                                    builder.append(obj.name)
                                    builder.append(", ")
                                } else {
                                    builder.append(obj.name)
                                }
                            }
                            binding.tvTags.text = builder
                            data.originalLanguage.let {
                                when (it) {
                                    "en" -> binding.tvLang.text = "English"
                                    "es" -> binding.tvLang.text = "Espanol"
                                    "tr" -> binding.tvLang.text = "Turkish"
                                    "ja" -> binding.tvLang.text = "Japanese"
                                    "pl" -> binding.tvLang.text = "Polish"
                                    else -> binding.tvLang.text = it
                                }
                            }
                            binding.tvStatus.text = data.status
                            binding.tvOverview.text = data.overview
                            binding.tvScore.text =
                                (data.voteAverage * 10).toString().substring(0, 2) + "%"

                        }
                        Status.ERROR -> {
                            // recyclerView.visibility = View.VISIBLE
                            // progressBar.visibility = View.GONE
                            Toast.makeText(applicationContext, film.message, Toast.LENGTH_LONG)
                                .show()
                        }
                        Status.LOADING -> {
                            //  progressBar.visibility = View.VISIBLE
                            // recyclerView.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}