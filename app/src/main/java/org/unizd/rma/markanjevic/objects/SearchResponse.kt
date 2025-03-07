package org.unizd.rma.markanjevic.objects

import com.google.gson.annotations.SerializedName

data class SearchResponse(

    @SerializedName("Search") val search: List<SearchMovie>?,
    @SerializedName("totalResults") val totalResults: String,
    @SerializedName("Response") val response: String
)