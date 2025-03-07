package org.unizd.rma.markanjevic.handlerClass

import org.unizd.rma.markanjevic.BuildConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import org.unizd.rma.markanjevic.objects.RetrofitClient
import org.unizd.rma.markanjevic.objects.Movie
import org.unizd.rma.markanjevic.objects.SearchMovie
import org.unizd.rma.markanjevic.objects.SearchResponse


/**
 * ApiController pruža statičke metode za komunikaciju s API-jem koristeći Retrofit.
 * Omogućuje pretragu filmova prema nazivu i dohvat pojedinačnih filmova prema ID-u.
 * Svaka metoda koristi Retrofit za slanje zahtjeva i pozivanje odgovarajućih callback funkcija
 * u slučaju uspjeha ili greške.
 */
class ApiController {

    companion object {

        val apiKey = BuildConfig.API_KEY

        /**
         * Pretražuje filmove prema nazivu.
         *
         * @param query Upit za pretragu filmova.
         * @param page Stranica s rezultatima pretrage.
         * @param onResult Funkcija koja se poziva u slučaju uspješnog odgovora. Prima listu filmova.
         * @param onError Funkcija koja se poziva u slučaju greške. Prima poruku greške.
         */
        fun searchMovieByTitle(query: String, page: Int, onResult: (List<SearchMovie>?) -> Unit, onError: (String) -> Unit) {

            val call = RetrofitClient.apiService.searchMovieByTitle(query, apiKey, page)

            call.enqueue(object : Callback<SearchResponse> {
                override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                    if (response.isSuccessful) {
                        onResult(response.body()?.search)
                    } else {
                        onError("Error: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    onError(t.message ?: "Unknown error")
                }
            })
        }


        /**
         * Dohvaća detalje filma prema ID-u.
         *
         * @param movieId ID filma za dohvat detalja.
         * @param onResult Funkcija koja se poziva u slučaju uspješnog odgovora. Prima film.
         * @param onError Funkcija koja se poziva u slučaju greške. Prima poruku greške.
         */
        fun getMovieById(movieId: String, onResult: (Movie?) -> Unit, onError: (String) -> Unit) {

            RetrofitClient.apiService.getMovieById(movieId, apiKey, "short").enqueue(object : Callback<Movie> {

                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    if (response.isSuccessful) {
                        onResult(response.body())
                    } else {
                        onError("Error: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    onError(t.message ?: "Unknown error")
                }
            })
        }
    }
}


