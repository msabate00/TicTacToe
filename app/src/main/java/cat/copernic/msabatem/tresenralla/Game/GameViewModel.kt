package cat.copernic.msabatem.tresenralla

import android.content.res.Resources
import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    val IA = 1;
    val PLAYER = 2;



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

    private val _ganador = MutableLiveData<Int>(0);
    var ganador: LiveData<Int> = _ganador;

    private val _comprovarGanador = MutableLiveData<Boolean>(false);
    var comprovarGanador: LiveData<Boolean> = _comprovarGanador;

    private val _turnoIA = MutableLiveData<Boolean>(false);
    var turnoIA: LiveData<Boolean> = _turnoIA;



    private val _currentTime = MutableLiveData<Long>();
    val currentTime: LiveData<Long> = _currentTime;

    val currentTimeString = Transformations.map(currentTime){ time ->
        DateUtils.formatElapsedTime(time);
    }

    private val timer: CountDownTimer;

    init {
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND){
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
        timer.cancel()
        timer.start();
        _tablero.value = Array<Int>(9) {0};
        _playerturno.value = true;
        _terminado.value = false;
        _primerturno.value = true;
        _ganador.value = 0;
    }

    fun primerturno(b: Boolean){
        _primerturno.value = b;
    }
    fun playerTurno(b: Boolean){
        _playerturno.value = b;
    }

    fun setGanador(i: Int){
        _ganador.value = i;
    }

    fun setTerminado(i: Boolean){
        _terminado.value = i;
    }

    fun onGameFinish(){
        _eventGameFinish.value = true;
    }
    fun onGameFinishComplete(){
        _eventGameFinish.value = false;
    }

    fun onComprovarGanador(){
        _comprovarGanador.value = true;
    }
    fun onComprovarGanadorComplete(){
        _comprovarGanador.value = false;
    }

    fun onPlayIA(){
        _turnoIA.value = true;
    }
    fun onPlayIAComplete(){
        _turnoIA.value = false;
    }


    fun onPulsacion(view: View){

        if (_playerturno.value ?: false) {

            if(!terminado.value!!) {


                //view.id.toString();

                //var result = string.filter { it.isDigit() }
                //result = result.plus(-1);


                val posi = view.tag.toString().toInt();



                if(tablero.value?.get(posi) ?: 0 == 0){
                    tablero.value?.set(posi,PLAYER);
                    view.setBackgroundResource(R.drawable.player_icon);
                    _playerturno.value = false;
                }


                //tablero.value?.set(Integer.getInteger(view.tag.toString()),PLAYER);


                if (playerturno.value == false) {
                    onComprovarGanador();

                    if (!terminado.value!!) {
                        // Log.i("Bucle", "salido")
                      //  turnoIA();
                        Log.i("Entra",terminado.value.toString()!!);
                        onPlayIA();
                    }
                }
            }


        }
    }




    companion object{
        private const val DONE = 0L;
        private const val ONE_SECOND = 1_000L;
        private const val COUNTDOWN_TIME = 60_000L;
    }


        


}