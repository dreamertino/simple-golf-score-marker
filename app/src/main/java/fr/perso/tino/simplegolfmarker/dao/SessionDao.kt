package fr.perso.tino.simplegolfmarker.dao

import androidx.room.Dao
import androidx.room.Insert
import fr.perso.tino.simplegolfmarker.model.HoleUserInfo
import fr.perso.tino.simplegolfmarker.model.SessionResult

@Dao
interface SessionDao {

    @Insert
    fun insertAll(vararg courses: SessionResult)

    @Insert
    fun insertHoleUserInfo(vararg holeUserInfo: HoleUserInfo)
}