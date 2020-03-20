package fr.perso.tino.simplegolfmarker.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class SessionResult(
    @PrimaryKey(autoGenerate = true) val uid: Int
)

data class SessionResultWithHolesUserInfo(
    @Embedded val session: SessionResult,
    @Relation(
        parentColumn = "uid",
        entityColumn = "sessionResultId"
    )
    val holesUserInfo: List<HoleUserInfo>
)