package com.abrar.kitabisanonton.presentation.detailmovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.abrar.kitabisanonton.R
import com.abrar.kitabisanonton.domain.model.Result
import com.abrar.kitabisanonton.external.isNetworkAvailable
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail_movie.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.DateFormat


private const val RESULT = "result"

class DetailMovieFragment : Fragment() {

    private val detailMovieViewModel: DetailMovieViewModel by viewModel()
    lateinit var adapter: ReviewAdapter
    var results = mutableListOf<Result>()
    private var result: Result? = null
    var movies =  mutableListOf<Result>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (requireContext().isNetworkAvailable()) {
            if (arguments?.getParcelable<Result>(RESULT) != null) {
                result = arguments?.getParcelable<Result>(RESULT)
                detailMovieViewModel.getDetailMovie(result?.id?.toInt()!!)
                detailMovieViewModel.getDetailReview(result!!.id.toInt())
                results.add(result!!)
                (activity as AppCompatActivity?)?.supportActionBar?.title = result?.title
            }
        } else {
            Toast.makeText(requireContext(), "Periksa Kembali Koneksi", Toast.LENGTH_SHORT).show()
        }

        with(detailMovieViewModel) {
            movieDetail.observe(viewLifecycleOwner, Observer {
                text_title_movie.text = it.titleDetail
                text_desc_movie.text = it.overview
                text_release_date.text = DateFormat.getDateInstance(DateFormat.FULL).format(
                    it.releaseDate
                ).toString()
                Glide.with(view)
                    .load("https://image.tmdb.org/t/p/original/" + it.posterPath)
                    .into(image_poster_movie)
            })

            reviewMovie.observe(viewLifecycleOwner, Observer {
                adapter = ReviewAdapter(it)
                loadRecyclerView()
            })

            favoriteMovie.observe(viewLifecycleOwner, Observer {
                it.forEach {
                    movies.add(it)
                }
            })
        }

        view.setOnClickListener {
            detailMovieViewModel.insertFav(result)
            detailMovieViewModel.showDialog(
                "Tambahkan Ke Favorite?",
                requireActivity(),
                this,
                null,
                result?.id?.toInt()
            )
        }

    }

    fun loadRecyclerView() {
        recycler_review_movie.adapter = adapter
        recycler_review_movie.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }


    companion object {
        fun newInstance(result: Result?): DetailMovieFragment {
            return DetailMovieFragment().apply {
                arguments = Bundle().apply {
                    if (result != null) {
                        putParcelable(RESULT, result)
                    }
                }
            }
        }
    }

}