package fr.perso.tino.simplegolfmarker.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SessionResult(
    @PrimaryKey(autoGenerate = true) val uid: Long
)

@Entity
data class HoleResult(
    @PrimaryKey(autoGenerate = true) val uid: Long,
    val holeNumber: Short,
    val score: Int,
    val sessionId: Long
)
