package com.abrar.kitabisanonton.presentation.nowplaying

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abrar.kitabisanonton.domain.model.ApiError
import com.abrar.kitabisanonton.domain.model.ResponseNowPlaying
import com.abrar.kitabisanonton.domain.model.ResponsePopular
import com.abrar.kitabisanonton.domain.usecase.GetMovieUseCase
import com.abrar.kitabisanonton.domain.usecase.base.UseCaseNowPlaying
import com.abrar.kitabisanonton.domain.usecase.base.UseCasePopularMovie
import kotlinx.coroutines.cancel

class NowPlayingViewModel constructor(private val gateNowPlayingUseCase: GetMovieUseCase): ViewModel() {

    val movieNowPlaying = MutableLiveData<ResponseNowPlaying>()
    val showProgressbar = MutableLiveData<Boolean>()
    val messageData = MutableLiveData<String>()

    fun getNowPlayingMovie() {
        showProgressbar.value = true
        gateNowPlayingUseCase.invokeNowPlaying(
            viewModelScope, null,
            object : UseCaseNowPlaying<ResponseNowPlaying> {
                override fun onSuccess(result: ResponseNowPlaying) {
                    Log.i(TAG, "resultTopRated: $result")
                    movieNowPlaying.value = result
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
        private val TAG = NowPlayingViewModel::class.java.name
    }
}