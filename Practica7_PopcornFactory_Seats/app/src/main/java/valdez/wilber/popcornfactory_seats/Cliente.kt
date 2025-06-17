package valdez.wilber.popcornfactory_seats

import java.io.Serializable

data class Cliente (var nombre: String,
                    var tipoPago: String,
                    var asiento: Int): Serializable