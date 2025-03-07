package org.unizd.rma.markanjevic.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.unizd.rma.markanjevic.R
import org.unizd.rma.markanjevic.activities.DetailActivity
import org.unizd.rma.markanjevic.objects.SearchMovie


/**
 * [MovieAdapter] je adapter za RecyclerView koji prikazuje popis filmova.
 * Adapter prikazuje osnovne informacije o svakom filmu, uključujući poster, naziv, godinu i tip.
 * Također omogućava otvaranje detalja filma klikom na stavku popisa.
 *
 * @param movies Lista filmova koji će biti prikazani u RecyclerView.
 * @param context Kontekst aplikacije koji se koristi za inflaciju view-a i pokretanje aktivnosti.
 */
class MovieAdapter(private val movies: List<SearchMovie>, private val context: Context) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    /**
     * Kreira novi ViewHolder za stavku popisa.
     * Ovdje se povećava layout za stavku popisa filma.
     *
     * @param parent Roditeljski view u koji će biti ubačen novi view.
     * @param viewType Tip pogleda koji treba biti infliran.
     * @return Novo kreirani [MovieViewHolder] koji sadrži referencu na layout stavke.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    /**
     * Povezuje podatke filma s pogledom stavke.
     * Ovdje se podaci o filmu (kao što su poster, naziv, godina i tip) postavljaju u odgovarajuće UI elemente.
     *
     * @param holder ViewHolder koji sadrži reference na UI elemente stavke.
     * @param position Pozicija filma u listi.
     */
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        Glide.with(context).load(movie.posterPath).into(holder.posterImageView)
        holder.titleTextView.text = movie.title
        holder.yearTextView.text = movie.year.toString()
        holder.typeTextView.text = movie.type.toString()

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("MOVIE_ID", movie.id)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    /**
     * ViewHolder za pojedinačnu stavku filma u RecyclerView-u.
     * Drži reference na UI elemente koji prikazuju informacije o filmu.
     */
    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val posterImageView: ImageView = itemView.findViewById(R.id.moviePoster)
        val titleTextView: TextView = itemView.findViewById(R.id.movieTitle)
        val yearTextView: TextView = itemView.findViewById(R.id.movieYear)
        val typeTextView: TextView = itemView.findViewById(R.id.mediaType)
    }
}


