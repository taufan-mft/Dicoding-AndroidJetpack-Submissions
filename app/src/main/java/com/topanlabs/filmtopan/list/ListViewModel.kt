package com.topanlabs.filmtopan.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.topanlabs.filmtopan.data.DataRepository
import com.topanlabs.filmtopan.utils.EspressoIdlingResource
import com.topanlabs.filmtopan.utils.Resource
import kotlinx.coroutines.Dispatchers

/**
 * Created by taufan-mft on 4/19/2021.
 */
class ListViewModel(val repository: DataRepository, val espresso: EspressoIdlingResource) : ViewModel() {

    fun getFilm() = liveData(Dispatchers.IO) {
        espresso.increment()
        try {
            emit(Resource.success(data = repository.getFilms()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
        espresso.decrement()

    }

    fun getTv() = liveData(Dispatchers.IO) {
        espresso.increment()
        try {
            emit(Resource.success(data = repository.getTvs()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }

        espresso.decrement()

    }

}