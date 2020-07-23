package com.sherif.themoviedb.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sherif.themoviedb.R
import com.sherif.themoviedb.adapters.RecycleListAdapter
import com.sherif.themoviedb.models.TopRatedMovies
import com.sherif.themoviedb.viewModels.HomeActivityViewModel
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NullPointerException

class HomeActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    lateinit var topRatedMovies: TopRatedMovies
    val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val viewModel: HomeActivityViewModel =
                ViewModelProvider(this).get(HomeActivityViewModel::class.java)

        viewModel.callTokenRequestApi()

        /* viewModel.topRatedMoviesList.observe(
                 this,
                 androidx.lifecycle.Observer { results -> initRecycleViewList(results) })
 */
        home_title.text = "Looding"
        val disposable = viewModel.callTopRatedMoviesApi()
                .subscribe({ data -> initRecycleViewList(data); home_title.text = "Done" },
                        { error -> error.stackTrace })

        compositeDisposable.add(disposable)




    }

    private fun initRecycleViewList(topRatedMovies: TopRatedMovies) {
        recylerView_list
        val mAdapter = RecycleListAdapter(this, topRatedMovies)
        recylerView_list.adapter = mAdapter
        recylerView_list.layoutManager = LinearLayoutManager(this)
    }

    override fun onStop() {
        super.onStop()

        compositeDisposable.clear()
    }
}


