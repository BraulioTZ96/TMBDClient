package com.example.tmbdclient.data.model.tvshow


import com.google.gson.annotations.SerializedName

data class TvShowList(
    @SerializedName("tvShows")
    val tvShows: List<TvShow>
)