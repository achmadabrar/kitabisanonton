package com.abrar.kitabisanonton.domain.usecase.base

import com.abrar.kitabisanonton.domain.model.ApiError
import com.abrar.kitabisanonton.domain.model.ResponseNowPlaying

interface UseCaseNowPlaying<T> {

    fun onSuccess(result: ResponseNowPlaying)

    fun onError(apiError: ApiError?)

}