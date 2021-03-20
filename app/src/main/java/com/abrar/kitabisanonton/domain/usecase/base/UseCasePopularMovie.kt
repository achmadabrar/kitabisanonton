package com.abrar.kitabisanonton.domain.usecase.base

import com.abrar.kitabisanonton.domain.model.ApiError
import com.abrar.kitabisanonton.domain.model.ResponsePopular

interface UseCasePopularMovie<Type> {

    fun onSuccess(result: ResponsePopular)

    fun onError(apiError: ApiError?)

}