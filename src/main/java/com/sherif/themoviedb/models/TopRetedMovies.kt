package com.sherif.themoviedb.models

data class TopRatedMovies(

    val page: Int,
    val results: List<Results>,
    val total_results: Int,
    val total_pages: Int,
    val status_message: String,
    val status_code: Int

)