package com.dicoding.naufal.mybackgroundthread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.dicoding.naufal.mybackgroundthread.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        lifecycleScope.launch(Dispatchers.Default){
            for (i in 0..10){
                delay(500)
                val percentage = i * 10
                withContext(Dispatchers.Main){
                    if (percentage == 100){
                        binding.tvStatus.setText(R.string.task_completed)
                    }else{
                        binding.tvStatus.text = String.format(getString(R.string.compressing), percentage)
                    }
                }
            }
        }

    }
}