package cat.copernic.msabatem.tresenralla

import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class EndViewModel(time: Long): ViewModel() {
    private val _tiempo = MutableLiveData<Long>(time);
    val tiempo: LiveData<Long> = _tiempo;

    val currentTimeString = Transformations.map(tiempo){ time ->
        DateUtils.formatElapsedTime(time);
    }

}