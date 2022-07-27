package com.example.tmbdclient.data.repository.movie

import com.example.tmbdclient.data.api.TMBDService
import com.example.tmbdclient.data.model.movie.MovieList
import retrofit2.Response

class MovieRemoteDataSourceImpl(private val tmbdService: TMBDService): MovieRemoteDataSource {

    override suspend fun getMovies(): Response<MovieList> = tmbdService.getPopularMovies()

}