package com.example.movie.remote.service

import com.example.movie.remote.response.ResponseMovie
import com.example.movie.utils.Constants.URL_NOWPLAYING_MOVIE
import com.example.movie.utils.Constants.URL_POPULAR_MOVIE
import com.example.movie.utils.Constants.URL_TOPRATED_MOVIE
import com.example.movie.utils.Constants.URL_UPCOMING_MOVIE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieServiceInstance {

    @GET(URL_NOWPLAYING_MOVIE)
    suspend fun getNowPlayingMovie(
        @Query("page") page: Int
    ): Response<ResponseMovie>

    @GET(URL_TOPRATED_MOVIE)
    suspend fun getTopRatedMovie(
        @Query("page") page: Int
    ): Response<ResponseMovie>

    @GET(URL_UPCOMING_MOVIE)
    suspend fun getUpComingMovie(
        @Query("page") page: Int
    ): Response<ResponseMovie>

    @GET(URL_POPULAR_MOVIE)
    suspend fun getPopularMovie(
        @Query("page") page: Int
    ): Response<ResponseMovie>

//    @GET(URL_TRAILER_MOVIE)
//    suspend fun getSegeraTrailer(
//        @Path("movie_id") id: Int?
//    ): Response<MovieTrailer>

}