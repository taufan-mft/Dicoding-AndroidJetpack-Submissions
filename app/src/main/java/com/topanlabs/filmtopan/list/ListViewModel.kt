package com.topanlabs.filmtopan.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.topanlabs.filmtopan.data.DataRepository
import com.topanlabs.filmtopan.utils.EspressoIdlingResource
import com.topanlabs.filmtopan.utils.Resource
import kotlinx.coroutines.launch

/**
 * Created by taufan-mft on 4/19/2021.
 */
class ListViewModel(val repository: DataRepository, val espresso: EspressoIdlingResource) : ViewModel() {

    val films = MutableLiveData<Resource<Any>>()
    val tvs = MutableLiveData<Resource<Any>>()


    fun getFilmku() {
        espresso.increment()
        viewModelScope.launch {
            try {
                films.postValue(Resource.success(data = repository.getFilms()))
            } catch (exception: Exception) {
                films.postValue(
                    Resource.error(
                        data = null,
                        message = exception.message ?: "Error Occurred!"
                    )
                )
            }
            espresso.decrement()
        }

    }


    fun getTvku() {
        espresso.increment()
        viewModelScope.launch {
            try {
                tvs.postValue(Resource.success(data = repository.getTvs()))
            } catch (exception: Exception) {
                tvs.postValue(
                    Resource.error(
                        data = null,
                        message = exception.message ?: "Error Occurred!"
                    )
                )
            }
            espresso.decrement()
        }
    }

}