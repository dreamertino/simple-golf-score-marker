package fr.perso.tino.simplegolfmarker

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import fr.perso.tino.simplegolfmarker.model.SessionResult
import kotlinx.coroutines.launch

class ScoreForOneHoleViewModel(application: Application) : AndroidViewModel(application) {
    val currentScore: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().also {
            it.postValue(2)
        }
    }
    var currentHole: MutableLiveData<Short> = MutableLiveData(1)
    private var scores: MutableMap<Short, Int> = mutableMapOf()
    private val repo: SessionRepository



    init {
        Log.i("ScoreForOneHoleViewModl", "view created")
        val sessionDao = AppDatabase.getDatabase(application).sessionDao()
        repo = SessionRepository(sessionDao)
    }

    fun increaseCurrentScore() {
        this.currentScore.value?.let { cV ->
            currentScore.postValue(cV.plus(1))
        }
    }

    fun decreaseCurrentScore() {
        this.currentScore.value?.let { cV ->
            if (cV > 1) {
                currentScore.postValue(cV.minus(1))
            }
        }
    }

    fun nextHole() {
        currentHole.value?.run {
            currentScore.value?.let {
                scores[this] = it
            }
            currentHole.postValue(this.inc())
            if (scores[this.inc()] == null) {
                currentScore.postValue(2)
            } else {
                currentScore.postValue(scores[this.inc()])
            }
        }

    }

    fun previousHole() {
        currentHole.value?.run {
            currentScore.value?.let { scores[this] = it }
            this.dec().let { c ->
                currentScore.postValue(
                    scores[c]
                )
            }
            currentHole.postValue(this.dec())
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(null, "ScoreForOneHole cleared")
    }

    fun saveCurrentSession() = viewModelScope.launch {
        Log.i("GameModel", "Enregistrement de la session ...")
        val sToSave = scores.entries.map { it -> it.value }.toMutableList()
        repo.insertFullSession(SessionResult(0), sToSave)
        Log.i("GameModel", "Enregistrement OK")
    }
}
