package com.abrar.kitabisanonton.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.abrar.kitabisanonton.R
import com.abrar.kitabisanonton.presentation.favorite.FavoriteMovieFragment
import com.abrar.kitabisanonton.presentation.listpopular.PopularMovieFragment
import kotlinx.android.synthetic.main.activity_movie.*


class MovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        setSupportActionBar(toolbar_main)
        supportActionBar?.title = resources.getString(R.string.app_name)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.addToBackStack("popular")
        transaction.replace(R.id.frame_layout_main, PopularMovieFragment(), "popular")
        transaction.commit()

        button_category.setOnClickListener {
            BottomSheetFragment().apply {
                show(supportFragmentManager, BottomSheetFragment::class.java.simpleName)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.fav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_fav -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.frame_layout_main,
                    FavoriteMovieFragment()
                ).addToBackStack(null).commit()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}