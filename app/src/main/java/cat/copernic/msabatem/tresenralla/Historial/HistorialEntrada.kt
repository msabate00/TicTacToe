package cat.copernic.msabatem.tresenralla

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "historial_table")
data class HistorialEntrada(
    @PrimaryKey(autoGenerate = true)
    var entradaID: Long = 0L,


    @ColumnInfo(name = "tiempo")
    var tiempo: Long = 0L,

    @ColumnInfo(name = "ganador")
    var ganador: Int = 0

)