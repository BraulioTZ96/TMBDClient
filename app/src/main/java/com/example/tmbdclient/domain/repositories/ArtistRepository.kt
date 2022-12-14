package com.example.tmbdclient.domain.repositories

import com.example.tmbdclient.data.model.artist.Artist

interface ArtistRepository {

    suspend fun getArtists(): List<Artist>?
    suspend fun updateArtists(): List<Artist>?

}