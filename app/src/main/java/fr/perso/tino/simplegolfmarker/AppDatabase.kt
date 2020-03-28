package fr.perso.tino.simplegolfmarker

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.perso.tino.simplegolfmarker.dao.SessionDao
import fr.perso.tino.simplegolfmarker.model.HoleResult
import fr.perso.tino.simplegolfmarker.model.SessionResult
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(SessionResult::class, HoleResult::class), version = 3)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sessionDao(): SessionDao

    companion object {
        val TAG = "AppDatabase"
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "golf-marker-db"
                ).fallbackToDestructiveMigration().build()
                //TODO Supprimer fallbackToDestructiveMigration
                //TODO Supprimer l'étape de suppresison de toutes les données
                GlobalScope.launch {
                    Log.w(TAG, "Suppression de toutes les données ...")
                    instance.clearAllTables()
                    Log.w(TAG, "Suppression de toutes les données Ok")
                }

                INSTANCE = instance
                return instance
            }
        }
    }

    override fun close() {
        Log.i("AppDatabase", "Fermeture bdd")
        super.close()
    }


}