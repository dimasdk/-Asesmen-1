package org.d3if0041.assessment.inventorytoko.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.d3if0041.assessment.inventorytoko.model.Clean

@Dao
interface CleanDao {
    @Insert
    suspend fun insert(clean: Clean)
    @Update
    suspend fun update(clean: Clean)
    @Query("SELECT * FROM clean ORDER BY nama DESC")
    fun getClean(): Flow<List<Clean>>
    @Query("SELECT * FROM clean WHERE nama = :nama")
    suspend fun getCleanById(nama: String): Clean?
    @Query("DELETE FROM clean WHERE nama = :nama")
    suspend fun deleteCleanById(nama: String)
}