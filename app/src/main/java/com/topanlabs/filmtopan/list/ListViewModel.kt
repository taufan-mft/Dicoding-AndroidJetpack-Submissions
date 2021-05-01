package com.topanlabs.filmtopan.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.topanlabs.filmtopan.data.DataRepository
import com.topanlabs.filmtopan.data.TvFactory
import com.topanlabs.filmtopan.data.TvModel
import com.topanlabs.filmtopan.utils.Resource
import kotlinx.coroutines.Dispatchers

/**
 * Created by taufan-mft on 4/19/2021.
 */
class ListViewModel(val repository: DataRepository) : ViewModel() {

    fun getFilm() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getFilms()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getTv(): List<TvModel> = TvFactory.listData
}