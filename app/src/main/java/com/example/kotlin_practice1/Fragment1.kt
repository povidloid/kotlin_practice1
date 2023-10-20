package com.example.kotlin_practice1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.kotlin_practice1.databinding.Fragment1Binding
import org.koin.androidx.viewmodel.ext.android.viewModel

class Fragment1 : Fragment() {
    private lateinit var binding: Fragment1Binding
    private val vModel : MyViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Fragment1Binding.inflate(layoutInflater)

        binding.buttonFr1Continue.setOnClickListener {
            val str = binding.editTextText.text.toString()
            if (str.isEmpty())
                Toast.makeText(context, "Enter your nickname", Toast.LENGTH_SHORT).show()
            else {
                vModel.setLogin(str)
                Log.d("MyApp", vModel.getLogin().toString())
                try {
                } catch (e: Exception){
                    Log.d("myApp", e.toString())}
                findNavController().navigate(R.id.action_fragment1_to_fragment2)
            }
        }
        return binding.root
    }
}

