package com.example.tmbdclient.domain.usecases

import com.example.tmbdclient.data.model.movie.Movie
import com.example.tmbdclient.domain.repositories.MovieRepository

class ViewMoviesUseCase(private val movieRepository: MovieRepository) {

    suspend fun execute(): List<Movie>? = movieRepository.getMovies()

}