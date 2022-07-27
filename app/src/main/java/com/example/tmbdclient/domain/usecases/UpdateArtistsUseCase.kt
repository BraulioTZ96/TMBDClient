package com.example.tmbdclient.domain.usecases

import com.example.tmbdclient.data.model.artist.Artist
import com.example.tmbdclient.domain.repositories.ArtistRepository

class UpdateArtistsUseCase(private val artistRepository: ArtistRepository) {

    suspend fun execute(): List<Artist>? = artistRepository.updateArtists()

}