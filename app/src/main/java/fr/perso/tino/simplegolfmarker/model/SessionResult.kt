package fr.perso.tino.simplegolfmarker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "session")
data class SessionResult(
    @PrimaryKey(autoGenerate = true) val uid: Long,
    @ColumnInfo(defaultValue = "'CURRENT_TIMESTAMP'") val lastModified: Date
) {
    constructor(uid: Long) : this(uid, Date())
}

@Entity
data class HoleResult(
    @PrimaryKey(autoGenerate = true) val uid: Long,
    val holeNumber: Short,
    val score: Int,
    var sessionId: Long
)
