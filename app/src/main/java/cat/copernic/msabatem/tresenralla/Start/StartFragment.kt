package cat.copernic.msabatem.tresenralla

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import cat.copernic.msabatem.tresenralla.databinding.FragmentStartBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StartFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentStartBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_start, container, false)
        binding.startButton.setOnClickListener {
            findNavController().navigate(StartFragmentDirections.actionStartFragmentToGameFragment())
            //findNavController().navigate()
        }

        binding.btHistorialparidasstart.setOnClickListener {
            findNavController().navigate(StartFragmentDirections.actionStartFragmentToHistorialFragment());
        }

        binding.settingsButton.setOnClickListener {
            findNavController().navigate(StartFragmentDirections.actionStartFragmentToSettingsFragment())
        }

        return binding.root;
    }




    companion object {

    }
}