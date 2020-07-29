@file:Suppress("unused")

package `in`.curioustools.architectures.db

import `in`.curioustools.architectures.models.Pokemon
import `in`.curioustools.architectures.utils.room.StringXListConverter
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/*

plugins {
   ...
    id 'kotlin-kapt'
}
android {
    ...

    defaultConfig {
        ...
        JavaCompileOptions {
            annotationProcessorOptions {
                arguments += [
                        "room.schemaLocation":"$projectDir/schemas".toString(),
                        "room.incremental":"true",
                        "room.expandProjection":"true"]
            }
        }
    }

* */

@TypeConverters(StringXListConverter::class)
@Database(entities = [Pokemon::class],version = 1, exportSchema = true)
abstract class PokeDb:RoomDatabase(){

    abstract val notesDbAccessDao: PokemonAccessDao

    companion object {
        private var INSTANCE: PokeDb? = null
        private const val DB_NAME = DbConstants.DB_NAME

        @JvmStatic
        @Synchronized
        fun getInstance(context: Context, debug: Boolean = false): PokeDb {

            if (debug) {
                return Room.inMemoryDatabaseBuilder(context, PokeDb::class.java).build()
            }
            if (INSTANCE == null) {
                INSTANCE =
                    Room.databaseBuilder(context, PokeDb::class.java, DB_NAME)
                        .fallbackToDestructiveMigration().build()
            }
            return INSTANCE!!
        }
    }
}

