package org.d3if0041.assessment.inventorytoko.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.d3if0041.assessment.inventorytoko.model.Catatan

@Dao
interface CatatanDao {
    @Insert
    suspend fun insert(catatan: Catatan)
    @Update
    suspend fun update(catatan: Catatan)
    @Query("SELECT * FROM catatan ORDER BY nama DESC")
    fun getCatatan(): Flow<List<Catatan>>
    @Query("SELECT * FROM catatan WHERE nama = :nama")
    suspend fun getCatatanById(nama: String): Catatan?
    @Query("DELETE FROM catatan WHERE nama = :nama")
    suspend fun deleteCatatanById(nama: String)
}