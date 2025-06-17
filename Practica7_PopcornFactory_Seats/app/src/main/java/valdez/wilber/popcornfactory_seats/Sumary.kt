package valdez.wilber.popcornfactory_seats

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import valdez.wilber.popcornfactory_seats.CatalogActivity.DataProvider

class Sumary : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sumary)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bundle = intent.extras

        var btn : Button = findViewById(R.id.confirm2)
        var peli : TextView = findViewById(R.id.sumary_title_name)
        var seat : TextView = findViewById(R.id.sumary_seat_name)
        var pago : String = ""
        var cash: RadioButton = findViewById(R.id.pagoCash)
        var card: RadioButton = findViewById(R.id.pagoCard)

        cash.isChecked = true

        var asiento : Int = 0
        var nombrePeli : String = ""
        var pos : Int = 0


        if (bundle != null){
            asiento = bundle.getInt("asiento" ,0)
            nombrePeli = bundle.getString("name", "")
            pos = bundle.getInt("pos")
        }

        peli.text = DataProvider.peliculas[pos].titulo

        seat.setText(asiento.toString())

        btn.setOnClickListener{
            if(cash.isChecked){
                pago = "cash"
            }
            else{
                pago = "card"
            }


            var cliente: Cliente = Cliente("Arturo", pago, asiento)

                DataProvider.peliculas[pos].seats.add(cliente)

            Toast.makeText(this, "Enjoy the movie", Toast.LENGTH_LONG).show()

            val intent: Intent = Intent(this, CatalogActivity::class.java)
            startActivity(intent)

        }
    }
}