package com.abrar.kitabisanonton.presentation.toprated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.abrar.kitabisanonton.R
import com.abrar.kitabisanonton.external.isNetworkAvailable
import com.abrar.kitabisanonton.presentation.MoviePopularAdapter
import kotlinx.android.synthetic.main.fragment_popular_movie.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer
import com.abrar.kitabisanonton.domain.model.Result
import com.abrar.kitabisanonton.presentation.ListMovieViewHolder
import com.abrar.kitabisanonton.presentation.detailmovie.DetailMovieFragment
import kotlinx.android.synthetic.main.fragment_top_rated.*


class TopRatedFragment : Fragment(), ListMovieViewHolder.Listener {

    lateinit var adapter: MoviePopularAdapter
    private val topRatedViewModel: TopRatedViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_rated, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)?.supportActionBar?.title = resources.getString(R.string.app_name)
        if (requireContext().isNetworkAvailable()) {
            topRatedViewModel.getTopRatedMovie()
        } else {
            Toast.makeText(requireContext(), "Periksa Kembali Koneksi", Toast.LENGTH_SHORT).show()
        }

        with(topRatedViewModel) {
            movieTopRated.observe(viewLifecycleOwner, Observer {
                adapter = MoviePopularAdapter(it.results, this@TopRatedFragment)
                loadRecyclerView()
            })
        }
    }

    fun loadRecyclerView() {
        recycler_top_rated.adapter = adapter
        recycler_top_rated.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun onClickMovie(result: Result?) {
        fragmentManager!!.beginTransaction().replace(R.id.frame_layout_main, DetailMovieFragment.newInstance(result)).addToBackStack("topRated").commit()
    }

}