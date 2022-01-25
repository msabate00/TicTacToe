package cat.copernic.msabatem.tresenralla.Corrutina_login

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import cat.copernic.msabatem.tresenralla.R
import cat.copernic.msabatem.tresenralla.databinding.CorrutinaLoginFragmentBinding

class Corrutina_LoginFragment : Fragment() {

    companion object {
        fun newInstance() = Corrutina_LoginFragment()
    }

    private lateinit var viewModel: CorrutinaLoginViewModel
    private lateinit var binding: CorrutinaLoginFragmentBinding;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.corrutina__login_fragment,
            container,
            false
        )
        viewModel = ViewModelProvider(this)[CorrutinaLoginViewModel::class.java];

        viewModel.loginResult.observe(viewLifecycleOwner, Observer {
            success -> toast(if (success) "Success" else "Failure")
        })

        binding.loginSubmit.setOnClickListener {

            viewModel.onSubmitClick(binding.loginUsername.toString(), binding.loginPassword.toString())
         }



        return binding.root;

        //return inflater.inflate(R.layout.corrutina__login_fragment, container, false)
    }


    private fun toast(message: String){
        Toast.makeText(context, "message", Toast.LENGTH_SHORT).show()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}