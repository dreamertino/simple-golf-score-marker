package fr.perso.tino.simplegolfmarker.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import fr.perso.tino.simplegolfmarker.model.Course
import fr.perso.tino.simplegolfmarker.model.CourseWithHoles

@Dao
interface CourseDao {
    @Query("SELECT * FROM Course")
    fun getAll(): List<Course>

    @Insert
    fun insertAll(vararg courses: Course)

    @Transaction
    @Query("SELECT * FROM Course")
    fun getUsersWithPlaylists(): List<CourseWithHoles>
}