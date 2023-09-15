package com.example.kotlin_practice1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_practice1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var vModel: viewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vModel = ViewModelProvider(this)[viewModel::class.java]
        vModel.getLogin().observe(this) { newLogin ->
            binding.login.text = newLogin
        }
        vModel.getPassword().observe(this) { newPassword ->
            binding.password.text = newPassword
        }
        vModel.getInfo().observe(this) { newInfo ->
            binding.info.text = newInfo
        }
    }
}