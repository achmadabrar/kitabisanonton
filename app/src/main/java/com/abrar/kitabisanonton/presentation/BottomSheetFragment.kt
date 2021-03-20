package com.abrar.kitabisanonton.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abrar.kitabisanonton.R
import com.abrar.kitabisanonton.presentation.listpopular.PopularMovieFragment
import com.abrar.kitabisanonton.presentation.nowplaying.NowPlayingFragment
import com.abrar.kitabisanonton.presentation.toprated.TopRatedFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_bottom_sheet.*


class BottomSheetFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        radio_button_popular.setOnClickListener {
            val transaction = fragmentManager?.beginTransaction()
            transaction?.addToBackStack("popular")
            transaction?.replace(R.id.frame_layout_main, PopularMovieFragment(), "popular")
            transaction?.commit()
            dismiss()
        }

        radio_button_top_rated.setOnClickListener {
            val transaction = fragmentManager?.beginTransaction()
            transaction?.addToBackStack("popular")
            transaction?.replace(R.id.frame_layout_main, TopRatedFragment(), "topRated")
            transaction?.commit()
            dismiss()
        }

        radio_button_now_playing.setOnClickListener {
            val transaction = fragmentManager?.beginTransaction()
            transaction?.addToBackStack("popular")
            transaction?.replace(R.id.frame_layout_main, NowPlayingFragment(), "nowPlaying")
            transaction?.commit()
            dismiss()
        }
    }
}