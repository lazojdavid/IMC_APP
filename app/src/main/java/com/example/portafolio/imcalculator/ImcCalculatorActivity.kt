package com.example.portafolio.imcalculator

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.portafolio.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider

class ImcCalculatorActivity : AppCompatActivity() {
    // compontentes de la interfaz con sus valores iniciales

    private var pesoActual: Int = 60
    private var edadActual: Int = 24
    private var alturaActual: Int = 100

    // Los componentes de mi interza que se van a modificar, ya sea con click o con cambios de valores
    // , con su tipo , sintaxis , idComponente:Tipo de componente

    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider
    private lateinit var btnSumarPeso: FloatingActionButton
    private lateinit var btnRestarPeso: FloatingActionButton
    private lateinit var tvPesoValor: TextView
    private lateinit var btnSumarEdad: FloatingActionButton
    private lateinit var btnRestarEdad: FloatingActionButton
    private lateinit var tvEdadValor: TextView
    private lateinit var btnCalcular: AppCompatButton

    companion object{
        const val IMC_KEY = "IMC_RESULT"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calculator)
        initComponent()
        initListeners()
        initUI()
    }

    private fun initComponent() {

        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        btnSumarPeso = findViewById(R.id.btnSumarPeso)
        btnRestarPeso = findViewById(R.id.btnRestarPeso)
        tvPesoValor = findViewById(R.id.tvPesoValor)
        btnSumarEdad = findViewById(R.id.btnSumarEdad)
        btnRestarEdad = findViewById(R.id.btnRestarEdad)
        tvEdadValor = findViewById(R.id.tvEdadValor)
        btnCalcular = findViewById(R.id.btnCalcular)
    }

    private fun initListeners() {
        //son funciones que se ejecutan cada vez que un botón sea presioando
        //estas funciones se ejecutan automáticamente con clada interacción

        rsHeight.addOnChangeListener { _, value, _ ->
            val fvalue = value.toInt()
            alturaActual = fvalue.toString().toInt()
            tvHeight.text = "$alturaActual cm"
        }
        btnSumarPeso.setOnClickListener {
            pesoActual += 1
            setPeso()
        }
        btnRestarPeso.setOnClickListener {
            pesoActual -= 1
            setPeso()
        }
        btnSumarEdad.setOnClickListener {
            edadActual += 1
            setEdad()
        }
        btnRestarEdad.setOnClickListener {
            edadActual -= 1
            setEdad()
        }
        btnCalcular.setOnClickListener {
            val result = calcularIMC() // una vez retornado se queda, acá pero como lo pasó ?
            navigateToResult(result) //después de calcular, se pasa a otra ventana con otra funcion que incluye un intnet
        }
    }

    private fun navigateToResult(result:Double) {
       val intent = Intent(this, ResultImcActivity::class.java)
       intent.putExtra(IMC_KEY, result) //el extra significa que lleva información en este caso proveniendo de la función calcular, resultado
       startActivity(intent) //arranca la otra vista
    }

    private fun setPeso() {
        tvPesoValor.text = pesoActual.toString()
    }

    private fun setEdad(){
        tvEdadValor.text = edadActual.toString()
    }
    private fun initUI() {
        setPeso()
        setEdad()
    }

    private fun calcularIMC(): Double {
        val df = DecimalFormat("#.##")
        val imc = pesoActual / ((alturaActual.toDouble()/100) * (alturaActual.toDouble()/100))
        return df.format(imc).toDouble()


    }
}


