package com.example.tmbdclient.data.repository.artist

import com.example.tmbdclient.data.model.artist.Artist

interface ArtistLocalDataSource {

    suspend fun getArtistsFromDB(): List<Artist>
    suspend fun saveArtistsToDB(artists: List<Artist>)
    suspend fun clearAllArtists()

}