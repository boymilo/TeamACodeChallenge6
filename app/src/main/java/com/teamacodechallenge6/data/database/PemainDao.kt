package com.teamacodechallenge6.database

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface PemainDao {
    @Query("SELECT * FROM Pemain")
    fun getAllPemain(): List<Pemain>

    @Query("SELECT * FROM Pemain WHERE username = :username OR email = :username")
    fun getPemain(username:String): Pemain

    @Insert(onConflict = REPLACE)
    fun insertPemain(pemain:Pemain):Long

    @Update
    fun updatePemain(pemain:Pemain):Int

    @Delete
    fun deletePemain(pemain:Pemain):Int

}