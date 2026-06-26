package com.example.variablesariel

/**
 * Project: VariablesAriel
 * From: com.example.variablesariel
 * Created by: ariel
 * On: 25/06/202
 * All rights reserved: 2026
 */

val participante = mutableListOf<String>()
val puntos = mutableListOf<MutableList<Int>>()

fun menu() {
    println("""
        ===== Registro para torneo de videojuegos =====
    
    1. Registrar participante
    2. Registrar puntos
    3. Consultar participante
    4. Mostrar estadisticas del torneo
    5. Salir y finalizar programa
    
    Seleccione una opcion:
    """.trimIndent())
}

fun participantes(nombre: String): Boolean {
    for (p in participante){
        if (p == nombre) return true
    }
    return false
}

fun promedios(total: Int, cantidad: Int): Double {
    if (cantidad > 0) return total.toDouble() / cantidad.toDouble()
    return 0.0
}

fun categorias(puntos: Int): String {
    if(puntos >= 1000) return "Leyenda"
    else if(puntos >= 500) return "Experto"
    else if(puntos >= 200) return "Competidor"
    else return "Novato"
}

fun main() {
    while (true) {
        println("------------------------------------------------------------------")
        menu()
        val opcion = readln().toInt()

        if (opcion == 1) {
            println("------------------------------------------------------------------")
            println("Nombre del participante:")
            val nombre = readln()

            if (nombre == "") {
                println("El registro no puede estar vacio.")
                continue
            }

            if (participantes(nombre) == true) {
                println("El participante ya esta registrado.")
            } else {
                participante.add(nombre)
                puntos.add(mutableListOf<Int>())
                println("Participante registrado.")
            }

        } else if (opcion == 2) {
            println("------------------------------------------------------------------")
            println("Nombre del participante:")
            val nombre = readln()

            if (participantes(nombre) == false) {
                println("El participante no existe.")
                continue
            }

            var indices = 0
            for (i in 0..participante.size - 1) {
                if (participante[i] == nombre) {
                    indices = i
                }
            }

            println("Puntos a registrar:")
            val pts = readln().toInt()

            if (pts <= 0) {
                println("Los puntos deben ser mayor a 0")
            } else {
                puntos[indices].add(pts)
                println("Puntos registrados.")
            }

        } else if (opcion == 3) {
            println("------------------------------------------------------------------")
            println("Nombre del participante a consultar:")
            val nombre = readln()

            if (participantes(nombre) == true) {
                var indice = 0
                for (i in 0..participante.size - 1) {
                    if (participante[i] == nombre) {
                        indice = i
                    }
                }

                var totalPuntos = 0
                for (p in 0..puntos[indice].size - 1) {
                    totalPuntos += puntos[indice][p]
                }

                val categoria = categorias(totalPuntos)

                println("Nombre: $nombre")
                println("Total de puntos acumulados: $totalPuntos")
                println("Categoria obtenida: $categoria")
            } else {
                println("No se encontro al participante.")
            }

        } else if (opcion == 4) {
            println("------------------------------------------------------------------")
            println("Estadisticas del torneo")

            var totalPuntosTorneo = 0
            var mejorPuntuacion = -1
            var mejorParticipante = ""
            var menorPuntuacion = 99999999
            var peorParticipante = ""

            var leyenda = 0
            var experto = 0
            var competidor = 0
            var novato = 0

            for (i in 0..participante.size - 1) {
                var suma = 0
                for (j in 0..puntos[i].size - 1) {
                    suma += puntos[i][j]
                }

                totalPuntosTorneo += suma

                if (suma > mejorPuntuacion) {
                    mejorPuntuacion = suma
                    mejorParticipante = participante[i]
                }

                if (suma < menorPuntuacion && puntos[i].size > 0) {
                    menorPuntuacion = suma
                    peorParticipante = participante[i]
                }

                val cat = categorias(suma)
                when(cat) {
                    "Leyenda" -> leyenda += 1
                    "Experto" -> experto += 1
                    "Competidor" -> competidor += 1
                    "Novato" -> novato += 1
                }
            }

            val prom = promedios(totalPuntosTorneo, participante.size)

            println("Cantidad total de participantes: ${participante.size}")
            println("Total de puntos acumulados: $totalPuntosTorneo")
            println("Promedio de puntos por participante: $prom")
            println("Participante con mayor cantidad de puntos: $mejorParticipante ($mejorPuntuacion)")

            if (menorPuntuacion == 99999999) {
                println("Participante con menor cantidad de puntos: $peorParticipante (0)")
            } else {
                println("Participante con menor cantidad de puntos: $peorParticipante ($menorPuntuacion)")
            }

            println("\nParticipantes por cada categoria:")
            println("Leyenda: $leyenda")
            println("Experto: $experto")
            println("Competidor: $competidor")
            println("Novato: $novato")

        } else if (opcion == 5) {
            println("Finalizando y saliendo...")
            break
        } else {
            println("Opcion no valida")
        }
    }
}