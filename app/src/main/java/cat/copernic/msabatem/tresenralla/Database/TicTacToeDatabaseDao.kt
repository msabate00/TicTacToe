package cat.copernic.msabatem.tresenralla.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import cat.copernic.msabatem.tresenralla.HistorialEntrada

@Dao
interface TicTacToeDatabaseDao {

    @Insert
    suspend fun insert(entrada: HistorialEntrada)

    @Update
    suspend fun update(entrada: HistorialEntrada)

    @Query("SELECT * from historial_table WHERE entradaID = :key")
    suspend fun get(key: Long): HistorialEntrada?

    @Query("DELETE FROM historial_table")
    suspend fun clear()

    @Query("SELECT * FROM historial_table ORDER BY entradaID DESC LIMIT 1")
    suspend fun getLast(): HistorialEntrada?

    @Query("SELECT * FROM historial_table ORDER BY entradaID DESC")
    fun getAll(): LiveData<List<HistorialEntrada>>

}