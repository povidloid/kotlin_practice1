package com.example.kotlin_practice1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.kotlin_practice1.database.MainDB
import com.example.kotlin_practice1.database.UserDB
import com.example.kotlin_practice1.databinding.ActivityMainBinding
import com.example.kotlin_practice1.retrofit.MainAPI
import com.example.kotlin_practice1.retrofit.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val vModel : MyViewModel by viewModel()
    private lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vModel.getLogin().observe(this) { newLogin ->
            binding.login.text = newLogin
        }
        vModel.getPassword().observe(this) { newPassword ->
            binding.password.text = newPassword
        }
        vModel.getEmail().observe(this) { newEmail ->
            binding.email.text = newEmail
        }

        binding.buttonGenerateAcc.setOnClickListener {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://dummyjson.com")
                .addConverterFactory(GsonConverterFactory.create()).build()
            val userAPI = retrofit.create(MainAPI::class.java)
            CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main)
                {
                    user = userAPI.getUserById(Random().nextInt(50) + 1)
                    Log.d("myApp", user.username + " " + user.password + " " + user.email)
                    vModel.setLogin(user.username)
                    vModel.setPassword(user.password)
                    vModel.setEmail(user.email)
                    Toast.makeText(
                        applicationContext,
                        "The account details was successfully generated",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        binding.buttonClean.setOnClickListener {
            cleanVModel()
        }

        Thread{
            this.deleteDatabase("test.db")
        }.start()

        val db = MainDB.getDb(this)

        binding.buttonCreateAcc.setOnClickListener {
            try {
                val user = UserDB(null, binding.login.text.toString(), binding.password.text.toString(), binding.email.text.toString())
                cleanVModel()
                Thread{
                    db.getDao().insertUser(user)
                }.start()
                Toast.makeText(applicationContext, "The account was successfully entered into the database", Toast.LENGTH_SHORT).show()
            } catch (e: Exception){
                Log.e("myApp", e.toString() )
            }
        }

        binding.buttonToDatabase.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }
    private fun cleanVModel(){
        vModel.setLogin("")
        vModel.setPassword("")
        vModel.setEmail("")
    }
}