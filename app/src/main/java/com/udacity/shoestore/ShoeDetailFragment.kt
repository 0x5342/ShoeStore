package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding

class ShoeDetailFragment : Fragment() {

    private lateinit var viewModel: SharedViewModel
    private lateinit var binding: FragmentShoeDetailBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_shoe_detail,
                container,
                false
        )

        // Get the viewModel
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        // Set the ViewModel for DataBinding to allow the bound layout access to all of the data
        // in the ViewModel
        binding.sharedViewModel = viewModel

        // Specify the current activity as the lifecycle owner of the binding. This is used so that
        // the binding can observe LiveData updates
        binding.lifecycleOwner = this

        // Set up event listener to know when to navigate back to the shoe list
        viewModel.eventSaveOrCancel.observe(viewLifecycleOwner, Observer { saveOrCancelTriggered ->
            if (saveOrCancelTriggered) {
                findNavController().navigate(
                    ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment()
                )
                viewModel.eventSaveOrCancelComplete()
            }
        })

        // Start with a blank New Shoe class
        viewModel.createBlankNewShoe()

        return binding.root
    }
}