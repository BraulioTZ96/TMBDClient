package com.example.tmbdclient.data.repository.movie

import com.example.tmbdclient.data.model.movie.Movie
import com.example.tmbdclient.data.repository.movie.MovieCacheDataSource

class MovieCacheDataSourceImpl: MovieCacheDataSource {

    private var movieList = ArrayList<Movie>()

    override suspend fun getMoviesFromCache(): List<Movie> {
        return movieList
    }

    override suspend fun saveMoviesToCache(movies: List<Movie>) {
        movieList.clear()
        movieList.addAll(movies)
    }

}