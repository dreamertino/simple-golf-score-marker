package fr.perso.tino.simplegolfmarker.model

import androidx.room.*

@Entity
data class Course(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "golf") val golf: String?
)

data class CourseWithHoles(
    @Embedded val course: Course,
    @Relation(
        parentColumn = "uid",
        entityColumn = "courseId"
    )
    val holes: List<CourseHole>
)