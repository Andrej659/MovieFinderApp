package org.unizd.rma.markanjevic.activities

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.unizd.rma.markanjevic.R
import org.unizd.rma.markanjevic.adapters.MovieAdapter
import org.unizd.rma.markanjevic.handlerClass.ApiController
import org.unizd.rma.markanjevic.objects.SearchMovie


/**
 * [SearchResActivity] prikazuje rezultate pretrage filmova na temelju unosa korisnika.
 * Aktivnost prikazuje popis filmova koji odgovaraju pretrazi, a omogućuje i navigaciju kroz stranice s rezultatima.
 */
class SearchResActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var movies: MutableList<SearchMovie>
    private lateinit var tvSearchTitle: TextView
    private lateinit var btnNext: Button
    private lateinit var btnPrevious: Button
    private var page: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)


        recyclerView = findViewById(R.id.rvMovies)
        tvSearchTitle = findViewById(R.id.tvSearchTitle)
        btnNext = findViewById(R.id.btnNextPage)
        btnPrevious = findViewById(R.id.btnPreviousPage)

        recyclerView.layoutManager = LinearLayoutManager(this)

        // Dohvaća query (pojam za pretragu) koji je poslan putem Intenta
        val query = intent.getStringExtra("QUERY") ?: ""

        tvSearchTitle.text = "Results of search for: \"$query\""

        setUpTheList(query, page)

        btnNext.setOnClickListener {
            page += 1
            setUpTheList(query, page)
            // Prikazuje ili skriva gumb "Previous" (prethodna stranica) na temelju trenutne stranice
            btnPrevious.visibility = if (page > 1) View.VISIBLE else View.GONE
        }

        btnPrevious.setOnClickListener {
            page -= 1
            setUpTheList(query, page)
            // Prikazuje ili skriva gumb "Previous" na temelju trenutne stranice
            btnPrevious.visibility = if (page > 1) View.VISIBLE else View.GONE
        }
    }

    /**
     * Postavlja popis filmova u RecyclerView na temelju rezultata pretrage.
     * Poziva API za dohvat filmova prema pretraživačkom upitu i trenutnoj stranici.
     *
     * @param query Pojam za pretragu filmova.
     * @param page Trenutna stranica s rezultatima.
     */
    private fun setUpTheList(query: String, page: Int){

        ApiController.searchMovieByTitle(query, page,
            onResult = { moviesList ->
                moviesList?.let {
                    movies = it.toMutableList()
                    movieAdapter = MovieAdapter(movies, this)
                    recyclerView.adapter = movieAdapter
                }
            },
            onError = { errorMessage ->
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        )
    }
}
