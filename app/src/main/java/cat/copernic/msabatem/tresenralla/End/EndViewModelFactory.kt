package cat.copernic.msabatem.tresenralla

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cat.copernic.msabatem.tresenralla.Database.TicTacToeDatabaseDao


class EndViewModelFactory(
    private val dataSource: TicTacToeDatabaseDao,
    private val time: Long,
    private val ganador: Int,
    private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EndViewModel::class.java)) {
            return EndViewModel(dataSource,application,time, ganador) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}