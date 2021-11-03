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
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    val IA = 1;
    val PLAYER = 2;
    var tablero = Array<Int>(9) {0};
    var playerTurno = true;
    var terminado = false;
    var primer_turno = true;

    var combinacionGanadora = arrayOf(
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
        setContentView(R.layout.activity_main)


        findViewById<Button>(R.id.restart).setOnClickListener{
            reset();
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

    fun reset(){
        tablero = Array<Int>(9) {0};
        playerTurno = true;
        terminado = false;
        primer_turno = true;

        for(i in buttonID){
            findViewById<Button>(i).setBackgroundResource(0);
            findViewById<Button>(i).setBackgroundResource(R.drawable.none);
        }

    }

    fun pulsacion(view: View) {


        if (playerTurno) {

            var contador = 0;
            for(i in buttonID){
               // Log.i("Bucle", contador.toString())
                if(i == view.id && tablero[contador] == 0){
                    view.setBackgroundResource(R.drawable.player_icon);
                    tablero[contador] = PLAYER;
                    playerTurno = false;
                    break;
                }

                contador++;
            }


            if(!playerTurno) {
                comprobarGanador();

                if(!terminado){
                   // Log.i("Bucle", "salido")
                    turnoIA();
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
                    break;
                }
            }else{
                tablero[4] = IA;
                findViewById<Button>(buttonID[4]).setBackgroundResource(R.drawable.ia_icon);
                puesto = true;
                primer_turno = false;
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






