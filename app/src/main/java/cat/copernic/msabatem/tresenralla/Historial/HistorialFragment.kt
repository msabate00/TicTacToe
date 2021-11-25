package cat.copernic.msabatem.tresenralla.Historial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import cat.copernic.msabatem.tresenralla.Database.TicTacToeDatabase
import cat.copernic.msabatem.tresenralla.HistorialViewModel
import cat.copernic.msabatem.tresenralla.HistorialViewModelFactory
import cat.copernic.msabatem.tresenralla.R
import cat.copernic.msabatem.tresenralla.databinding.FragmentHistorialBinding


class HistorialFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentHistorialBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_historial,
            container,
            false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = TicTacToeDatabase.getInstance(application).ticTacToeDatabaseDao
        val viewModelFactory = HistorialViewModelFactory(dataSource, application)
        val viewModel =
            ViewModelProvider(
                this, viewModelFactory).get(HistorialViewModel::class.java);


        binding.viewModel = viewModel;
        binding.lifecycleOwner = viewLifecycleOwner;


        val adapter = HistorialEntradaAdapter()
        binding.entradaList.adapter = adapter;




        viewModel.entradas.observe(viewLifecycleOwner, {
            it?.let {
                adapter.data = it;
            }
        })


        return binding.root

    }

    companion object {

    }
}