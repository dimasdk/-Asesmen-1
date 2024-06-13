package org.d3if0041.assessment.inventorytoko.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.d3if0041.assessment.inventorytoko.model.Mandi

@Dao
interface MandiDao {
    @Insert
    suspend fun insert(mandi: Mandi)
    @Update
    suspend fun update(mandi: Mandi)
    @Query("SELECT * FROM mandi ORDER BY nama DESC")
    fun getMandi(): Flow<List<Mandi>>
    @Query("SELECT * FROM mandi WHERE nama = :nama")
    suspend fun getMandiById(nama: String): Mandi?
    @Query("DELETE FROM mandi WHERE nama = :nama")
    suspend fun deleteById(nama: String)
}