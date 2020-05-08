package com.sherif.themoviedb.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sherif.themoviedb.models.RequestTokenResponse
import com.sherif.themoviedb.models.TopRatedMovies
import com.sherif.themoviedb.utils.APIs
import com.sherif.themoviedb.utils.ClientProvider
import com.sherif.themoviedb.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomeActivityViewModel : ViewModel() {

    private val callClient: APIs = ClientProvider.getClient(Constants.BASE_URL)
    val topRatedMoviesList: MutableLiveData<TopRatedMovies> = MutableLiveData<TopRatedMovies>()
    lateinit var topRatedListObserver: io.reactivex.Observable<TopRatedMovies>


    fun callTokenRequestApi() {

        val tokenResCall = callClient.createRequestToken(Constants.APIKEY)

        tokenResCall.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        object : io.reactivex.Observer<RequestTokenResponse> {
                            override fun onComplete() {


                            }

                            override fun onSubscribe(d: Disposable) {


                            }

                            override fun onNext(t: RequestTokenResponse) {


                                Log.d("MainActivity", " Status_Code :: ${t.request_token}")


                            }

                            override fun onError(e: Throwable) {
                                e.printStackTrace()
                            }
                        })


    }

    fun callTopRatedMoviesApi() {

        Log.d("ViewModel", "callTopRatedMoviesApi called")
        val topRatedMoviesCall =
                callClient.getTopRatedMovies(Constants.APIKEY, Constants.LANG, 1, "")

        this.topRatedListObserver = topRatedMoviesCall

        val s = topRatedMoviesCall.subscribeOn(Schedulers.newThread())

                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        object : io.reactivex.Observer<TopRatedMovies> {
                            override fun onComplete() {
                                Log.d("ViewModel", "onComplete called")
                            }

                            override fun onNext(response: TopRatedMovies) {


                                Log.d("ViewModel", "response = ${response.results.get(0).original_title}")
                                topRatedMoviesList.value = response


                            }

                            override fun onError(e: Throwable) {
                                e.stackTrace
                            }

                            override fun onSubscribe(d: Disposable) {

                            }
                        })


    }


}