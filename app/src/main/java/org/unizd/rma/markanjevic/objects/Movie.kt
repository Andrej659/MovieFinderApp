package org.unizd.rma.markanjevic.objects

import com.google.gson.annotations.SerializedName

data class Movie(

    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("Director") val director: String,
    @SerializedName("Genre") val genreName: String,
    @SerializedName("Plot") val plot: String,
    @SerializedName("Poster") val posterPath: String,
    @SerializedName("imdbRating") val rating: String,
    @SerializedName("imdbID") val id: String,
    @SerializedName("Runtime") val runtime: String
)


