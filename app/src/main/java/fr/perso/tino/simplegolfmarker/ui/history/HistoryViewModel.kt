package fr.perso.tino.simplegolfmarker.ui.history

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import fr.perso.tino.simplegolfmarker.AppDatabase
import fr.perso.tino.simplegolfmarker.SessionRepository
import fr.perso.tino.simplegolfmarker.model.SessionResult

class HistoryViewModel(application: Application) : AndroidViewModel(application) {
    private val repo: SessionRepository
    var sessions: LiveData<List<SessionResult>>

    init {
        Log.i("ScoreForOneHoleViewModl", "view created")
        val sessionDao = AppDatabase.getDatabase(application).sessionDao()
        repo = SessionRepository(sessionDao)
        Log.i("History", "Chargement des sessions ...")
        sessions = repo.getAllSessions()
        Log.i("History", "Chargement des sessions OK")
        sessions.value?.forEach { Log.d("History", "Détail de la session $it") }
    }
}
