package es.usj.mastertsa.jveron.ticketsdb.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import es.usj.mastertsa.jveron.ticketsdb.data.NO_USER
import es.usj.mastertsa.jveron.ticketsdb.databinding.FragmentSignUpBinding
import es.usj.mastertsa.jveron.ticketsdb.domain.model.User

const val SIGN_UP_TAG = "SignUpTag"
const val SIGN_UP_REQUEST_KEY = "SignUpRequestKey"
const val SIGN_UP_KEY = "SignUpKey"

class SignUpFragment: DialogFragment() {

    var _binding : FragmentSignUpBinding? = null
    val binding : FragmentSignUpBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logInButton.setOnClickListener {
            val newUser = getData()
            val bundle = Bundle()
            bundle.putParcelable(SIGN_UP_KEY, newUser)
            setFragmentResult(SIGN_UP_REQUEST_KEY, bundle)
            dismiss()
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

        val rawName = binding.etUserName.text
        var name: String = NO_USER.name
        if (rawName != null && rawName.toString() != "") {
            name = rawName.toString()
        }

        return User(email = email,
            password = password,
            name = name)
    }

}