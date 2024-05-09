package org.d3if0041.mopro1.assessment_1.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.d3if0041.mopro1.assessment_1.model.Makanan

@Dao
interface MakanDao {
    @Insert
    suspend fun insert(makanan: Makanan)
    @Update
    suspend fun update(makanan: Makanan)
    @Query("SELECT * FROM makanan ORDER BY nama DESC")
    fun getMakanan(): Flow<List<Makanan>>
    @Query("SELECT * FROM makanan WHERE nama = :nama")
    suspend fun getMakananById(nama: String): Makanan?
    @Query("DELETE FROM makanan WHERE nama = :nama")
    suspend fun deleteMakananById(nama: String)
}