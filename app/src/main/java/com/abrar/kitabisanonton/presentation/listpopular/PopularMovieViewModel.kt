package com.abrar.kitabisanonton.presentation.listpopular

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abrar.kitabisanonton.data.source.local.dao.PopularDao
import com.abrar.kitabisanonton.domain.model.ApiError
import com.abrar.kitabisanonton.domain.model.ResponsePopular
import com.abrar.kitabisanonton.domain.model.ResponseTopRated
import com.abrar.kitabisanonton.domain.usecase.GetMovieUseCase
import com.abrar.kitabisanonton.domain.usecase.base.UseCasePopularMovie
import com.abrar.kitabisanonton.domain.usecase.base.UseCaseTopRated
import kotlinx.coroutines.cancel

class PopularMovieViewModel constructor(private val getMovieUseCase: GetMovieUseCase, private val popularDao: PopularDao) : ViewModel(){

    val moviePopular = MutableLiveData<ResponsePopular>()
    val showProgressbar = MutableLiveData<Boolean>()
    val messageData = MutableLiveData<String>()

    fun getMoviePopular() {
        showProgressbar.value = true
        getMovieUseCase.invokePopular(popularDao, viewModelScope, null, object : UseCasePopularMovie<ResponsePopular> {
            override fun onSuccess(result: ResponsePopular) {
                Log.i(TAG, "result: $result")
                moviePopular.value = result
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
        private val TAG = PopularMovieViewModel::class.java.name
    }
}