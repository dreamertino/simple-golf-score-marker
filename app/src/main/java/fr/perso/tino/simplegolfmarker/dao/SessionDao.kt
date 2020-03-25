package fr.perso.tino.simplegolfmarker.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import fr.perso.tino.simplegolfmarker.model.SessionResult

@Dao
interface SessionDao {

    @Insert
    fun insertAll(vararg courses: SessionResult)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(session: SessionResult)
}