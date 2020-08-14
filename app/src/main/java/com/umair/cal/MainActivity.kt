package com.umair.cal

import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var previousTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        previousTextView = findViewById(R.id.previousTextView)

        //Numbers

        oneTextView.setOnClickListener {
            putOnPreviousView("1", true)
            vibrate()
        }
        twoTextView.setOnClickListener {
            putOnPreviousView("2", true)
            vibrate()
        }
        threeTextView.setOnClickListener {
            putOnPreviousView("3", true)
            vibrate()
        }
        fourTextView.setOnClickListener {
            putOnPreviousView("4", true)
            vibrate()
        }
        fiveTextView.setOnClickListener {
            putOnPreviousView("5", true)
            vibrate()
        }
        sixTextView.setOnClickListener {
            putOnPreviousView("6", true)
            vibrate()
        }
        sevenTextView.setOnClickListener {
            putOnPreviousView("7", true)
            vibrate()
        }
        eightTextView.setOnClickListener {
            putOnPreviousView("8", true)
            vibrate()
        }
        nineTextView.setOnClickListener {
            putOnPreviousView("9", true)
            vibrate()
        }
        zeroTextView.setOnClickListener {
            putOnPreviousView("0", true)
            vibrate()
        }
        pointTextView.setOnClickListener {
            putOnPreviousView(".", true)
            vibrate()
        }

        //Operators

        plusTextView.setOnClickListener {
            operator('+')
            vibrate()
        }
        minusTextView.setOnClickListener {
            operator('-')
            vibrate()
        }
        multiplyTextView.setOnClickListener {
            operator('*')
            vibrate()
        }
        divideTextView.setOnClickListener {
            operator('/')
            vibrate()
        }
        percentageTextView.setOnClickListener {
            operator('%')
            vibrate()
        }


        ccTextView.setOnClickListener {
            previousTextView.text = ""
            resultTextView.text = ""
            vibrate()
        }

        clearTextView.setOnClickListener {
            val string = previousTextView.text.toString()
            vibrate()
            if (string.isNotEmpty()) {
                previousTextView.text = string.substring(0, string.length - 1)
            }
            resultTextView.text = ""
        }

        equalsTextView.setOnClickListener {
            vibrate()
            try {

                val expression = ExpressionBuilder(previousTextView.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble())
                    resultTextView.text = longResult.toString()
                else
                    resultTextView.text = result.toString()

            } catch (e: Exception) {
                Log.d("Exception", " message : " + e.message)
            }
        }
    }

    // Method to put on operators

    private fun operator(op : Char)
    {
        if (previousTextView.text.isEmpty())
            previousTextView.text = "0"
        else if (op != previousTextView.text.last()) {
            val last = previousTextView.text.lastIndex
            if (previousTextView.text[last] in '0'..'9')  putOnPreviousView(op.toString(),false)
            else {
                previousTextView.text = previousTextView.text.dropLast(1)
                putOnPreviousView(op.toString(),false)
            }
        }
    }

    // Method to put button on vibrate when clicked

    private fun vibrate() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(30)
    }

    private fun putOnPreviousView(string: String, canClear: Boolean) {

        if (resultTextView.text.isNotEmpty()) {
            previousTextView.text = ""
        }

        if (canClear) {
            resultTextView.text = ""
            previousTextView.append(string)
        } else {
            previousTextView.append(resultTextView.text)
            previousTextView.append(string)
            resultTextView.text = ""
        }
    }
}