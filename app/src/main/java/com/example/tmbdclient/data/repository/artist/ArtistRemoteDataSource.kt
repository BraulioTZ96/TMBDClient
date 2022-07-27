package com.example.tmbdclient.data.repository.artist

import com.example.tmbdclient.data.model.artist.ArtistList
import retrofit2.Response

interface ArtistRemoteDataSource {

    suspend fun getArtists(): Response<ArtistList>

}