package org.unizd.rma.markanjevic.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import org.unizd.rma.markanjevic.R
import org.unizd.rma.markanjevic.handlerClass.ApiController


/**
 * [DetailActivity] prikazuje detalje o filmu odabranom u aplikaciji.
 * Aktivnost preuzima podatke o filmu putem API poziva koristeći ID filma
 * i prikazuje ih na korisničkom sučelju.
 */
class DetailActivity : AppCompatActivity()  {

    private lateinit var movieId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_view)

        movieId = intent.getStringExtra("MOVIE_ID") ?: ""

        // Poziva ApiController za dohvat podataka o filmu prema ID-u
        ApiController.getMovieById(movieId,
            onResult = { movie ->
                if (movie != null) {
                    findViewById<TextView>(R.id.tvTitle).text = movie.title
                    findViewById<TextView>(R.id.tvReleaseYear).text = movie.year.toString()
                    findViewById<TextView>(R.id.tvPlot).text = movie.plot
                    findViewById<TextView>(R.id.tvDirector).text = movie.director
                    findViewById<TextView>(R.id.tvRuntime).text = movie.runtime
                    findViewById<TextView>(R.id.tvImdbRating).text = movie.rating
                    findViewById<TextView>(R.id.tvGenre).text = movie.genreName

                    // Učitava poster filma koristeći Glide biblioteku
                    Glide.with(this).load(movie.posterPath).into(findViewById<ImageView>(R.id.ivPoster))
                }
            },
            onError = { errorMessage ->

                // Ako se dogodi greška pri dohvaćanju podataka, prikazuje Toast poruku s greškom
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        )
    }
}