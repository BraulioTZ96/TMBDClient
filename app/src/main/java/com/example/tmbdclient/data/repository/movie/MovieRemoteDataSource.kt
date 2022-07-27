package com.example.tmbdclient.data.repository.movie

import com.example.tmbdclient.data.model.movie.MovieList
import retrofit2.Response

interface MovieRemoteDataSource {

    suspend fun getMovies(): Response<MovieList>

}