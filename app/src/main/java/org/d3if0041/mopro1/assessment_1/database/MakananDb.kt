package org.d3if0041.mopro1.assessment_1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import org.d3if0041.mopro1.assessment_1.model.Makanan

@Database(entities = [Makanan::class], version = 2, exportSchema = false)
abstract class MakananDb : RoomDatabase() {
    abstract val dao : MakanDao

    companion object {
        @Volatile
        private var INSTANCE: MakananDb? = null

        fun getInstance(context: Context): MakananDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MakananDb::class.java,
                        "makan_database"
                    )
                        .addMigrations(MIGRATION_1_2)
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE mandi ADD COLUMN gambarResId TEXT")
            }
        }
    }
}
