package org.d3if0041.mopro1.assessment_1.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.d3if0041.mopro1.assessment_1.model.Minuman

@Dao
interface MinumanDao {
    @Insert
    suspend fun insert(minuman: Minuman)
    @Update
    suspend fun update(minuman: Minuman)
    @Query("SELECT * FROM minuman ORDER BY nama DESC")
    fun getMinuman(): Flow<List<Minuman>>
    @Query("SELECT * FROM minuman WHERE nama = :nama")
    suspend fun getMinumanById(nama: String): Minuman?
    @Query("DELETE FROM minuman WHERE nama = :nama")
    suspend fun deleteMinumanById(nama: String)
}