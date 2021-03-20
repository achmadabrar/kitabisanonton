package com.abrar.kitabisanonton.presentation.nowplaying

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.abrar.kitabisanonton.R
import com.abrar.kitabisanonton.domain.model.Result
import com.abrar.kitabisanonton.external.isNetworkAvailable
import com.abrar.kitabisanonton.presentation.ListMovieViewHolder
import com.abrar.kitabisanonton.presentation.MoviePopularAdapter
import com.abrar.kitabisanonton.presentation.detailmovie.DetailMovieFragment
import com.abrar.kitabisanonton.presentation.toprated.TopRatedViewModel
import kotlinx.android.synthetic.main.fragment_now_playing.*
import kotlinx.android.synthetic.main.fragment_top_rated.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class NowPlayingFragment : Fragment(), ListMovieViewHolder.Listener {

    lateinit var adapter: MoviePopularAdapter
    private val nowPlayingViewModel: NowPlayingViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_now_playing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)?.supportActionBar?.title = resources.getString(R.string.app_name)
        if (requireContext().isNetworkAvailable()) {
            nowPlayingViewModel.getNowPlayingMovie()
        } else {
            Toast.makeText(requireContext(), "Periksa Kembali Koneksi", Toast.LENGTH_SHORT).show()
        }

        with(nowPlayingViewModel) {
            movieNowPlaying.observe(viewLifecycleOwner, Observer {
                adapter = MoviePopularAdapter(it.results, this@NowPlayingFragment)
                loadRecyclerView()
            })
        }
    }

    override fun onClickMovie(result: Result?) {
        fragmentManager!!.beginTransaction().replace(R.id.frame_layout_main, DetailMovieFragment.newInstance(result)).addToBackStack("nowPlaying").commit()
    }

    fun loadRecyclerView() {
        recycler_now_playing.adapter = adapter
        recycler_now_playing.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

}