package com.example.calcultortecson


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.annotation.SuppressLint

class MainActivity : AppCompatActivity()
{

    //    Calling all variables related to the application (buttons, screen, input, operators)

    private lateinit var tvDisplay: TextView
    private lateinit var buttonAC: Button
    private lateinit var buttonPlusMinus: Button
    private lateinit var buttonPercent: Button
    private lateinit var buttonDivide: Button
    private lateinit var buttonMultiply: Button
    private lateinit var buttonMinus: Button
    private lateinit var buttonPlus: Button
    private lateinit var buttonEquals: Button
    private lateinit var button0: Button
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button
    private lateinit var button7: Button
    private lateinit var button8: Button
    private lateinit var button9: Button
    private lateinit var buttonDot: Button
    private var currentInput = ""
    private var previousInput = ""
    private var operator = ""

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //    Declaring every UI element to add functionality.

        tvDisplay = findViewById(R.id.tvDisplay)
        buttonAC = findViewById(R.id.buttonAC)
        buttonPlusMinus = findViewById(R.id.buttonPlusMinus)
        buttonPercent = findViewById(R.id.buttonPercent)
        buttonDivide = findViewById(R.id.buttonDivide)
        buttonMultiply = findViewById(R.id.buttonMultiply)
        buttonMinus = findViewById(R.id.buttonMinus)
        buttonPlus = findViewById(R.id.buttonPlus)
        buttonEquals = findViewById(R.id.buttonEquals)
        button0 = findViewById(R.id.button0)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)
        button7 = findViewById(R.id.button7)
        button8 = findViewById(R.id.button8)
        button9 = findViewById(R.id.button9)
        buttonDot = findViewById(R.id.buttonDot)

        //    Setting up listeners to give functionality on adding numbers per button, operator functionality, and computation listeners.

        setNumberListeners()
        setOperatorListeners()
        setSpecialFunctionListeners()
    }
    @SuppressLint("SetTextI18n")
    private fun setNumberListeners()
    {
        //    Calls all number buttons to add functionality

        val numberButtons = listOf(button0, button1, button2, button3, button4, button5, button6, button7, button8, button9)
        for (button in numberButtons)
        {
            //    Sets all buttons into their own number using the button value and show up on the display.
            button.setOnClickListener {
                val value = (it as Button).text.toString()
                currentInput += value

                //    Triggers an if-else statement if an operator will be added on the current input in order for it to show up to the display.

                if (operator.isNotEmpty())
                {
                    tvDisplay.text = "$previousInput $operator $currentInput"
                }
                else
                {
                    tvDisplay.text = currentInput
                }
            }
        }
        //   Adds the same functionality for the decimal point and adds the same if-else statement for the operators.
        buttonDot.setOnClickListener {
            if (!currentInput.contains("."))
            {
                currentInput += "."
                if (operator.isNotEmpty())
                {
                    tvDisplay.text = "$previousInput $operator $currentInput"
                }
                else
                {
                    tvDisplay.text = currentInput
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setOperatorListeners()
    {
        //    Calling all variables related to operators. Uses a for loop to listen if an operator is pressed.
        val operatorButtons = listOf(buttonPlus, buttonMinus, buttonMultiply, buttonDivide)
        for (button in operatorButtons)

        //    Checks if the user has pressed an operator. If the user has pressed one, the variable is stored as previousInput.
        {
            button.setOnClickListener {
                if (currentInput.isNotEmpty())
                {
                    previousInput = currentInput
                    operator = (it as Button).text.toString()

                    //    Resets current input back to nothing in order to for variable declaration for operators.

                    currentInput = ""
                    tvDisplay.text = "$previousInput $operator"
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setSpecialFunctionListeners()
    {
        //    Adds all functionality for the operators, allowing the numbers to be added, subtracted, multiplied, divided, percentage and switch from positive to negative as well as the equals functionality.
        buttonPlusMinus.setOnClickListener {
            if (currentInput.isNotEmpty())
            {
                currentInput = if (currentInput.startsWith("-"))
                {
                    currentInput.substring(1)
                }
                else
                {
                    "-$currentInput"
                }
                tvDisplay.text = if (operator.isNotEmpty()) {
                    "$previousInput $operator $currentInput"
                }
                else
                {
                    currentInput
                }
            }
        }
        //    Adds functionality on percentage button by dividing the current input by 100
        buttonPercent.setOnClickListener {
            if (currentInput.isNotEmpty())
            {
                val percentage = (currentInput.toDoubleOrNull() ?: 0.0) / 100
                currentInput = percentage.toString()
                tvDisplay.text = if (operator.isNotEmpty())
                {
                    "$previousInput $operator $currentInput"
                }
                else
                {
                    currentInput
                }
            }
        }
        //    Adds functionality on the AC/Reset button by setting the display back to 0.
        buttonAC.setOnClickListener {
            currentInput = ""
            previousInput = ""
            operator = ""
            tvDisplay.text = "0"
        }
        //   Adds the equals functionality and uses an if-else statement to check for which operator was used on which and displays the final answer on the screen, removing the previous input and the used operator along the process.
        buttonEquals.setOnClickListener {
            if (currentInput.isNotEmpty() && previousInput.isNotEmpty())
            {
                val result = when (operator)
                {
                    "+" -> previousInput.toDouble() + currentInput.toDouble()
                    "-" -> previousInput.toDouble() - currentInput.toDouble()
                    "×" -> previousInput.toDouble() * currentInput.toDouble()
                    "÷" -> previousInput.toDouble() / currentInput.toDouble()
                    else -> return@setOnClickListener
                }
                tvDisplay.text = result.toString()
                currentInput = result.toString()
                previousInput = ""
                operator = ""
            }
        }
    }
}