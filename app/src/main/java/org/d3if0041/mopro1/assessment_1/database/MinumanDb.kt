package org.d3if0041.mopro1.assessment_1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import org.d3if0041.mopro1.assessment_1.model.Minuman

@Database(entities = [Minuman::class], version = 2, exportSchema = false)
abstract class MinumanDb : RoomDatabase() {
    abstract val dao : MinumanDao

    companion object {
        @Volatile
        private var INSTANCE: MinumanDb? = null

        fun getInstance(context: Context): MinumanDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MinumanDb::class.java,
                        "minum_database"
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
                database.execSQL("ALTER TABLE minum ADD COLUMN gambarResId TEXT")
            }
        }
    }
}