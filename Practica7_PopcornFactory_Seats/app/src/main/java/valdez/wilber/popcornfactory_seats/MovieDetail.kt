package valdez.wilber.popcornfactory_seats


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MovieDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var titulo: String = getIntent().getStringExtra("titulo") as String
        var image: Int = getIntent().getIntExtra("imagen", 0) as Int
        var sinopsis: String = getIntent().getStringExtra("sinopsis") as String
        var asientoLeft: Int = getIntent().getIntExtra("numberSeats", 0)
        var id: Int = getIntent().getIntExtra("pos",-1)
        var boton: Button = findViewById(R.id.buy_tickets)


        var imagen : ImageView = findViewById(R.id.image_movie_only)
        var title : TextView = findViewById(R.id.movie_tittle_only)
        var sinops: TextView = findViewById(R.id.movie_description_only)
        var seats: TextView = findViewById(R.id.seatLeft)

        imagen.setImageResource(image.toInt())
        title.setText(titulo)
        sinops.setText(sinopsis)


        if(CatalogActivity.DataProvider.peliculas[id].seats.size != 0){
            asientoLeft = 20 - CatalogActivity.DataProvider.peliculas[id].seats.size
        }

        seats.setText(asientoLeft.toString() + " seat available")

        if(asientoLeft == 0){
            boton.isActivated = false
        }
        else{
            boton.isActivated = true
            boton.setOnClickListener{
                val intent: Intent = Intent(this,SeatSelection::class.java)

                intent.putExtra("movie", id)
                intent.putExtra("name", titulo)

                this.startActivity(intent)

            }
        }

    }

}