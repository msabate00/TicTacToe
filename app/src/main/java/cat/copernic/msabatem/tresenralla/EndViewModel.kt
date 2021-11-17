package cat.copernic.msabatem.tresenralla

import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class EndViewModel(time: Long, ganador: Int): ViewModel() {
    private val _tiempo = MutableLiveData<Long>(time);
    val tiempo: LiveData<Long> = _tiempo;

    private val _ganador = MutableLiveData<Int>(ganador);
    val ganador: LiveData<Int> = _ganador;

    private val _eventPlayAgain = MutableLiveData<Boolean>(false);
    val eventPlayAgain: LiveData<Boolean> = _eventPlayAgain;


    val currentTimeString = Transformations.map(tiempo){ time ->
        DateUtils.formatElapsedTime(time);
    }

    val ganadorString = when(_ganador.value){

        0 -> "SE ACABO EL TIEMPO, PERDISTE";
        1 -> "HAS GANADO"
        2 -> "HAS PERDIDO :("
        3 -> "HA SIDO UN EMPATE"
        else -> "Null";
    }


    fun onPlayAgain(){
        _eventPlayAgain.value = true;
    }
    fun onPlayAgainComplete(){
        _eventPlayAgain.value = false;
    }



}