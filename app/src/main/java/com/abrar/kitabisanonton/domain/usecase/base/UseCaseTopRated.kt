package com.abrar.kitabisanonton.domain.usecase.base

import com.abrar.kitabisanonton.domain.model.ApiError
import com.abrar.kitabisanonton.domain.model.ResponsePopular
import com.abrar.kitabisanonton.domain.model.ResponseTopRated

interface UseCaseTopRated<Type> {

    fun onSuccess(result: ResponseTopRated)

    fun onError(apiError: ApiError?)

}