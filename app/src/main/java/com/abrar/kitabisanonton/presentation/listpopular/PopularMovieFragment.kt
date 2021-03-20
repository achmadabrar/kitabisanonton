package com.abrar.kitabisanonton.presentation.listpopular

import android.os.Bundle
import android.view.*
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
import kotlinx.android.synthetic.main.activity_movie.*
import kotlinx.android.synthetic.main.fragment_popular_movie.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Abrar
 */
class PopularMovieFragment : Fragment(), ListMovieViewHolder.Listener {

    lateinit var adapter: MoviePopularAdapter
    private val popularMovieViewModel: PopularMovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)?.supportActionBar?.title = resources.getString(R.string.app_name)
        if (requireContext().isNetworkAvailable()) {
            popularMovieViewModel.getMoviePopular()
        } else {
            Toast.makeText(requireContext(), "Periksa Kembali Koneksi", Toast.LENGTH_SHORT).show()
        }

        with(popularMovieViewModel) {
            moviePopular.observe(viewLifecycleOwner, Observer {
                adapter = MoviePopularAdapter(it.results, this@PopularMovieFragment)
                loadRecyclerView()
            })
        }
    }

    fun loadRecyclerView() {
        recycler_popular_movie.adapter = adapter
        recycler_popular_movie.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun onClickMovie(result: Result?) {
        fragmentManager!!.beginTransaction().replace(R.id.frame_layout_main, DetailMovieFragment.newInstance(result)).addToBackStack("popular").commit()
    }


}