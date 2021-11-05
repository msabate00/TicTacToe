package cat.copernic.msabatem.tresenralla

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val _tablero = MutableLiveData(Array<Int>(9){0})
    private val _terminado = MutableLiveData<Boolean>(false);
    private val _primerturno = MutableLiveData<Boolean>(true);
    private val _playerturno = MutableLiveData<Boolean>(true);

    var tablero: LiveData<Array<Int>> = _tablero;
    var terminado: LiveData<Boolean> = _terminado;
    var primerturno: LiveData<Boolean> = _primerturno;
    var playerturno: LiveData<Boolean> = _playerturno;






    fun reset(){
        _tablero.value = Array<Int>(9) {0};
        _playerturno.value = true;
        _terminado.value = false;
        _primerturno.value = true;
    }

    fun primerturno(b: Boolean){
        _primerturno.value = b;
    }
    fun playerTurno(b: Boolean){
        _playerturno.value = b;
    }


        


}