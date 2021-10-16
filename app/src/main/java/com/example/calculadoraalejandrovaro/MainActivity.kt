package com.example.calculadoraalejandrovaro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import org.mariuszgromada.math.mxparser.Expression
import java.lang.Exception
import java.text.DecimalFormat
import kotlin.math.exp
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val input = findViewById<TextView>(R.id.input)
        val output = findViewById<TextView>(R.id.output)

        val clearButton = findViewById<Button>(R.id.buttonClear)
        clearButton.setOnClickListener{
            input.text = ""
            output.text = ""
        }

        val bracketLeftButton = findViewById<Button>(R.id.buttonBracketLeft)
        bracketLeftButton.setOnClickListener{
            input.text = addToInputText("(")
        }

        val bracketRightButton = findViewById<Button>(R.id.buttonBracketRight)
        bracketRightButton.setOnClickListener{
            input.text = addToInputText(")")
        }

        val ceroButton = findViewById<Button>(R.id.buttonCero)
        ceroButton.setOnClickListener{
            input.text = addToInputText("0")
        }

        val oneButton = findViewById<Button>(R.id.buttonOne)
        oneButton.setOnClickListener{
            input.text = addToInputText("1")
        }

        val twoButton = findViewById<Button>(R.id.buttonTwo)
        twoButton.setOnClickListener{
            input.text = addToInputText("2")
        }

        val threeButton = findViewById<Button>(R.id.buttonThree)
        threeButton.setOnClickListener{
            input.text = addToInputText("3")
        }

        val fourButton = findViewById<Button>(R.id.buttonFour)
        fourButton.setOnClickListener{
            input.text = addToInputText("4")
        }

        val fiveButton = findViewById<Button>(R.id.buttonFive)
        fiveButton.setOnClickListener{
            input.text = addToInputText("5")
        }

        val sixButton = findViewById<Button>(R.id.buttonSix)
        sixButton.setOnClickListener{
            input.text = addToInputText("6")
        }

        val sevenButton = findViewById<Button>(R.id.buttonSeven)
        sevenButton.setOnClickListener{
            input.text = addToInputText("7")
        }

        val eightButton = findViewById<Button>(R.id.buttonEight)
        eightButton.setOnClickListener{
            input.text = addToInputText("8")
        }

        val nineButton = findViewById<Button>(R.id.buttonNine)
        nineButton.setOnClickListener{
            input.text = addToInputText("9")
        }

        val dotButton = findViewById<Button>(R.id.buttonDot)
        dotButton.setOnClickListener{
            input.text = addToInputText(".")
        }

        val divisionButton = findViewById<Button>(R.id.buttonDivision)
        divisionButton.setOnClickListener{
            input.text = addToInputText("÷")
        }

        val multiplyButton = findViewById<Button>(R.id.buttonMultiply)
        multiplyButton.setOnClickListener{
            input.text = addToInputText("x")
        }

        val sumaButton = findViewById<Button>(R.id.buttonPlus)
        sumaButton.setOnClickListener{
            input.text = addToInputText("+")
        }

        val restaButton = findViewById<Button>(R.id.buttonMinus)
        restaButton.setOnClickListener{
            input.text = addToInputText("-")
        }

        val equalButton = findViewById<Button>(R.id.buttonEqual)
        equalButton.setOnClickListener{
            showResult()
        }

        val binaryButton = findViewById<Button>(R.id.buttonBinario)
        binaryButton.setOnClickListener{
            showBinary()
        }

        val hexButton = findViewById<Button>(R.id.buttonHexadecimal)
        hexButton.setOnClickListener{
            showHex()
        }

        val decButton = findViewById<Button>(R.id.buttonDecimal)
        decButton.setOnClickListener{
            showResult()
        }
    }

    private fun addToInputText(buttonValue: String): String{
        val input = findViewById<TextView>(R.id.input)
        return "${input.text}$buttonValue"
    }

    private fun getInputExpression(): String{
        val input = findViewById<TextView>(R.id.input)
        var expression = input.text.replace(Regex("÷"), "/")
        expression = expression.replace(Regex("x"),"*")
        return expression
    }

    private fun showResult(){
        try {
            val expression = getInputExpression()
            val result = Expression(expression).calculate()
            if(result.isNaN()){
                val output = findViewById<TextView>(R.id.output)
                output.text = "Error"
                output.setTextColor(ContextCompat.getColor(this,R.color.red))
            }else{
                val output = findViewById<TextView>(R.id.output)
                output.text = DecimalFormat("0.######").format(result).toString()
                output.setTextColor(ContextCompat.getColor(this,R.color.green))
            }
        }catch (e: Exception){
            val output = findViewById<TextView>(R.id.output)
            output.text = "Error"
            output.setTextColor(ContextCompat.getColor(this,R.color.red))
        }
    }

    private fun showBinary(){
        try {
            val expressionBin = getInputExpression()
            val resultBin = Expression(expressionBin).calculate()
            val resultBinInt = resultBin.toInt()

            if (resultBin.isNaN()){
                val outputBin = findViewById<TextView>(R.id.output)
                outputBin.text = "Error"
                outputBin.setTextColor(ContextCompat.getColor(this,R.color.red))
            }else{
                val outputBin = findViewById<TextView>(R.id.output)
                outputBin.text = calculateBin(resultBinInt)
                outputBin.setTextColor(ContextCompat.getColor(this,R.color.green))
            }
        }catch (e: Exception){
            val outputBin = findViewById<TextView>(R.id.output)
            outputBin.text = "Error"
            outputBin.setTextColor(ContextCompat.getColor(this,R.color.red))
        }
    }

    private fun calculateBin(decimalNumber:Int): String {
        var decimalNumber = decimalNumber
        val binaryStr = StringBuilder()

        while (decimalNumber > 0) {
            val r = decimalNumber % 2
            decimalNumber /= 2
            binaryStr.append(r)
        }

        return binaryStr.reverse().toString()
    }

    private fun showHex(){
        try {
            val expressionHex = getInputExpression()
            val resultHex = Expression(expressionHex).calculate()
            val resultHexInt = resultHex.toInt()

            if (resultHex.isNaN()){
                val outputHex = findViewById<TextView>(R.id.output)
                outputHex.text = "Error"
                outputHex.setTextColor(ContextCompat.getColor(this,R.color.red))
            }else{
                val outputHex = findViewById<TextView>(R.id.output)
                outputHex.text = Integer.toHexString(resultHexInt)
                outputHex.setTextColor(ContextCompat.getColor(this,R.color.green))
            }
        }catch (e: Exception){
            val outputHex = findViewById<TextView>(R.id.output)
            outputHex.text = "Error"
            outputHex.setTextColor(ContextCompat.getColor(this,R.color.red))
        }
    }
}