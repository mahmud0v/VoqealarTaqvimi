package uz.tuit.voqealartaqvimi

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [EventEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun eventDao(): EventDao


    companion object {
        var db: AppDatabase? = null

        fun init(context: Context) {
            db = Room.databaseBuilder(context, AppDatabase::class.java, "EventCalendar.db")
                .allowMainThreadQueries()
                .build()
        }
    }


}