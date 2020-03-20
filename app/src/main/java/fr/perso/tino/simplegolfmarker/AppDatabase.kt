package fr.perso.tino.simplegolfmarker

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.perso.tino.simplegolfmarker.dao.CourseDao
import fr.perso.tino.simplegolfmarker.model.Course
import fr.perso.tino.simplegolfmarker.model.CourseHole

@Database(entities = arrayOf(Course::class, CourseHole::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
}