package com.abrar.kitabisanonton.domain.usecase.base

import com.abrar.kitabisanonton.domain.model.ApiError
import com.abrar.kitabisanonton.domain.model.Result

interface UseCaseDetailMovie<Type> {

    fun onSuccessDetail(result: Result)

    fun onError(apiError: ApiError?)

}