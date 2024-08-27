package com.example.portafolio

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.portafolio.imcalculator.ImcCalculatorActivity

class menuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

    val btnImc = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btnImc)
    btnImc.setOnClickListener{ navigateToImc()}
    }

    private fun navigateToImc(){
        val intent = Intent(this, ImcCalculatorActivity::class.java)
        startActivity(intent)
    }

}