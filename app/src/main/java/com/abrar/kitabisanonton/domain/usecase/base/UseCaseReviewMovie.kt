package com.abrar.kitabisanonton.domain.usecase.base

import com.abrar.kitabisanonton.domain.model.ApiError
import com.abrar.kitabisanonton.domain.model.ResponseReview

interface UseCaseReviewMovie<Type> {

    fun onSuccessReview(result: ResponseReview)

    fun onError(apiError: ApiError?)

}