package com.sherif.themoviedb.utils


import com.sherif.themoviedb.models.RequestTokenResponse
import com.sherif.themoviedb.models.TopRatedMovies
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface APIs {

    @GET("/3/authentication/token/new")
    fun createRequestToken(@Query("api_key") api_key: String): Observable<RequestTokenResponse>

    @GET("/3/movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("region") region: String
    ): Observable<TopRatedMovies>

}