package com.example.tmbdclient.data.repository.artist

import com.example.tmbdclient.data.api.TMBDService
import com.example.tmbdclient.data.model.artist.ArtistList
import retrofit2.Response

class ArtistRemoteDataSourceImpl(private val tmbdService: TMBDService): ArtistRemoteDataSource {

    override suspend fun getArtists(): Response<ArtistList>  = tmbdService.getPopularArtists()

}