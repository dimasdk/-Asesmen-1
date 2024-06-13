package org.d3if0041.assessment.inventorytoko.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import org.d3if0041.assessment.inventorytoko.model.Mandi

@Database(entities = [Mandi::class], version = 2, exportSchema = false)
abstract class MandiDb : RoomDatabase() {
    abstract val dao : MandiDao

    companion object {
        @Volatile
        private var INSTANCE: MandiDb? = null

        fun getInstance(context: Context): MandiDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MandiDb::class.java,
                        "mandi_database"
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