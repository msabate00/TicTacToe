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
import androidx.lifecycle.Observer
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


    private lateinit var buttons: Array<View>;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        buttons = arrayOf(
            binding.b1,
            binding.b2,
            binding.b3,
            binding.b4,
            binding.b5,
            binding.b6,
            binding.b7,
            binding.b8,
            binding.b9,
        )



        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        tablero = viewModel.tablero.value ?: Array<Int>(9){0};
        terminado = viewModel.terminado.value ?: false;
        primer_turno = viewModel.primerturno.value ?: true;
        playerTurno = viewModel.playerturno.value ?: true;
        mostrar();


        binding.restart.setOnClickListener {
            viewModel.reset();
            mostrar();
           // reset();

        }


        viewModel.tablero.observe(this,
                {
                    newTablero -> tablero = newTablero;
                }
            )

        viewModel.terminado.observe(this,
            Observer{
                    newTerminado -> terminado = newTerminado;
            }
        );
        viewModel.primerturno.observe(this,
            Observer{
                    newPrimerturno -> primer_turno = newPrimerturno;
            }
        );
        viewModel.playerturno.observe(this,
            Observer{
                    newPlayerturno -> playerTurno = newPlayerturno;
            }
        );



    }

    override fun onStart (){
        super.onStart()


        //findViewById<Button>(R.id.b1);

        for(i in buttons){
            i.setOnClickListener{
                pulsacion(it);
            };


        }


    }

    fun mostrar(){
        for(i in 0..8){

            if(tablero[i] == PLAYER){
                buttons[i].setBackgroundResource(R.drawable.player_icon);
            }else if (tablero[i] == IA){
                buttons[i].setBackgroundResource(R.drawable.ia_icon);
            }else{
                buttons[i].setBackgroundResource(R.drawable.none);
            }

        }
    }

/*
    fun reset(){
        tablero = viewModel.tablero.value ?: Array<Int>(9){0};
        terminado = viewModel.terminado.value ?: false;
        primer_turno = viewModel.primerturno.value ?: true;
        playerTurno = viewModel.playerturno.value ?: true;
        mostrar();
    }*/

    fun pulsacion(view: View) {

        Log.i("bucle",terminado.toString() + " _ " + viewModel.terminado.value.toString());
        if (playerTurno) {

            if(!terminado) {
                var contador = 0;
                for (i in buttons) {
                    // Log.i("Bucle", contador.toString())
                    if (i == view && tablero[contador] == 0) {
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
                    buttons[posicion[2]].setBackgroundResource(R.drawable.ia_icon);
                    break;

                    //CON LA PRIMERA Y TERCERA COMO JUGADOR
                }else if(tablero[posicion[0]] == tablero[posicion[2]] &&
                    tablero[posicion[0]] == IA &&
                    tablero[posicion[1]] == 0
                ){
                    puesto = true;
                    tablero[posicion[1]] = IA
                    buttons[posicion[1]].setBackgroundResource(R.drawable.ia_icon);
                    break;

                    //CON LA SEGUNDA Y TERCERA COMO JUGADOR
                }else if(tablero[posicion[1]] == tablero[posicion[2]] &&
                    tablero[posicion[1]] == IA &&
                    tablero[posicion[0]] == 0
                ){
                    puesto = true;
                    tablero[posicion[0]] = IA
                    buttons[posicion[0]].setBackgroundResource(R.drawable.ia_icon);
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
                    buttons[posicion[2]].setBackgroundResource(R.drawable.ia_icon);
                    break;

                    //CON LA PRIMERA Y TERCERA COMO JUGADOR
                }else if(tablero[posicion[0]] == tablero[posicion[2]] &&
                    tablero[posicion[0]] == PLAYER &&
                    tablero[posicion[1]] == 0
                ){
                    puesto = true;
                    tablero[posicion[1]] = IA
                    buttons[posicion[1]].setBackgroundResource(R.drawable.ia_icon);
                    break;


                    //CON LA SEGUNDA Y TERCERA COMO JUGADOR
                }else if(tablero[posicion[1]] == tablero[posicion[2]] &&
                    tablero[posicion[1]] == PLAYER &&
                    tablero[posicion[0]] == 0
                ){
                    puesto = true;
                    tablero[posicion[0]] = IA
                    buttons[posicion[0]].setBackgroundResource(R.drawable.ia_icon);
                    break;

                }
            }
        }


        if(primer_turno){
            if(tablero[4] == PLAYER){
                val esquinas = arrayOf(0,2,6,8);
                while(!puesto){
                    val r = Random.nextInt(0,4);
                    Log.i("bucle",r.toString() + " __ " + esquinas[r])
                    tablero[esquinas[r]] = IA;
                    buttons[esquinas[r]].setBackgroundResource(R.drawable.ia_icon);
                    puesto = true;
                    primer_turno = false;
                    viewModel.primerturno(false);
                    break;
                }
            }else{
                tablero[4] = IA;
                buttons[4].setBackgroundResource(R.drawable.ia_icon);
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
                    buttons[r].setBackgroundResource(R.drawable.ia_icon);
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






