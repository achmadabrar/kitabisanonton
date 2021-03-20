package com.abrar.kitabisanonton.presentation.detailmovie

import android.app.Activity
import android.app.Dialog
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abrar.kitabisanonton.R
import com.abrar.kitabisanonton.data.source.local.dao.FavoriteMovieDao
import com.abrar.kitabisanonton.domain.model.ApiError
import com.abrar.kitabisanonton.domain.model.ResponseReview
import com.abrar.kitabisanonton.domain.model.Result
import com.abrar.kitabisanonton.domain.usecase.GetMovieUseCase
import com.abrar.kitabisanonton.domain.usecase.base.UseCaseDetailMovie
import com.abrar.kitabisanonton.domain.usecase.base.UseCaseFavoriteMovie
import com.abrar.kitabisanonton.domain.usecase.base.UseCaseReviewMovie
import com.abrar.kitabisanonton.presentation.favorite.FavoriteMovieFragment
import kotlinx.coroutines.cancel

class DetailMovieViewModel constructor(
    private val getMovieUseCase: GetMovieUseCase,
    private val favoriteMovieDao: FavoriteMovieDao
) : ViewModel() {

    val movieDetail = MutableLiveData<Result>()
    val reviewMovie = MutableLiveData<ResponseReview>()
    val favoriteMovie = MutableLiveData<List<Result>>()
    val showProgressbar = MutableLiveData<Boolean>()
    val messageData = MutableLiveData<String>()
    var favMovie = mutableListOf<List<Result>>()

    fun getDetailMovie(movieId: Int) {
        showProgressbar.value = true
        getMovieUseCase.invokeDetailMovie(
            movieId, viewModelScope, null,
            object : UseCaseDetailMovie<Result> {
                override fun onSuccessDetail(result: Result) {
                    movieDetail.value = result
                    showProgressbar.value = false
                }

                override fun onError(apiError: ApiError?) {
                    messageData.value = apiError?.getErrorMessage()
                    showProgressbar.value = false
                }
            },
        )
    }

    fun getDetailReview(movieId: Int) {
        showProgressbar.value = true
        getMovieUseCase.invokeReviewMovie(
            viewModelScope, movieId,
            object : UseCaseReviewMovie<ResponseReview> {
                override fun onSuccessReview(result: ResponseReview) {
                    reviewMovie.value = result
                    showProgressbar.value = false
                }

                override fun onError(apiError: ApiError?) {
                    messageData.value = apiError?.getErrorMessage()
                    showProgressbar.value = false
                }

            }
        )
    }

    fun insertFav(results: Result?) {
        getMovieUseCase.invokeInsertFavoriteMovie(
            favoriteMovieDao, viewModelScope, results,
            object : UseCaseFavoriteMovie<Result> {
                override fun onSuccess(result: List<Result>) {
                    favoriteMovie.value = result
                    showProgressbar.value = false
                }

                override fun onError(apiError: ApiError?) {
                    messageData.value = apiError?.getErrorMessage()
                    showProgressbar.value = false
                }

            }
        )
    }

    fun getFav() {
        getMovieUseCase.invokeListFavoriteMovie(
            favoriteMovieDao, viewModelScope,
            object : UseCaseFavoriteMovie<Result> {
                override fun onSuccess(result: List<Result>) {
                    favoriteMovie.value = result
                    showProgressbar.value = false
                }

                override fun onError(apiError: ApiError?) {
                    messageData.value = apiError?.getErrorMessage()
                    showProgressbar.value = false
                }

            }
        )
    }

    fun removeFav(id: Int) {
        getMovieUseCase.invokeRemoveFavoriteMovie(
            favoriteMovieDao, viewModelScope, id,
            object : UseCaseFavoriteMovie<Result> {
                override fun onSuccess(result: List<Result>) {
                    favoriteMovie.value = result
                    showProgressbar.value = false
                }

                override fun onError(apiError: ApiError?) {
                    messageData.value = apiError?.getErrorMessage()
                    showProgressbar.value = false
                }

            }
        )
    }

    fun showDialog(title: String, activity: Activity, detailMovieFragment: DetailMovieFragment?, favoriteMovieFragment: FavoriteMovieFragment?, id: Int?) {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_layout)
        val body = dialog.findViewById(R.id.text_title_dialog) as TextView
        body.text = title
        val yesBtn = dialog.findViewById(R.id.button_yes) as Button
        val noBtn = dialog.findViewById(R.id.button_no) as Button
        yesBtn.setOnClickListener {
            if (detailMovieFragment != null) {
                dialog.dismiss()
                detailMovieFragment.fragmentManager?.beginTransaction()?.replace(R.id.frame_layout_main, FavoriteMovieFragment())?.addToBackStack(null)?.commit()
            }

            if (favoriteMovieFragment != null) {
                removeFav(id!!)
                dialog.dismiss()
            }
        }
        noBtn.setOnClickListener { dialog.dismiss() }
        dialog.show()

    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    companion object {
        private val TAG = DetailMovieViewModel::class.java.name
    }
}