package cat.copernic.msabatem.tresenralla

import android.app.ActionBar
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import cat.copernic.msabatem.tresenralla.databinding.ActivityMainBinding
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(GameViewModel::class.java)
    }


    lateinit var binding: ActivityMainBinding;
    val IA = 1;
    val PLAYER = 2;
    var tablero = Array<Int>(9) {0};
    var playerTurno = true;
    var terminado = false;
    var primer_turno = true;


    val combinacionGanadora = arrayOf(
        intArrayOf(0, 1, 2),
        intArrayOf(3, 4, 5),
        intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6),
        intArrayOf(1, 4, 7),
        intArrayOf(2, 5, 8),
        intArrayOf(0, 4, 8),
        intArrayOf(2, 4, 6)
    )
    private var buttonID: Array<Int> = arrayOf(
        R.id.b1,
        R.id.b2,
        R.id.b3,
        R.id.b4,
        R.id.b5,
        R.id.b6,
        R.id.b7,
        R.id.b8,
        R.id.b9
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        tablero = viewModel.tablero.value ?: Array<Int>(9){0};
        terminado = viewModel.terminado.value ?: false;
        primer_turno = viewModel.primerturno.value ?: true;
        playerTurno = viewModel.playerturno.value ?: true;
        mostrar();


        //binding.b1.setBackgroundResource(viewModel.btn1.value ?: R.drawable.none);
        //viewModel.btn1.equals(R.drawable.player_icon);


        binding.restart.setOnClickListener {
            viewModel.reset();
            reset();
            mostrar();
        }



    }

    override fun onStart (){
        super.onStart()


        //findViewById<Button>(R.id.b1);

        for(i in buttonID){
            findViewById<View>(i).setOnClickListener{
                pulsacion(it);
            };


        }


    }

    fun mostrar(){
        for(i in 0..8){

            if(tablero[i] == PLAYER){
                findViewById<Button>(buttonID[i]).setBackgroundResource(R.drawable.player_icon);
            }else if (tablero[i] == IA){
                findViewById<Button>(buttonID[i]).setBackgroundResource(R.drawable.ia_icon);
            }else{
                findViewById<Button>(buttonID[i]).setBackgroundResource(R.drawable.none);
            }

        }
    }


    fun reset(){
        tablero = Array<Int>(9) {0};
        playerTurno = true;
        terminado = false;
        primer_turno = true;

        for(i in buttonID){
            findViewById<Button>(i).setBackgroundResource(R.drawable.none);
        }

    }

    fun pulsacion(view: View) {

        Log.i("bucle",terminado.toString() + " _ " + viewModel.terminado.value.toString());
        if (playerTurno) {

            if(!terminado) {
                var contador = 0;
                for (i in buttonID) {
                    // Log.i("Bucle", contador.toString())
                    if (i == view.id && tablero[contador] == 0) {
                        view.setBackgroundResource(R.drawable.player_icon);
                        tablero[contador] = PLAYER;
                        playerTurno = false;
                        viewModel.playerTurno(false);
                        break;
                    }

                    contador++;
                }


                if (!playerTurno) {
                    comprobarGanador();

                    if (!terminado) {
                        // Log.i("Bucle", "salido")
                        turnoIA();
                    }
                }
            }


        }
        
        //turnoIA();
    }

    fun turnoIA(){
        var puesto: Boolean = false;

        fun atacar(){



            for(posicion in combinacionGanadora){


                //CON LA PRIMERA Y SEGUNDO COMO JUGADOR
                if(tablero[posicion[0]] == tablero[posicion[1]] &&
                    tablero[posicion[0]] == IA &&
                    tablero[posicion[2]] == 0
                ){
                    puesto = true;
                    tablero[posicion[2]] = IA
                    findViewById<Button>(buttonID[posicion[2]]).setBackgroundResource(R.drawable.ia_icon);
                    break;

                    //CON LA PRIMERA Y TERCERA COMO JUGADOR
                }else if(tablero[posicion[0]] == tablero[posicion[2]] &&
                    tablero[posicion[0]] == IA &&
                    tablero[posicion[1]] == 0
                ){
                    puesto = true;
                    tablero[posicion[1]] = IA
                    findViewById<Button>(buttonID[posicion[1]]).setBackgroundResource(R.drawable.ia_icon);
                    break;

                    //CON LA SEGUNDA Y TERCERA COMO JUGADOR
                }else if(tablero[posicion[1]] == tablero[posicion[2]] &&
                    tablero[posicion[1]] == IA &&
                    tablero[posicion[0]] == 0
                ){
                    puesto = true;
                    tablero[posicion[0]] = IA
                    findViewById<Button>(buttonID[posicion[0]]).setBackgroundResource(R.drawable.ia_icon);
                    break;
                }
            }
        }

        fun proteger(){
            for(posicion in combinacionGanadora){

                //CON LA PRIMERA Y SEGUNDO COMO JUGADOR
                if(tablero[posicion[0]] == tablero[posicion[1]] &&
                    tablero[posicion[0]] == PLAYER &&
                    tablero[posicion[2]] == 0
                ){
                    puesto = true;
                    tablero[posicion[2]] = IA
                    findViewById<Button>(buttonID[posicion[2]]).setBackgroundResource(R.drawable.ia_icon);
                    break;

                    //CON LA PRIMERA Y TERCERA COMO JUGADOR
                }else if(tablero[posicion[0]] == tablero[posicion[2]] &&
                    tablero[posicion[0]] == PLAYER &&
                    tablero[posicion[1]] == 0
                ){
                    puesto = true;
                    tablero[posicion[1]] = IA
                    findViewById<Button>(buttonID[posicion[1]]).setBackgroundResource(R.drawable.ia_icon);
                    break;


                    //CON LA SEGUNDA Y TERCERA COMO JUGADOR
                }else if(tablero[posicion[1]] == tablero[posicion[2]] &&
                    tablero[posicion[1]] == PLAYER &&
                    tablero[posicion[0]] == 0
                ){
                    puesto = true;
                    tablero[posicion[0]] = IA
                    findViewById<Button>(buttonID[posicion[0]]).setBackgroundResource(R.drawable.ia_icon);
                    break;

                }
            }
        }


        if(primer_turno){
            if(tablero[4] == PLAYER){
                val esquinas = arrayOf(0,2,6,8);
                while(!puesto){
                    val r = Random.nextInt(0,4);
                    tablero[r] = IA;
                    findViewById<Button>(buttonID[r]).setBackgroundResource(R.drawable.ia_icon);
                    puesto = true;
                    primer_turno = false;
                    viewModel.primerturno(false);
                    break;
                }
            }else{
                tablero[4] = IA;
                findViewById<Button>(buttonID[4]).setBackgroundResource(R.drawable.ia_icon);
                puesto = true;
                primer_turno = false;
                viewModel.primerturno(false);
            }

        }


        if(!puesto){
            atacar();
        }
        if(!puesto){
            proteger();
        }


        if(!puesto){

            while (!puesto){
                val r = Random.nextInt(0, 8)
                if(tablero[r] == 0){
                    tablero[r] = IA;
                    findViewById<Button>(buttonID[r]).setBackgroundResource(R.drawable.ia_icon);
                    puesto = true;
                    break;
                }
            }
        }
        comprobarGanador();

        if(!terminado) {
            playerTurno = true;
            viewModel.playerTurno(true);
        }
    }


    fun comprobarGanador(){

        for (posicion in combinacionGanadora) {
            if (tablero[posicion[0]] == tablero[posicion[1]] && tablero[posicion[1]] == tablero[posicion[2]] && tablero[posicion[0]] != 0) {
                if (tablero[posicion[0]] == PLAYER){
                    Toast.makeText(applicationContext, "HA GANADO EL JUGADOR", Toast.LENGTH_LONG).show();
                } else if (tablero[posicion[0]] == IA){
                    Toast.makeText(applicationContext, "HA GANADO LA MAQUINA", Toast.LENGTH_LONG).show();
                }
                terminado = true
            }
        }
        var cero = false;
        for(i in tablero){
            if(i == 0){
                cero = true;
            }
        }
        if(!cero){
            Toast.makeText(applicationContext, "HA SIDO EMPATE", Toast.LENGTH_LONG).show();
            terminado = true;
        }


        //return terminado;

    }


    fun debug(a: Any){
        Toast.makeText(applicationContext, a.toString(), Toast.LENGTH_SHORT).show();
    }




}






