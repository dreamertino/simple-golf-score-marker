package fr.perso.tino.simplegolfmarker.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CourseHole(
    @PrimaryKey val uid: Long,
    val courseId: Long
)