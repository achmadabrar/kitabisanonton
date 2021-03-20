package com.abrar.kitabisanonton.presentation.favorite

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
import com.abrar.kitabisanonton.presentation.ListMovieViewHolder
import com.abrar.kitabisanonton.presentation.MoviePopularAdapter
import com.abrar.kitabisanonton.presentation.detailmovie.DetailMovieFragment
import com.abrar.kitabisanonton.presentation.detailmovie.DetailMovieViewModel
import kotlinx.android.synthetic.main.fragment_favorite_movie.*
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val SELECTED_FAV = "favorite"

class FavoriteMovieFragment : Fragment(), ListMovieViewHolder.Listener {

    lateinit var adapter: MoviePopularAdapter
    private val viewModel: DetailMovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)?.supportActionBar?.title = "Favorite Movie"
        viewModel.getFav()

        with(viewModel) {
            favoriteMovie.observe(viewLifecycleOwner, Observer {
                if (!it.isNullOrEmpty()) {
                    adapter = MoviePopularAdapter(it, this@FavoriteMovieFragment)
                    loadRecylerView()
                } else {
                    text_favorite_empty.visibility = View.VISIBLE
                    recycler_favorite_movie.visibility = View.GONE
                }
            })
        }
    }

    fun loadRecylerView() {
        text_favorite_empty.visibility = View.GONE
        recycler_favorite_movie.visibility = View.VISIBLE
        recycler_favorite_movie.adapter = adapter
        recycler_favorite_movie.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun onClickMovie(result: Result?) {
        viewModel.showDialog("Hapus Dari Favorite?", requireActivity(), null, this, result?.id?.toInt())
    }
}