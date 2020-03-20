package fr.perso.tino.simplegolfmarker

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class GameActivity : AppCompatActivity() {
    lateinit var scoreView: TextView
    var score: String? = "2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            this.score = savedInstanceState.getString("score")
        }
        setContentView(R.layout.activity_game)

        this.scoreView = findViewById<EditText>(R.id.score)
        val addBtn = findViewById<Button>(R.id.add)
        addBtn.setOnClickListener(View.OnClickListener {
            // Code here executes on main thread after user presses button
            this.addTry(it)
        })
        val minusBtn = findViewById<Button>(R.id.minus)
        minusBtn.setOnClickListener {
            removeTry(it)
        }

    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState?.run {
            putString("score", scoreView.text.toString())
        }
        savedInstanceState.putString("score", this.score)
        super.onSaveInstanceState(savedInstanceState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {

        super.onRestoreInstanceState(savedInstanceState)
        scoreView.text = savedInstanceState?.getString(STATE_SCORE)
    }

    fun addTry(view: View) {
        Log.d(null, "Ajout d'un essai")
        val scoreMoreOne = scoreView.text.toString().toInt().plus(1)
        scoreView.setText(scoreMoreOne)
    }

    fun removeTry(view: View) {
        val scoreLessOne = scoreView.text.toString().toInt()
        if (scoreLessOne > 0) scoreView.setText(scoreLessOne.minus(1))
    }

    companion object {
        val STATE_SCORE = "playerScore"
        val STATE_LEVEL = "playerLevel"
    }
}
