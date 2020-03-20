package fr.perso.tino.simplegolfmarker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(null, "MainActivity>onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i(null, "Database starts ...")

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "golf-marker-db"
        ).build()
        Log.i(null, "Database started")

    }

    /** Called when the user taps the New Game button */
    fun newGame(view: View) {
        val intent = Intent(this, GameActivity::class.java).apply {

        }
        startActivity(intent)
    }
}
