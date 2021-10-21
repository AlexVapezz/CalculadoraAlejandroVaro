package com.example.calculadoraalejandrovaro

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
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

        val aButton = findViewById<Button>(R.id.buttonA)
        aButton.setOnClickListener{
            input.text = addToInputText("A")
        }

        val bButton = findViewById<Button>(R.id.buttonB)
        bButton.setOnClickListener{
            input.text = addToInputText("B")
        }
        val cButton = findViewById<Button>(R.id.buttonC)
        cButton.setOnClickListener{
            input.text = addToInputText("C")
        }
        val dButton = findViewById<Button>(R.id.buttonD)
        dButton.setOnClickListener{
            input.text = addToInputText("D")
        }
        val eButton = findViewById<Button>(R.id.buttonE)
        eButton.setOnClickListener{
            input.text = addToInputText("E")
        }
        val fButton = findViewById<Button>(R.id.buttonF)
        fButton.setOnClickListener{
            input.text = addToInputText("F")
        }

        val equalButton = findViewById<Button>(R.id.buttonEqual)
        equalButton.setOnClickListener{
            showResult()
        }

        val binaryButton = findViewById<Button>(R.id.buttonBinario)
        binaryButton.setOnClickListener{
            buttonBinario.setBackgroundColor(Color.parseColor("#008000"))
            buttonHexadecimal.setBackgroundColor(Color.parseColor("#ff9900"))
            buttonDecimal.setBackgroundColor(Color.parseColor("#ff9900"))
            showBinary()
        }

        val hexButton = findViewById<Button>(R.id.buttonHexadecimal)
        hexButton.setOnClickListener{
            buttonHexadecimal.setBackgroundColor(Color.parseColor("#008000"))
            buttonBinario.setBackgroundColor(Color.parseColor("#ff9900"))
            buttonDecimal.setBackgroundColor(Color.parseColor("#ff9900"))
            showHex()
        }

        val decButton = findViewById<Button>(R.id.buttonDecimal)
        decButton.setOnClickListener{
            buttonDecimal.setBackgroundColor(Color.parseColor("#008000"))
            buttonHexadecimal.setBackgroundColor(Color.parseColor("#ff9900"))
            buttonBinario.setBackgroundColor(Color.parseColor("#ff9900"))
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
        expression = expression.replace(Regex("A"),"10")
        expression = expression.replace(Regex("B"),"11")
        expression = expression.replace(Regex("C"),"12")
        expression = expression.replace(Regex("D"),"13")
        expression = expression.replace(Regex("E"),"14")
        expression = expression.replace(Regex("F"),"15")
        return expression
    }

    private fun showResult(){

        buttonA.visibility = View.GONE
        buttonB.visibility = View.GONE
        buttonC.visibility = View.GONE
        buttonD.visibility = View.GONE
        buttonE.visibility = View.GONE
        buttonF.visibility = View.GONE
        buttonTwo.isEnabled = true;
        buttonThree.isEnabled = true;
        buttonFour.isEnabled = true;
        buttonFive.isEnabled = true;
        buttonSix.isEnabled = true;
        buttonSeven.isEnabled = true;
        buttonEight.isEnabled = true;
        buttonNine.isEnabled = true;
        buttonDot.isEnabled = true;
        buttonEqual.isEnabled = true;

        try {
            val expression = getInputExpression()
            val result = Expression(expression).calculate()
            if(result.isNaN()){
                val output = findViewById<TextView>(R.id.output)
                output.text = ""
                output.setTextColor(ContextCompat.getColor(this,R.color.red))
            }else{
                val output = findViewById<TextView>(R.id.output)
                output.text = DecimalFormat("0.######").format(result).toString()
                output.setTextColor(ContextCompat.getColor(this,R.color.green))
            }
        }catch (e: Exception){
            val output = findViewById<TextView>(R.id.output)
            output.text = ""
            output.setTextColor(ContextCompat.getColor(this,R.color.red))
        }
    }

    private fun showBinary(){

        buttonA.visibility = View.GONE
        buttonB.visibility = View.GONE
        buttonC.visibility = View.GONE
        buttonD.visibility = View.GONE
        buttonE.visibility = View.GONE
        buttonF.visibility = View.GONE
        buttonTwo.isEnabled = false;
        buttonThree.isEnabled = false;
        buttonFour.isEnabled = false;
        buttonFive.isEnabled = false;
        buttonSix.isEnabled = false;
        buttonSeven.isEnabled = false;
        buttonEight.isEnabled = false;
        buttonNine.isEnabled = false;
        buttonDot.isEnabled = false;
        buttonEqual.isEnabled = false;

        try {
            val expressionBin = getInputExpression()
            val resultBin = Expression(expressionBin).calculate()
            val resultBinInt = resultBin.toInt()

            if (resultBin.isNaN()){
                val outputBin = findViewById<TextView>(R.id.output)
                outputBin.text = ""
                outputBin.setTextColor(ContextCompat.getColor(this,R.color.red))
            }else{
                val outputBin = findViewById<TextView>(R.id.output)
                outputBin.text = calculateBin(resultBinInt)
                outputBin.setTextColor(ContextCompat.getColor(this,R.color.green))
            }
        }catch (e: Exception){
            val outputBin = findViewById<TextView>(R.id.output)
            outputBin.text = ""
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

        buttonA.visibility = View.VISIBLE
        buttonB.visibility = View.VISIBLE
        buttonC.visibility = View.VISIBLE
        buttonD.visibility = View.VISIBLE
        buttonE.visibility = View.VISIBLE
        buttonF.visibility = View.VISIBLE
        buttonEqual.isEnabled = false;
        buttonDot.isEnabled = false;
        buttonTwo.isEnabled = true;
        buttonThree.isEnabled = true;
        buttonFour.isEnabled = true;
        buttonFive.isEnabled = true;
        buttonSix.isEnabled = true;
        buttonSeven.isEnabled = true;
        buttonEight.isEnabled = true;
        buttonNine.isEnabled = true;

        try {
            val expressionHex = getInputExpression()
            val resultHex = Expression(expressionHex).calculate()
            val resultHexInt = resultHex.toInt()

            if (resultHex.isNaN()){
                val outputHex = findViewById<TextView>(R.id.output)
                outputHex.text = ""
                outputHex.setTextColor(ContextCompat.getColor(this,R.color.red))
            }else{
                val outputHex = findViewById<TextView>(R.id.output)
                outputHex.text = Integer.toHexString(resultHexInt)
                outputHex.setTextColor(ContextCompat.getColor(this,R.color.green))
            }
        }catch (e: Exception){
            val outputHex = findViewById<TextView>(R.id.output)
            outputHex.text = ""
            outputHex.setTextColor(ContextCompat.getColor(this,R.color.red))
        }
    }
}