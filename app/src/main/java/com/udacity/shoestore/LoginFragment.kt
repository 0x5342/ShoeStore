package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding

/**
 * Fragment where user logs in
 */
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )
        binding.createNewLoginButton.setOnClickListener { v: View ->
            v.findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToWelcomeScreenFragment()
            )
        }
        binding.existingLoginButton.setOnClickListener { v: View ->
            v.findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToWelcomeScreenFragment()
            )
        }
        return binding.root
    }
}