package com.utad.banderas.data

import com.utad.banderas.model.Autonomia

object AutonomiaData {
    fun obtenerAutonomias(): List<Autonomia> = listOf(
        Autonomia(1, "Andalucía", "andalucia"),
        Autonomia(2, "Cataluña", "catalunya"),
        Autonomia(3, "Madrid", "madrid"),
        Autonomia(4, "Valencia", "valencia"),
        Autonomia(5, "Galicia", "galicia"),
        Autonomia(6, "País Vasco", "pais_vasco"),
        Autonomia(7, "Castilla y León", "castilla_y_leon"),
        Autonomia(8, "Castilla-La Mancha", "castilla_la_mancha"),
        Autonomia(9, "Murcia", "murcia"),
        Autonomia(10, "Aragón", "aragon"),
        Autonomia(11, "Asturias", "asturias"),
        Autonomia(12, "Canarias", "canarias"),
        Autonomia(13, "Cantabria", "cantabria"),
        Autonomia(14, "Extremadura", "extremadura"),
        Autonomia(15, "Balears", "baleares"),
        Autonomia(16, "La Rioja", "la_rioja"),
        Autonomia(17, "Navarra", "navarra"),
        Autonomia(18, "Ceuta", "ceuta"),
        Autonomia(19, "Melilla", "melilla")
    )
}