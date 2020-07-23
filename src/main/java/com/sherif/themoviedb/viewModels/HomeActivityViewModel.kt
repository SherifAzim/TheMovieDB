package com.sherif.themoviedb.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sherif.themoviedb.models.RequestTokenResponse
import com.sherif.themoviedb.models.TopRatedMovies
import com.sherif.themoviedb.utils.APIs
import com.sherif.themoviedb.utils.ClientProvider
import com.sherif.themoviedb.utils.Constants
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.SingleSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.AsyncSubject

class HomeActivityViewModel : ViewModel() {

    private val callClient: APIs = ClientProvider.getClient(Constants.BASE_URL)
    val topRatedMoviesList: MutableLiveData<TopRatedMovies> = MutableLiveData<TopRatedMovies>()


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


    fun callTopRatedMoviesApi(): AsyncSubject<TopRatedMovies> {

       // Log.d("ViewModel", "callTopRatedMoviesApi called")

        val topRatedListObservable: AsyncSubject<TopRatedMovies> = AsyncSubject.create()


        val disposable = callClient.getTopRatedMovies(Constants.APIKEY, Constants.LANG, 1, "")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    //onNext
                  //  Log.d("ViewModel", "response = ${response.results[0].original_title}")

                    //update UI using LiveData
                    //topRatedMoviesList.value = response

                    //update UI using rxjava single observable
                    topRatedListObservable.onNext(response)
                },
                        { e ->
                            //onError
                            e.stackTrace
                        },
                        {
                            //onComplete
                           // Log.d("ViewModel", "onComplete called");
                            topRatedListObservable.onComplete()
                        })

        return topRatedListObservable

    }


}