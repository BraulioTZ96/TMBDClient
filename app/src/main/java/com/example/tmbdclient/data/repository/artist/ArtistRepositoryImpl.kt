package com.example.tmbdclient.data.repository.artist

import android.util.Log
import com.example.tmbdclient.data.model.artist.Artist
import com.example.tmbdclient.domain.repositories.ArtistRepository

class ArtistRepositoryImpl(
    private val cacheDataSourceImpl: ArtistCacheDataSourceImpl,
    private val localDataSourceImpl: ArtistLocalDataSourceImpl,
    private val remoteDataSourceImpl: ArtistRemoteDataSourceImpl
): ArtistRepository {

    override suspend fun getArtists(): List<Artist>? {
        return getArtistFromCache()
    }

    override suspend fun updateArtists(): List<Artist>? {
        val newListOfMovies = getArtistFromAPI()
        localDataSourceImpl.clearAllArtists()
        localDataSourceImpl.saveArtistsToDB(newListOfMovies)
        cacheDataSourceImpl.saveArtistsToCache(newListOfMovies)
        return newListOfMovies
    }

    private suspend fun getArtistFromAPI(): List<Artist>{
        lateinit var artistList: List<Artist>
        try {
            val response = remoteDataSourceImpl.getArtists()
            val body = response.body()
            if (body != null){
                artistList = body.artists
            }
        }catch (exception: Exception){
            Log.i("MyTagException", exception.message.toString())
        }
        return artistList
    }

    private suspend fun getArtistFromDB(): List<Artist>{
        lateinit var artistList: List<Artist>
        try {
            artistList = localDataSourceImpl.getArtistsFromDB()
        }catch (exception: Exception){
            Log.i("MyTagException", exception.message.toString())
        }
        return if(artistList.isNotEmpty()){
            artistList
        }else{
            artistList = getArtistFromAPI()
            localDataSourceImpl.saveArtistsToDB(artistList)
            artistList
        }
    }

    private suspend fun getArtistFromCache():List<Artist>{
        lateinit var artistList: List<Artist>
        try {
            artistList = cacheDataSourceImpl.getArtistsFromCache()
        }catch (exception: Exception){
            Log.i("MyTagException", exception.message.toString())
        }
        return if(artistList.isNotEmpty()){
            artistList
        }else{
            artistList = getArtistFromDB()
            cacheDataSourceImpl.saveArtistsToCache(artistList)
            artistList
        }
    }

}