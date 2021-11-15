package cat.copernic.msabatem.tresenralla

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class EndViewModelFactory(private val time: Long) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EndViewModel::class.java)) {
            return EndViewModel(time) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}