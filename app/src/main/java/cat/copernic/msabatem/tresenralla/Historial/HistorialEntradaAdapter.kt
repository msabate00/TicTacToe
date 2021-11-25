package cat.copernic.msabatem.tresenralla.Historial

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.msabatem.tresenralla.HistorialEntrada
import cat.copernic.msabatem.tresenralla.R
import cat.copernic.msabatem.tresenralla.TextItemViewHolder

class HistorialEntradaAdapter: RecyclerView.Adapter<TextItemViewHolder>()  {

    var data = listOf<HistorialEntrada>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size;

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        //holder.textView.text = item.tiempo.toString();
        holder.textView.text = "A";
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.text_item_view, parent, false) as TextView
        return TextItemViewHolder(view)

    }


}