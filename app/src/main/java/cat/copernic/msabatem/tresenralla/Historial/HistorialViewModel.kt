package cat.copernic.msabatem.tresenralla

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import cat.copernic.msabatem.tresenralla.Database.TicTacToeDatabaseDao
import kotlinx.coroutines.launch

class HistorialViewModel(
        val database: TicTacToeDatabaseDao,
        application: Application
    ) : AndroidViewModel(application) {
    private var entrada = MutableLiveData<HistorialEntrada?>()
    val entradas = database.getAll()
    var entradasString = Transformations.map(entradas) { entradas ->
        formatEntradas(entradas, application.resources)
    }


    init {

        initializEntrada();

    }



    private fun initializEntrada(){
        viewModelScope.launch {
            entrada.value = getEntradaFromDatabase();
        }
    }

    private suspend fun getEntradaFromDatabase(): HistorialEntrada? {
        var entrada = database.getLast()
        return entrada;
    }

    fun clearAll(){
        viewModelScope.launch {
            clear();
        }
    }

    private suspend fun clear(){
        database.clear();
    }



}