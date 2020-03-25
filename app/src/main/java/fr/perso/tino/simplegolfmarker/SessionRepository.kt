package fr.perso.tino.simplegolfmarker

import androidx.room.Transaction
import fr.perso.tino.simplegolfmarker.dao.SessionDao
import fr.perso.tino.simplegolfmarker.model.SessionResult

class SessionRepository(private val sessionDao: SessionDao) {
    suspend fun insertSession(sessionResult: SessionResult) {
        sessionDao.insert(sessionResult)
    }

    @Transaction
    suspend fun insertFullSession(sessionResult: SessionResult, scores: List<Any>) {
        insertSession(sessionResult)
    }
}