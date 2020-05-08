package com.sherif.themoviedb.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sherif.themoviedb.R
import com.sherif.themoviedb.adapters.RecycleListAdapter
import com.sherif.themoviedb.models.TopRatedMovies
import com.sherif.themoviedb.viewModels.HomeActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    lateinit var topRatedMovies: TopRatedMovies


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val viewModel: HomeActivityViewModel =
                ViewModelProvider(this).get(HomeActivityViewModel::class.java)

        viewModel.callTokenRequestApi()
        viewModel.callTopRatedMoviesApi()
        //Log.d(TAG, " TopRatedList.Value =  ${viewModel.topRatedMoviesList.value}")

        viewModel.topRatedMoviesList.observe(
                this,
                androidx.lifecycle.Observer { results -> initRecycleViewList(results) })


    }

    private fun initRecycleViewList(topRatedMovies: TopRatedMovies) {
        recylerView_list
        val mAdapter = RecycleListAdapter(this, topRatedMovies)
        recylerView_list.adapter = mAdapter
        recylerView_list.layoutManager = LinearLayoutManager(this)
    }


}
