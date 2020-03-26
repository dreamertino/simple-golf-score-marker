package fr.perso.tino.simplegolfmarker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(null, "MainActivity>onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    /** Called when the user taps the New Game button */
    fun newGame(view: View) {
        val intent = Intent(this, GameActivity::class.java).apply {

        }
        startActivity(intent)
    }

    fun history(view: View) {
        val intent = Intent(this, History::class.java).apply {

        }
        startActivity(intent)
    }
}
