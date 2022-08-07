package com.example.movie.sources

import com.example.movie.services.DetailMovieServiceInstance
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDetailDataSource @Inject constructor(
    private val service: DetailMovieServiceInstance
) {

    suspend fun getDetailMovie(movie_id: Int?) = service.getDetailMovie(movie_id)

    suspend fun getTrailerMovie(movie_id: Int?) = service.getTrailerMovie(movie_id)

    suspend fun getCredits(movie_id: Int?) = service.getCredits(movie_id)

}