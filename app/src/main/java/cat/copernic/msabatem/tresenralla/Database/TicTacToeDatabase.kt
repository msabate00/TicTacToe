package cat.copernic.msabatem.tresenralla.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cat.copernic.msabatem.tresenralla.HistorialEntrada

@Database(entities = [HistorialEntrada::class], version = 1, exportSchema = false)
abstract class TicTacToeDatabase: RoomDatabase() {
    abstract val ticTacToeDatabaseDao: TicTacToeDatabaseDao;

    companion object{
        @Volatile
        private var INSTANCE: TicTacToeDatabase? = null
        fun getInstance(context: Context): TicTacToeDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TicTacToeDatabase::class.java,
                        "historial_table")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance;
            }
        }
    }


}