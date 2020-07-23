package com.sherif.themoviedb.models

import io.reactivex.SingleOnSubscribe

data class TopRatedMovies(

        val page: Int,
        val results: List<Results>,
        val total_results: Int,
        val total_pages: Int,
        val status_message: String,
        val status_code: Int

) {
    constructor() : this(0,
            listOf(),
            0,
            0,
            "",
            0) {

    }
}