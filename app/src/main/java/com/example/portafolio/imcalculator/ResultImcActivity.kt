package com.example.portafolio.imcalculator

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.portafolio.R
import com.example.portafolio.imcalculator.ImcCalculatorActivity.Companion.IMC_KEY

class ResultImcActivity : AppCompatActivity() {
    //componentes con sus valores inciales

    // lateinit para todos los componentes que iran cambiando de valor conforme la aplicación se ejecute
    // estos componentes no llevan ningun valor asignado

   private lateinit var rangoValor: TextView
   private lateinit var valor: TextView
   private lateinit var info : TextView
   private lateinit var btnRecalcular : AppCompatButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imc)
        val result:Double =  intent.extras?.getDouble(IMC_KEY)?: -1.0 //repasar esta función, guarda la info anteior el valor resul es un Int
        initComponent()
        initListener()
        initUI(result)
    }

    private fun initComponent() {
        rangoValor = findViewById(R.id.rangoValor)
        valor = findViewById(R.id.valor)
        info = findViewById(R.id.info)
        btnRecalcular = findViewById(R.id.btnRecalcular)
    }

    private fun initListener() {
       btnRecalcular.setOnClickListener {
           val intent = Intent(this, ImcCalculatorActivity::class.java)
           startActivity(intent)
       }
    }

    private fun initUI(result: Double) {
       //esta función actualiza pasa a los componentes todos los valores finales, ya tengo valores finales solo de un componente: resultado
        //me faltaría entonces relacion este valor final con la modificación de dos text view, acá habrían dos funciones que reciben
        //como parámetro el result y cambian su información en base a ella
        valor.text = result.toString()
        when(result){
            in 0.00..18.50->{
                rangoValor.text = getString(R.string.title_bajoPeso)
                info.text = getString(R.string.info_bajoPeso)
            }

            in 18.50..24.90 ->{
                rangoValor.text = getString(R.string.title_pesoNormal)
                info.text = getString(R.string.info_pesoNormal)

            }
            in 25.00..29.00 ->{
                rangoValor.text = getString(R.string.title_sobrePeso)
                info.text = getString(R.string.info_sobrePeso)

            }
            else ->{
                rangoValor.text = getString(R.string.title_error)
                info.text = getString(R.string.info_error)

            }

        }
    }








}