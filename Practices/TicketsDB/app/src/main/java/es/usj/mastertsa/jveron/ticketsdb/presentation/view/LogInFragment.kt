package es.usj.mastertsa.jveron.ticketsdb.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import es.usj.mastertsa.jveron.ticketsdb.databinding.FragmentLogInBinding
import es.usj.mastertsa.jveron.ticketsdb.domain.model.User

const val LOG_IN_TAG = "LogInTag"
const val LOG_IN_REQUEST_KEY = "LogInRequestKey"
const val LOG_IN_KEY = "LogInKey"

class LogInFragment: DialogFragment() {

    var _binding : FragmentLogInBinding? = null
    val binding : FragmentLogInBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logInButton.setOnClickListener {
            val user = getData()
            val bundle = Bundle()
            bundle.putParcelable(LOG_IN_KEY, user)
            setFragmentResult(LOG_IN_REQUEST_KEY, bundle)
            dismiss()
        }

        childFragmentManager.setFragmentResultListener(SIGN_UP_REQUEST_KEY, viewLifecycleOwner) { _, bundle ->
            val user : User? = bundle.getParcelable(SIGN_UP_KEY) as? User
            val bundle = Bundle()
            bundle.putParcelable(SIGN_UP_KEY, user)
            setFragmentResult(SIGN_UP_REQUEST_KEY, bundle)
            dismiss()
        }

        binding.signUpButton.setOnClickListener {
            val signUpFragment = SignUpFragment()
            signUpFragment.show(childFragmentManager, SIGN_UP_TAG)
        }
    }

    private fun getData() : User {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        return User(
            email = email,
            password = password,
            name = System.currentTimeMillis().toString())
    }

}