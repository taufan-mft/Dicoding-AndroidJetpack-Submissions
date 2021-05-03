package com.topanlabs.filmtopan.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.topanlabs.filmtopan.data.FilmDetailData
import com.topanlabs.filmtopan.data.TvDetailData
import com.topanlabs.filmtopan.databinding.ActivityDetailBinding
import com.topanlabs.filmtopan.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

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
            changeVisibility(View.GONE)
            supportActionBar?.title = "Detail Film"
            intent.getIntExtra(ID_TAG, 0).let {
                viewModel.getFilmDetail(it).observe(this, Observer {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                val data = resource.data as FilmDetailData
                                binding.tvTitle.text = data.originalTitle
                                Glide.with(applicationContext)
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
                                binding.tvYear.text = data.releaseDate.substring(0, 4)
                                lifecycleScope.launch(Dispatchers.Main) {
                                    val rating = viewModel.getFilmRating(data.id)
                                    binding.tvAge.text = rating
                                    binding.progressBar.visibility = View.GONE
                                    changeVisibility(View.VISIBLE)
                                }

                            }
                            Status.ERROR -> {
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                })
            }
        } else {
            supportActionBar?.title = "Detail TV Show"
            intent.getIntExtra(ID_TAG, 0).let {
                changeVisibility(View.GONE)
                viewModel.getTvDetail(it).observe(this, Observer {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                val data = resource.data as TvDetailData
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
                                binding.tvYear.text = data.firstAirDate.substring(0, 4)

                                lifecycleScope.launch(Dispatchers.Main) {
                                    val rating = viewModel.getTvRating(data.id)
                                    binding.tvAge.text = rating
                                    binding.progressBar.visibility = View.GONE
                                    changeVisibility(View.VISIBLE)
                                }
                            }
                            Status.ERROR -> {
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                })
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun changeVisibility(visible: Int) {
        binding.tvYear.visibility = visible
        binding.tvTitle.visibility = visible
        binding.imageView2.visibility = visible
        binding.tvTags.visibility = visible
        binding.tvScore.visibility = visible
        binding.tvAge.visibility = visible
        binding.tvLang.visibility = visible
        binding.tvOverview.visibility = visible
        binding.tvStatus.visibility = visible
        binding.stat.visibility = visible
        binding.oView.visibility = visible
        binding.lang.visibility = visible
        binding.score.visibility = visible
    }
}