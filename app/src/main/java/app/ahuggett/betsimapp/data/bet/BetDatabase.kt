package app.ahuggett.betsimapp.data.bet

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database(entities = [Bet::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class BetDatabase : RoomDatabase() {
    abstract fun betDao(): BetDao

    companion object {
        @Volatile
        private var INSTANCE: BetDatabase? = null

        fun getDatabase(context: Context): BetDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        BetDatabase::class.java,
                        "bet_database")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}


class Converters {
    @TypeConverter
    fun fromBetType(betType: BetType): String {
        return betType.name
    }

    @TypeConverter
    fun toBetType(betType: String): BetType {
        return BetType.valueOf(betType)
    }
}