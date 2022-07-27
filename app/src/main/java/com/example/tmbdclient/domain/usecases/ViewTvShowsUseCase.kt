package com.example.tmbdclient.domain.usecases

import com.example.tmbdclient.data.model.tvshow.TvShow
import com.example.tmbdclient.domain.repositories.TvShowRepository

class ViewTvShowsUseCase(private val tvShowRepository: TvShowRepository) {

    suspend fun execute(): List<TvShow>? = tvShowRepository.getTvShows()

}