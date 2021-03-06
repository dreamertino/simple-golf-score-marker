package fr.perso.tino.simplegolfmarker.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.perso.tino.simplegolfmarker.model.HoleResult
import fr.perso.tino.simplegolfmarker.model.SessionResult
import fr.perso.tino.simplegolfmarker.model.SessionResultWithHoleResults

@Dao
interface SessionDao {
    @Transaction
    @Query("SELECT * FROM session")
    fun getAllSessions(): LiveData<List<SessionResultWithHoleResults>>


    @Query("SELECT * FROM HoleResult WHERE sessionId = :sessionId ")
    fun getHRForSession(sessionId: Long): LiveData<List<HoleResult>>

    @Insert
    fun insertAll(vararg courses: SessionResult)


    @Insert
    suspend fun insert(session: SessionResult)

    @Insert
    suspend fun insertWithResult(session: SessionResult): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(holeResult: HoleResult)

    @Insert
    suspend fun insertWithResult(holeResult: HoleResult): Long

    @Insert
    suspend fun insertWithHoleResults(sessionResult: SessionResult, holeResult: List<HoleResult>)

    @Insert
    suspend fun insertHoleResult(vararg score: HoleResult)

    @Insert
    suspend fun insertAllHoleResults(h: List<HoleResult>)

}