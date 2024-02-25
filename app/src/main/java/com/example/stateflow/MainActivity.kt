package com.example.stateflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val editValue = findViewById<EditText>(R.id.editValue)
        val resultBtn = findViewById<Button>(R.id.resultBtn)
        val transformationBtn = findViewById<Button>(R.id.transformationBtn)
        val resultText = findViewById<TextView>(R.id.resultText)


        resultBtn.setOnClickListener {
            viewModel.setData(editValue.text.toString().toInt())
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED){
                    viewModel.mutableStateFlow.collect {
                        resultText.text = it.toString()
                    }
                }
            }
        }

        transformationBtn.setOnClickListener {
            viewModel.setData(editValue.text.toString().toInt())
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.transformationStateFlow.collect {
                        resultText.text = it.toString()
                    }
                }
            }
        }




    }
}