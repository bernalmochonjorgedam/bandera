package com.utad.banderas.util

import com.utad.banderas.R

object BanderasProvider {
    fun getBanderas(): Map<String, Int> {
        return mapOf(
            "andalucia" to R.drawable.andalucia,
            "catalunya" to R.drawable.catalunya,
            "madrid" to R.drawable.madrid,
            "valencia" to R.drawable.valencia,
            "galicia" to R.drawable.galicia,
            "pais_vasco" to R.drawable.paisvasco,
            "castilla_y_leon" to R.drawable.castillaleon,
            "castilla_la_mancha" to R.drawable.castillamancha,
            "murcia" to R.drawable.murcia,
            "aragon" to R.drawable.aragon,
            "asturias" to R.drawable.asturias,
            "canarias" to R.drawable.canarias,
            "cantabria" to R.drawable.cantabria,
            "extremadura" to R.drawable.extremadura,
            "baleares" to R.drawable.baleares,
            "la_rioja" to R.drawable.larioja,
            "navarra" to R.drawable.navarra,
            "ceuta" to R.drawable.ceuta,
            "melilla" to R.drawable.melilla,
        )
    }
}
