package com.abrar.kitabisanonton.presentation.toprated

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abrar.kitabisanonton.domain.model.ApiError
import com.abrar.kitabisanonton.domain.model.ResponsePopular
import com.abrar.kitabisanonton.domain.model.ResponseTopRated
import com.abrar.kitabisanonton.domain.usecase.GetMovieUseCase
import com.abrar.kitabisanonton.domain.usecase.base.UseCasePopularMovie
import com.abrar.kitabisanonton.domain.usecase.base.UseCaseTopRated
import kotlinx.coroutines.cancel

class TopRatedViewModel constructor(private val getMovieUseCase: GetMovieUseCase) : ViewModel() {

    val movieTopRated = MutableLiveData<ResponseTopRated>()
    val showProgressbar = MutableLiveData<Boolean>()
    val messageData = MutableLiveData<String>()

    fun getTopRatedMovie() {
        showProgressbar.value = true
        getMovieUseCase.invokeTopRated(
            viewModelScope, null,
            object : UseCaseTopRated<ResponseTopRated> {
                override fun onSuccess(result: ResponseTopRated) {
                    Log.i(TAG, "resultTopRated: $result")
                    movieTopRated.value = result
                    showProgressbar.value = false
                }

                override fun onError(apiError: ApiError?) {
                    messageData.value = apiError?.getErrorMessage()
                    showProgressbar.value = false
                }
            },
        )
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    companion object {
        private val TAG = TopRatedViewModel::class.java.name
    }
}