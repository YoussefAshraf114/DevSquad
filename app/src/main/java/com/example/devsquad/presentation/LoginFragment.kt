package com.example.devsquad.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.devsquad.R
import com.example.devsquad.databinding.FragmentLoginBinding
import com.example.devsquad.presentation.viewmodels.AuthViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : Fragment() {
    val authViewModel by lazy {
        ViewModelProvider(this)[AuthViewModel::class.java]
    }

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.signUpNav.isClickable = true
        binding.signUpNav.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .add(
                    R.id.fragment_container,
                    RegisterFragment(),
                    "")
                .addToBackStack(null)
                .commit()
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}