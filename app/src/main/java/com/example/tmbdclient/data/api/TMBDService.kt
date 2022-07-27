package com.example.tmbdclient.data.api

import com.example.tmbdclient.const.Constants
import com.example.tmbdclient.data.model.artist.ArtistList
import com.example.tmbdclient.data.model.movie.MovieList
import com.example.tmbdclient.data.model.tvshow.TvShowList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMBDService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): Response<MovieList>

    @GET("tv/popular")
    suspend fun getPopularTvShows(
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): Response<TvShowList>

    @GET("person/popular")
    suspend fun getPopularArtists(
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): Response<ArtistList>

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }

}