package com.example.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class CalculatorFragment : Fragment() {
    private lateinit var expressionTextView: TextView
    private lateinit var resultTextView: TextView

    private var operand1 = 0.0
    private var operand2 = 0.0
    private var operator: Char = ' '
    private var result: Double = 0.0

    private var expressionFullString: String = ""
    private var currentInput: String = ""
    private var isCalculationPerformed: Boolean = false

    private var operators: Array<Char> = arrayOf('+', '-', '*', '/')

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)

        expressionTextView = view.findViewById(R.id.expressionTextView)
        resultTextView = view.findViewById(R.id.resultTextView)

        // Set click listeners for number buttons
        val numberButtons = arrayOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )

        for (buttonId in numberButtons) {
            view.findViewById<Button>(buttonId).setOnClickListener { numberOnClick(it) }
        }

        // Set click listeners for other buttons
        view.findViewById<Button>(R.id.btnClear).setOnClickListener { clearOnClick() }
        view.findViewById<Button>(R.id.btnPositiveNegative).setOnClickListener { positiveNegativeOnClick() }
        view.findViewById<Button>(R.id.btnDot).setOnClickListener { numberOnClick(it) }
        view.findViewById<Button>(R.id.btnMultiply).setOnClickListener { operatorOnClick(it) }
        view.findViewById<Button>(R.id.btnDivide).setOnClickListener { operatorOnClick(it) }
        view.findViewById<Button>(R.id.btnPlus).setOnClickListener { operatorOnClick(it) }
        view.findViewById<Button>(R.id.btnMinus).setOnClickListener { operatorOnClick(it) }
        view.findViewById<Button>(R.id.btnBackspace).setOnClickListener { backspaceOnClick() }
        view.findViewById<Button>(R.id.btnPercent).setOnClickListener { percentOnClick() }
        view.findViewById<Button>(R.id.btnEqual).setOnClickListener { equalOnClick() }

        return view
    }

    private fun numberOnClick(view: View) {
        if (view is Button) {
            if (isCalculationPerformed) {
                // Clear the expression and result TextViews if a calculation was just performed
                expressionFullString = ""
                resultTextView.text = ""
                currentInput = ""
                isCalculationPerformed = false
            }

            val clickedNumber = view.text.toString()
            currentInput += clickedNumber
            updateExpressionTextView()
        }
    }

    private fun clearOnClick() {
        expressionFullString = ""
        currentInput = ""
        operand1 = 0.0
        operand2 = 0.0
        operator = ' '
        result = 0.0
        updateExpressionTextView()
        resultTextView.text = ""
    }

    private fun positiveNegativeOnClick() {
        if (currentInput.isNotEmpty() && currentInput.toDouble() != 0.0) {
            currentInput = (-currentInput.toDouble()).toString()
            updateExpressionTextView()
        }
    }

    private fun operatorOnClick(view: View) {
        if (currentInput.isNotEmpty()) {
            operand1 = currentInput.toDouble()
            operator = (view as Button).text.toString()[0]

            if (isCalculationPerformed) {
                expressionFullString = ""
                resultTextView.text = ""
                isCalculationPerformed = false
            }

            expressionFullString = "$operand1 $operator "
            currentInput = ""
            updateExpressionTextView()
        }
    }

    private fun backspaceOnClick() {
        if (currentInput.isNotEmpty()) {
            currentInput = currentInput.substring(0, currentInput.length - 1)
            updateExpressionTextView()
        }
    }

    private fun percentOnClick() {
        if (currentInput.isNotEmpty()) {
            val percentValue = currentInput.toDouble() / 100
            currentInput = percentValue.toString()
            updateExpressionTextView()
        }
    }

    private fun equalOnClick() {
        if (currentInput.isNotEmpty() && operator != ' ') {
            operand2 = currentInput.toDouble()
            result = performCalculation()
            expressionFullString = "$operand1 $operator $operand2 = "
            currentInput = result.toString()
            updateExpressionTextView()
            updateResultTextView()
            isCalculationPerformed = true
            operator = ' '
        }
    }

    private fun performCalculation(): Double {
        return when (operator) {
            '+' -> operand1 + operand2
            '-' -> operand1 - operand2
            '*' -> operand1 * operand2
            '/' -> operand1 / operand2
            else -> 0.0
        }
    }

    private fun updateExpressionTextView() {
        expressionTextView.text = expressionFullString + currentInput
    }

    private fun updateResultTextView() {
        resultTextView.text = result.toString()
    }
}
