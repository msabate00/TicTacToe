package cat.copernic.msabatem.tresenralla

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
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
        viewModelFactory = EndViewModelFactory(time);
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(EndViewModel::class.java)

        binding.viewModel = viewModel;
        binding.lifecycleOwner = viewLifecycleOwner;

        return binding.root

    }



    companion object {


    }
}