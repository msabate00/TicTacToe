package cat.copernic.msabatem.tresenralla

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
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

    private val _eventGameFinish = MutableLiveData<Boolean>(false);
    val eventGameFinish: LiveData<Boolean> = _eventGameFinish;


    private val _currentTime = MutableLiveData<Long>();
    val currentTime: LiveData<Long> = _currentTime;

    val currentTimeString = Transformations.map(currentTime){ time ->
        DateUtils.formatElapsedTime(time);
    }

    private val timer: CountDownTimer;

    init {
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND){
            /**
             * Callback fired on regular interval.
             * @param millisUntilFinished The amount of time until finished.
             */
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished/ ONE_SECOND;
            }

            /**
             * Callback fired when the time is up.
             */
            override fun onFinish() {
                _currentTime.value = DONE;
                onGameFinish();
            }

        };
        timer.start();
    }

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

    fun onGameFinish(){
        _eventGameFinish.value = true;
    }
    fun onGameFinishComplete(){
        _eventGameFinish.value = false;
    }


    companion object{
        private const val DONE = 0L;
        private const val ONE_SECOND = 1_000L;
        private const val COUNTDOWN_TIME = 60_000L;
    }


        


}