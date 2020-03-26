package fr.perso.tino.simplegolfmarker

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Transaction
import fr.perso.tino.simplegolfmarker.dao.SessionDao
import fr.perso.tino.simplegolfmarker.model.HoleResult
import fr.perso.tino.simplegolfmarker.model.SessionResult

class SessionRepository(private val sessionDao: SessionDao) {
    suspend fun insertSession(sessionResult: SessionResult) {
        val insertWithResult = sessionDao.insertWithResult(sessionResult)
        Log.i(null, "Session uid = $insertWithResult enregistrée ")
    }

    @Transaction
    suspend fun insertFullSession(sessionResult: SessionResult, scores: List<HoleResult>) {
        Log.d("Repo", "Tentative d'enregistré tout d'un coup")
        sessionDao.getAllSessions().value?.let {
            Log.d(
                "Repo",
                "${it.size} sessions avant tentative"
            )
        }

        sessionDao.insertWithHoleResults(sessionResult, scores)
        //Vérification
        sessionDao.getAllSessions().value?.let {
            Log.d(
                "Repo",
                "${it.size} sessions après tentative"
            )
        }

        Log.d("Repo", "Enregistrement dans la table session ...")
        val insertWithResult = sessionDao.insertWithResult(sessionResult)
        Log.d("Repo", "Enregistrement dans la table session OK (uid = $insertWithResult)")

        scores.onEach { s -> s.sessionId = insertWithResult }.forEach {
            val holeResultId = sessionDao.insertWithResult(it)
            Log.i("Repo", "Enregistrement dans la table HoleResult OK (uid = $holeResultId)")
        }
        sessionDao.getAllSessions().value.run {
            Log.i("Repository", "${this?.size} sessions enregistrées")
            this?.forEach {
                Log.d("Repo", "Détail de la session ${it.uid} modified on ${it.lastModified}")
            }
        }
    }

    fun getAllSessions(): LiveData<List<SessionResult>> {
        return sessionDao.getAllSessions()
    }
}