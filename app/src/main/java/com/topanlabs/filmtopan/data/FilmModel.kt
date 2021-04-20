package com.topanlabs.filmtopan.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
/**
 * Created by taufan-mft on 4/19/2021.
 */
@Parcelize
data class FilmModel(
        val name: String,
        val year: String,
        val userScore: String,
        val shortDesc: String,
        val language: String,
        val status: String,
        val budget: String,
        val income: String,
        val ageRating: String,
        val tags: String,
        val runtime: String,
        val picture: Int
) : Parcelable