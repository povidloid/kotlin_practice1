package com.example.kotlin_practice1

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.kotlin_practice1.databinding.ActivitySecondBinding
import com.example.kotlin_practice1.databinding.ActivityThirdBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    private lateinit var file: File
    private lateinit var folder: File
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonDownload.setOnClickListener {
            val imageUrl = binding.editTT.text.toString()

            try {
                val path = this.getExternalFilesDir(null)
                folder = File(path, "photos")
                folder.mkdirs()
            }catch(e: Exception){
                Log.e("MyApp", e.toString())
            }

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val url = URL(imageUrl)
                    val connection = url.openConnection()
                    connection.doInput = true
                    connection.connect()
                    val input = connection.getInputStream()
                    val bitmap = BitmapFactory.decodeStream(input)

                    val fileName = "my_image.jpg"
                    file = File(folder, fileName)
                    file.createNewFile()
                    val fileOutputStream = FileOutputStream(file)
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
                    fileOutputStream.flush()
                    fileOutputStream.close()

                    runOnUiThread { Toast.makeText(applicationContext, "Yepi", Toast.LENGTH_SHORT).show() }

                } catch (e: Exception) {
                    Log.e("MyApp", e.toString())
                }
            }
        }
    }
}
