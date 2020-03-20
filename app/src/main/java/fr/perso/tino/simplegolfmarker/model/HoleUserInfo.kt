package fr.perso.tino.simplegolfmarker.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HoleUserInfo(
    @PrimaryKey(autoGenerate = true) val uid: Long,
    val score: Short,
    val sessionResultId: Int
)