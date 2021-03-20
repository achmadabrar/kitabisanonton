package com.abrar.kitabisanonton.domain.usecase.base

import com.abrar.kitabisanonton.domain.model.ApiError
import com.abrar.kitabisanonton.domain.model.Result

interface UseCaseFavoriteMovie<Type> {

    fun onSuccess(result: List<Result>)

    fun onError(apiError: ApiError?)

}