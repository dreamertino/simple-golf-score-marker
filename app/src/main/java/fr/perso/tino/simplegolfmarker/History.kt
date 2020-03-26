package fr.perso.tino.simplegolfmarker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.perso.tino.simplegolfmarker.ui.history.HistoryFragment

class History : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HistoryFragment.newInstance())
                .commitNow()
        }
    }
}
