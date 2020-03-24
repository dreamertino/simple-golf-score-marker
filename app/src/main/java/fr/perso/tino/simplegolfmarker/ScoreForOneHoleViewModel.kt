package fr.perso.tino.simplegolfmarker

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreForOneHoleViewModel : ViewModel() {
    val currentScore: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().also {
            it.postValue(2)
        }
    }
    var currentHole: MutableLiveData<Short> = MutableLiveData(1)
    private var scores: MutableMap<Short, Int> = mutableMapOf()

    init {
        Log.i("ScoreForOneHoleViewModl", "view created")
    }

    fun increaseCurrentScore() {
        this.currentScore.value?.let { cV ->
            currentScore.postValue(cV.plus(1))
        }
    }

    fun decreaseCurrentScore() {
        this.currentScore.value?.let { cV ->
            currentScore.postValue(cV.minus(1))
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
}
