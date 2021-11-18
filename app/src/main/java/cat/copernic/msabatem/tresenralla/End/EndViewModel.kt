package cat.copernic.msabatem.tresenralla

import android.app.Application
import android.provider.SyncStateContract.Helpers.insert
import android.text.format.DateUtils
import androidx.lifecycle.*
import cat.copernic.msabatem.tresenralla.Database.TicTacToeDatabase
import cat.copernic.msabatem.tresenralla.Database.TicTacToeDatabaseDao
import kotlinx.coroutines.launch

class EndViewModel(
    database: TicTacToeDatabaseDao,
    aplication: Application,
    time: Long,
    ganador: Int): ViewModel() {

    private val database = database;

    private val _tiempo = MutableLiveData<Long>(time);
    val tiempo: LiveData<Long> = _tiempo;

    private val _ganador = MutableLiveData<Int>(ganador);
    val ganador: LiveData<Int> = _ganador;

    private val _eventPlayAgain = MutableLiveData<Boolean>(false);
    val eventPlayAgain: LiveData<Boolean> = _eventPlayAgain;

    private val _verHistorial = MutableLiveData<Boolean>(false);
    val verHistorial: LiveData<Boolean> = _verHistorial;

    val currentTimeString = Transformations.map(tiempo){ time ->
        DateUtils.formatElapsedTime(time);
    }

    val ganadorString = when(_ganador.value){

        0 -> "SE ACABO EL TIEMPO, PERDISTE :(";
        1 -> "HAS GANADO"
        2 -> "HAS PERDIDO :("
        3 -> "HA SIDO UN EMPATE"
        else -> "Null";
    }


    init {
        guardarPartida();
    }


    fun guardarPartida(){
        viewModelScope.launch {
            val newEntrada = HistorialEntrada(tiempo = tiempo.value ?: 0, ganador = ganador.value ?: 0)
            insert(newEntrada);
        }
    }
    private suspend fun insert(entrada: HistorialEntrada) {
        database.insert(entrada);
    }


    fun onPlayAgain(){
        _eventPlayAgain.value = true;
    }
    fun onPlayAgainComplete(){
        _eventPlayAgain.value = false;
    }

    fun onVerHistorial(){
        _verHistorial.value = true;
    }
    fun onVerHistorialComplete(){
        _verHistorial.value = false;
    }




}