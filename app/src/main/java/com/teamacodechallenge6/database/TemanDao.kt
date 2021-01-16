package com.teamacodechallenge6.database

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface TemanDao {
    @Query("SELECT * FROM Teman")
    fun getAllTeman(): List<Teman>

    @Insert(onConflict = REPLACE)
    fun insertTeman(teman:Teman):Long

    @Update
    fun updateTeman(teman:Teman):Int

    @Delete
    fun deleteTeman(teman:Teman):Int

}