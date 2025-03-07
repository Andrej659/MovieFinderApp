package org.unizd.rma.markanjevic.objects

import com.google.gson.annotations.SerializedName

data class SearchMovie(

    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("imdbID") val id: String,
    @SerializedName("Type") val type: String,
    @SerializedName("Poster") val posterPath: String,
)
