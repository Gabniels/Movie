package com.example.movie.sources

import com.example.movie.services.TvServiceInstance
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvDataSource @Inject constructor(
    private val service: TvServiceInstance
) {

    suspend fun getAiringToday(page: Int) = service.getAiringTodayTv(page)

    suspend fun getTopRated(page: Int) = service.getTopRatedTv(page)

    suspend fun getOnTheAir(page: Int) = service.getOntheAirTv(page)

    suspend fun getPopular(page: Int) = service.getPopularTv(page)

}