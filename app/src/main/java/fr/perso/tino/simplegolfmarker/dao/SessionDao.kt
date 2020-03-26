package fr.perso.tino.simplegolfmarker.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.perso.tino.simplegolfmarker.model.HoleResult
import fr.perso.tino.simplegolfmarker.model.SessionResult

@Dao
interface SessionDao {
    @Query("SELECT * FROM SessionResult")
    fun getAllSessions(): LiveData<List<SessionResult>>

    @Query("SELECT * FROM HoleResult")
    fun getHRForSession(sessionId: Long): LiveData<List<HoleResult>>

    @Insert
    fun insertAll(vararg courses: SessionResult)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(session: SessionResult)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(holeResult: HoleResult)

    @Insert
    suspend fun insertSessionWithHole(sessionResult: SessionResult, holeResult: List<HoleResult>)

}