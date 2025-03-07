package org.unizd.rma.markanjevic.interfaces

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path
import org.unizd.rma.markanjevic.objects.Movie
import org.unizd.rma.markanjevic.objects.SearchResponse

interface ApiService {

    @GET("/")
    fun searchMovieByTitle(
        @Query("s") title: String,
        @Query("apikey") apiKey: String,
        @Query("page") page: Int
    ): Call<SearchResponse>

    @GET("/")
    fun getMovieById(
        @Query("i") id: String,
        @Query("apikey") apiKey: String,
        @Query("plot") plot: String
    ): Call<Movie>
}
