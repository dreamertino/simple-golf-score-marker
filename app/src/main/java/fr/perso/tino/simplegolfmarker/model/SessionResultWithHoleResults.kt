package fr.perso.tino.simplegolfmarker.model

import androidx.room.Embedded
import androidx.room.Relation

data class SessionResultWithHoleResults(
    @Embedded val sessionResult: SessionResult,
    @Relation(
        parentColumn = "uid",
        entityColumn = "sessionId"
    )
    val holeResults: List<HoleResult>
)