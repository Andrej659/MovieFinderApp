package org.unizd.rma.markanjevic.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.unizd.rma.markanjevic.R


/**
 * MainActivity predstavlja početnu aktivnost aplikacije.
 * Ova aktivnost omogućuje korisniku unos pretrage putem `SearchView` komponente,
 * a također omogućuje navigaciju do aktivnosti koja prikazuje rezultate pretrage.
 */
class MainActivity : AppCompatActivity() {

    /**
     * Poziva se pri kreiranju aktivnosti.
     * Inicijalizira korisničko sučelje, postavlja ponašanje za `SearchView` i
     * dodaje funkcionalnost za navigaciju na sljedeću aktivnost prilikom pretrage.
     *
     * @param savedInstanceState Spremi stanje aktivnosti, ako postoji (npr. pri ponovnom pokretanju).
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnSearch = findViewById<Button>(R.id.searchBtn)
        val searchView = findViewById<SearchView>(R.id.searchView)

        // Postavlja onClickListener za 'Search' gumb.
        // Kada korisnik klikne na gumb, provodi pretragu koristeći tekst iz 'SearchView'.
        btnSearch.setOnClickListener {
            val queryText = searchView.query.toString()

            if (queryText.isNotEmpty()) {
                val intent = Intent(this, SearchResActivity::class.java).apply {
                    putExtra("QUERY", queryText)
                }
                startActivity(intent)
            }
        }

        // Postavlja listener za 'SearchView' koji reagira na promjene u unosu teksta.
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            /**
             * Poziva se kada korisnik pritisne Enter nakon unosa teksta u 'SearchView'.
             * Ako uneseni tekst nije prazan, pokreće novu aktivnost za prikaz rezultata.
             *
             * @param query Uneseni tekst za pretragu.
             * @return Vraća true kako bi označio da je unos prihvaćen.
             */
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {

                    val intent = Intent(this@MainActivity, SearchResActivity::class.java)
                    intent.putExtra("QUERY", query)
                    startActivity(intent)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}