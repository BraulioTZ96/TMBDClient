package com.example.tmbdclient.data.repository.movie

import android.util.Log
import com.example.tmbdclient.data.model.movie.Movie
import com.example.tmbdclient.domain.repositories.MovieRepository

class MovieRepositoryImpl(
    private val movieRemoteDataSourceImpl: MovieRemoteDataSourceImpl,
    private val movieLocalDataSourceImpl: MovieLocalDataSourceImpl,
    private val movieCacheDataSourceImpl: MovieCacheDataSourceImpl
): MovieRepository {

    override suspend fun getMovies(): List<Movie>? {
        return getMoviesFromCache()
    }

    override suspend fun updateMovies(): List<Movie>? {
        val newListOfMovies = getMoviesFromAPI()
        movieLocalDataSourceImpl.clearAllMovies()
        movieLocalDataSourceImpl.saveMoviesToDB(newListOfMovies)
        movieCacheDataSourceImpl.saveMoviesToCache(newListOfMovies)
        return newListOfMovies
    }

    private suspend fun getMoviesFromAPI(): List<Movie>{
        lateinit var movieList: List<Movie>
        try {
            val response = movieRemoteDataSourceImpl.getMovies()
            val body = response.body()
            if (body != null){
                movieList = body.movies
            }
        }catch (exception: Exception){
            Log.i("MyTagException", exception.message.toString())
        }
        return movieList
    }

    private suspend fun getMoviesFromDB(): List<Movie>{
        lateinit var movieList: List<Movie>
        try {
            movieList = movieLocalDataSourceImpl.getMoviesFromDB()
        }catch (exception: Exception){
            Log.i("MyTagException", exception.message.toString())
        }
        return if(movieList.isNotEmpty()){
            movieList
        }else{
            movieList = getMoviesFromAPI()
            movieLocalDataSourceImpl.saveMoviesToDB(movieList)
            movieList
        }
    }

    private suspend fun getMoviesFromCache():List<Movie>{
        lateinit var movieList: List<Movie>
        try {
            movieList = movieCacheDataSourceImpl.getMoviesFromCache()
        }catch (exception: Exception){
            Log.i("MyTagException", exception.message.toString())
        }
        return if(movieList.isNotEmpty()){
            movieList
        }else{
            movieList = getMoviesFromDB()
            movieCacheDataSourceImpl.saveMoviesToCache(movieList)
            movieList
        }
    }

}