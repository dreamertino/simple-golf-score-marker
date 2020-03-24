package fr.perso.tino.simplegolfmarker.model

data class Game(
    val scores: Map<Short, Short> = mutableMapOf(),

    val currentHole: Short = 1


) {
    fun getScoreCurrentHole(): Short? {
        return this.scores[currentHole]
    }


}