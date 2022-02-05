package es.usj.mastertsa.jveron.ticketsdb.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import es.usj.mastertsa.jveron.ticketsdb.data.NO_USER
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
            if (user != null &&
                (user.name == NO_USER.name ||
                        user.password == NO_USER.password ||
                        user.email == NO_USER.email)) {
                Toast.makeText(context, "Incomplete name, email, or password!", Toast.LENGTH_SHORT).show()
            }
            else {
                val bundle = Bundle()
                bundle.putParcelable(SIGN_UP_KEY, user)
                setFragmentResult(SIGN_UP_REQUEST_KEY, bundle)
                dismiss()
            }
        }

        binding.signUpButton.setOnClickListener {
            val signUpFragment = SignUpFragment()
            signUpFragment.show(childFragmentManager, SIGN_UP_TAG)
        }
    }

    private fun getData() : User {
        val rawEmail = binding.etEmail.text
        var email: String = NO_USER.email
        if (rawEmail != null && rawEmail.toString() != "") {
            email = rawEmail.toString()
        }

        val rawPassword = binding.etPassword.text
        var password: String = NO_USER.password
        if (rawPassword != null && rawPassword.toString() != "") {
            password = rawPassword.toString()
        }

        return User(
            email = email,
            password = password,
            name = System.currentTimeMillis().toString())
    }

}