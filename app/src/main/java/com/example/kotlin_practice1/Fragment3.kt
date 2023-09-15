package com.example.kotlin_practice1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kotlin_practice1.databinding.Fragment3Binding

class Fragment3 : Fragment() {
    private lateinit var binding:Fragment3Binding
    private lateinit var vModel: viewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Fragment3Binding.inflate(layoutInflater)

        binding.buttonThirdFragment.setOnClickListener {
            val info = binding.editTextText2.text.toString()
            if (info.isEmpty())
                Toast.makeText(context, "Enter your info", Toast.LENGTH_SHORT).show()
            else{
                vModel = ViewModelProvider(requireActivity())[viewModel::class.java]
                vModel.setInfo(info)
                findNavController().navigate(R.id.action_fragment3_to_fragment1)
            }
        }
        return binding.root
    }
}