package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentShoeListBinding

class ShoeListFragment : Fragment() {

    private lateinit var viewModel: SharedViewModel
    private lateinit var binding: FragmentShoeListBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_shoe_list,
                container,
                false
        )

        // Get the viewModel
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        // Create an observer of the shoe list to update the UI
        viewModel.shoes.observe(viewLifecycleOwner, Observer { shoeList ->
            val parent = binding.shoeListLayout
            val lp = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            lp.setMargins(16,8,16,8)
            parent.removeAllViews()
            for (shoe in shoeList) {
                val tv = TextView(activity)
                tv.layoutParams = lp
                tv.text = shoe.toString()
                binding.shoeListLayout.addView(tv)
            }
        })

        binding.addShoeFab.setOnClickListener { v: View ->
            v.findNavController().navigate(
            ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.log_out -> requireView().findNavController().navigate(
                ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment()
            )
        }
        return super.onOptionsItemSelected(item)
    }
}