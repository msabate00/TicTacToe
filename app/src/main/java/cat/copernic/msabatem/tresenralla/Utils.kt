package cat.copernic.msabatem.tresenralla

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import java.text.SimpleDateFormat


@SuppressLint("SimpleDateFormat")
fun convertLongToDateString(systemTime: Long): String {
    return SimpleDateFormat("EEEE MMM-dd-yyyy' Time: 'HH:mm")
        .format(systemTime).toString()
}


fun convertNumericWinnerToDrawableWinner(ganador: Int, resources: Resources): Drawable {
    var icono = resources.getDrawable(R.drawable.notime_icon);
    when (ganador) {
        0 -> icono = resources.getDrawable(R.drawable.notime_icon);
        1 -> icono = resources.getDrawable(R.drawable.player_icon);
        2 -> icono = resources.getDrawable(R.drawable.ia_icon);
        3 -> icono = resources.getDrawable(R.drawable.draw_icon);

    }
    return icono;
}
fun convertNumericWinnerToString(ganador: Int, resources: Resources): String{

    return when(ganador){
        0 -> resources.getString(R.string.ganador_TIMEOUT);
        1 -> resources.getString(R.string.ganador_PLAYER);
        2 -> resources.getString(R.string.ganador_IA);
        3 -> resources.getString(R.string.ganador_DRAW);

        else -> resources.getString(R.string.ganador_TIMEOUT);
    }

}





fun formatEntradas(nights: List<HistorialEntrada>, resources: Resources): Spanned {
    val sb = StringBuilder()
    sb.apply {
        append(resources.getString(R.string.titulo_entradas))
        nights.forEach {
            append("<br>")
            append(resources.getString(R.string.tiempo))
            //append("\t${convertLongToDateString(it.tiempo)}<br>")
            append("00:${it.tiempo} <br>")
            //if (it.endTimeMilli != it.startTimeMilli) {
                append(resources.getString(R.string.ganador))

                //append("${it.ganador}")
                append("\t${convertNumericWinnerToString(it.ganador, resources)}<br><br>")
                //append("<img src=\t${convertNumericWinnerToDrawableWinner(it.ganador, resources)}<br>")
                //append(resources.getString(R.string.hours_slept))
                // Hours
                //append("\t ${it.endTimeMilli.minus(it.startTimeMilli) / 1000 / 60 / 60}:")
                // Minutes
                //append("${it.endTimeMilli.minus(it.startTimeMilli) / 1000 / 60}:")
                // Seconds
                //append("${it.endTimeMilli.minus(it.startTimeMilli) / 1000}<br><br>")
            //}
        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}