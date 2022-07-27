package com.example.tmbdclient.domain.repositories

import com.example.tmbdclient.data.model.movie.Movie

interface MovieRepository {

    suspend fun getMovies(): List<Movie>?
    suspend fun updateMovies(): List<Movie>?

}