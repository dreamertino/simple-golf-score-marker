package fr.perso.tino.simplegolfmarker

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Transaction
import fr.perso.tino.simplegolfmarker.dao.SessionDao
import fr.perso.tino.simplegolfmarker.model.HoleResult
import fr.perso.tino.simplegolfmarker.model.SessionResult
import fr.perso.tino.simplegolfmarker.model.SessionResultWithHoleResults

class SessionRepository(private val sessionDao: SessionDao) {
    suspend fun insertSession(sessionResult: SessionResult) {
        val insertWithResult = sessionDao.insertWithResult(sessionResult)
        Log.i(null, "Session uid = $insertWithResult enregistrée ")
    }

    @Transaction
    suspend fun insertFullSession(sessionResult: SessionResult, scores: List<HoleResult>) {
        Log.d("SessionRepository", "Tentative d'enregistré tout d'un coup")

        Log.d("Repo", "Enregistrement dans la table session ...")
        val insertWithResult = sessionDao.insertWithResult(sessionResult)
        Log.d("Repo", "Enregistrement dans la table session OK (uid = $insertWithResult)")

        scores.onEach { s -> s.sessionId = insertWithResult }.forEach {
            val holeResultId = sessionDao.insertWithResult(it)
            Log.i(
                "Repo",
                "Enregistrement dans la table HoleResult OK (uid = $holeResultId , score = ${it.score}, holeNumber = ${it.holeNumber})"
            )
        }

    }

    fun getAllSessions(): LiveData<List<SessionResultWithHoleResults>> {
        return sessionDao.getAllSessions()

    }
}