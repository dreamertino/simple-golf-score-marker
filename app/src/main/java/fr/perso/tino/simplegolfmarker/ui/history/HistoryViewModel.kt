package fr.perso.tino.simplegolfmarker.ui.history

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import fr.perso.tino.simplegolfmarker.AppDatabase
import fr.perso.tino.simplegolfmarker.SessionRepository
import fr.perso.tino.simplegolfmarker.model.SessionResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryViewModel(application: Application) : AndroidViewModel(application) {
    private val repo: SessionRepository
    val sessions: LiveData<List<SessionResult>>

    init {
        Log.i("History", "view created")
        val sessionDao = AppDatabase.getDatabase(application).sessionDao()
        repo = SessionRepository(sessionDao)
        Log.i("History", "Chargement des sessions ...")
        sessions = repo.getAllSessions()
        Log.i("History", "Chargement des sessions OK")
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                sessions.asFlow().first().let {
                    Log.i(null, "Sessions trouvées !!!")
                    it.forEach { e -> Log.i(null, "Session (uid = ${e.uid})") }
                }
            }
        }

    }
}
