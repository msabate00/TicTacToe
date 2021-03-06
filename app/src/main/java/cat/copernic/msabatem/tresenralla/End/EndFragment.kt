package cat.copernic.msabatem.tresenralla

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import cat.copernic.msabatem.tresenralla.Database.TicTacToeDatabase
import cat.copernic.msabatem.tresenralla.databinding.FragmentEndBinding
import cat.copernic.msabatem.tresenralla.databinding.FragmentGameBinding


class EndFragment : Fragment() {

    private lateinit var  viewModel: EndViewModel;
    private lateinit var viewModelFactory: EndViewModelFactory;


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentEndBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_end,
            container,
            false
        )
        val time = EndFragmentArgs.fromBundle(requireArguments()).time;
        val ganador = EndFragmentArgs.fromBundle(requireArguments()).ganador;

        val application = requireNotNull(this.activity).application
        val dataSource = TicTacToeDatabase.getInstance(application).ticTacToeDatabaseDao



        viewModelFactory = EndViewModelFactory(dataSource,time, ganador, application);
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(EndViewModel::class.java)

        binding.viewModel = viewModel;
        binding.lifecycleOwner = viewLifecycleOwner;







        viewModel.eventPlayAgain.observe(viewLifecycleOwner, {
                playAgain -> if(playAgain){
            findNavController().navigate(EndFragmentDirections.actionEndFragmentToGameFragment());
            viewModel.onPlayAgainComplete();
        }
        })

        viewModel.verHistorial.observe(viewLifecycleOwner,{
            ver -> if (ver){
                findNavController().navigate(EndFragmentDirections.actionEndFragmentToHistorialFragment());
                viewModel.onVerHistorialComplete();
            }
        })


        when(viewModel.ganador.value){

            0 -> binding.imageView.setImageResource(R.drawable.notime_icon);
            1 -> binding.imageView.setImageResource(R.drawable.player_icon);
            2 -> binding.imageView.setImageResource(R.drawable.ia_icon);
            3 -> binding.imageView.setImageResource(R.drawable.draw_icon);
            else -> binding.imageView.setImageResource(R.drawable.notime_icon);
        }





        return binding.root

    }



    companion object {


    }
}