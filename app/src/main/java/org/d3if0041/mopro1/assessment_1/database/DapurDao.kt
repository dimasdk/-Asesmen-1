package org.d3if0041.mopro1.assessment_1.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.d3if0041.mopro1.assessment_1.model.Dapur
import org.d3if0041.mopro1.assessment_1.model.Makanan

@Dao
interface DapurDao {
    @Insert
    suspend fun insert(dapur: Dapur)
    @Update
    suspend fun update(dapur: Dapur)
    @Query("SELECT * FROM dapur ORDER BY nama DESC")
    fun getDapur(): Flow<List<Dapur>>
    @Query("SELECT * FROM dapur WHERE nama = :nama")
    suspend fun getDapurById(nama: String): Dapur?
    @Query("DELETE FROM dapur WHERE nama = :nama")
    suspend fun deleteDapurById(nama: String)
}