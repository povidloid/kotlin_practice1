package com.example.kotlin_practice1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import com.example.kotlin_practice1.database.MainDB
import com.example.kotlin_practice1.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = MainDB.getDb(this)
        db.getDao().getAllUsers().asLiveData().observe(this){ it->
            if (it.isEmpty()){
                binding.textView.text = "Empty!"
            } else {
                binding.textView.text = ""
                it.forEach {
                    val text = "Id: ${it.id}" +
                            "\nNickname: " +
                            "${it.nickname}" +
                            "\nPassword: " +
                            "${it.password}" +
                            "\nEmail: " +
                            "${it.email}\n\n"
                    binding.textView.append(text)
                }
            }
        }

        binding.buttonReturnToMP.setOnClickListener {
            finish()
        }
    }
}