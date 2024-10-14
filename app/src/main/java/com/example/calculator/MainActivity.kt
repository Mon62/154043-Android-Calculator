package com.example.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.Locale

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var resultTextView: TextView
    private lateinit var calculationTextView: TextView

    private var inputState: Int = 1
    private var operation: Int = 1
    private var firstOperand: Int = 0
    private var secondOperand: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        resultTextView = findViewById(R.id.text_result)
        calculationTextView = findViewById(R.id.text_calculation)
        calculationTextView.text = ""

        findViewById<Button>(R.id.button_bs).setOnClickListener(this)
        findViewById<Button>(R.id.button_c).setOnClickListener(this)
        findViewById<Button>(R.id.button_ce).setOnClickListener(this)
        findViewById<Button>(R.id.button_equal).setOnClickListener(this)
        findViewById<Button>(R.id.button_0).setOnClickListener(this)
        findViewById<Button>(R.id.button_1).setOnClickListener(this)
        findViewById<Button>(R.id.button_2).setOnClickListener(this)
        findViewById<Button>(R.id.button_3).setOnClickListener(this)
        findViewById<Button>(R.id.button_4).setOnClickListener(this)
        findViewById<Button>(R.id.button_5).setOnClickListener(this)
        findViewById<Button>(R.id.button_6).setOnClickListener(this)
        findViewById<Button>(R.id.button_7).setOnClickListener(this)
        findViewById<Button>(R.id.button_8).setOnClickListener(this)
        findViewById<Button>(R.id.button_9).setOnClickListener(this)
        findViewById<Button>(R.id.button_add).setOnClickListener(this)
        findViewById<Button>(R.id.button_subtract).setOnClickListener(this)
        findViewById<Button>(R.id.button_multiply).setOnClickListener(this)
        findViewById<Button>(R.id.button_divide).setOnClickListener(this)
        findViewById<Button>(R.id.button_sign).setOnClickListener(this)
        findViewById<Button>(R.id.button_point).setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(view: View?) {
        val viewId = view?.id
        when (viewId) {
            R.id.button_0 -> addDigit(0)
            R.id.button_1 -> addDigit(1)
            R.id.button_2 -> addDigit(2)
            R.id.button_3 -> addDigit(3)
            R.id.button_4 -> addDigit(4)
            R.id.button_5 -> addDigit(5)
            R.id.button_6 -> addDigit(6)
            R.id.button_7 -> addDigit(7)
            R.id.button_8 -> addDigit(8)
            R.id.button_9 -> addDigit(9)

            R.id.button_bs -> {
                when (inputState) {
                    1 -> {
                        firstOperand /= 10
                        resultTextView.text = formatResult(firstOperand)
                    }

                    2 -> {
                        secondOperand /= 10
                        resultTextView.text = formatResult(secondOperand)
                    }

                    3 -> {
                        calculationTextView.text = ""
                    }
                }
            }

            R.id.button_c -> {
                inputState = 1
                firstOperand = 0
                secondOperand = 0
                resultTextView.text = "0"
                calculationTextView.text = ""
            }

            R.id.button_ce -> {
                when (inputState) {
                    1 -> {
                        firstOperand = 0
                        resultTextView.text = "0"

                    }

                    2 -> {
                        secondOperand = 0
                        resultTextView.text = "0"
                    }

                    3 -> {
                        inputState = 1
                        firstOperand = resultTextView.text.toString().toInt()
                        secondOperand = 0
                        calculationTextView.text = ""
                    }
                }
            }

            R.id.button_add -> {
                when (inputState) {
                    1 -> {
                        calculationTextView.text = formatResult(firstOperand) + " +"

                    }

                    2 -> {
                        firstOperand = calculate()
                        calculationTextView.text = formatResult(firstOperand) + " +"
                        secondOperand = 0
                        resultTextView.text = "0"

                    }

                    3 -> {
                        firstOperand = resultTextView.text.toString().toInt()
                        secondOperand = 0
                        resultTextView.text = "0"
                        calculationTextView.text = formatResult(firstOperand) + " +"
                    }
                }

                inputState = 2
                operation = 1
                secondOperand = 0
                resultTextView.text = "0"

            }

            R.id.button_subtract -> {
                when (inputState) {
                    1 -> {
                        calculationTextView.text = formatResult(firstOperand) + " -"

                    }

                    2 -> {
                        firstOperand = calculate()
                        calculationTextView.text = formatResult(firstOperand) + " -"
                        secondOperand = 0
                        resultTextView.text = "0"
                    }

                    3 -> {
                        firstOperand = resultTextView.text.toString().toInt()
                        secondOperand = 0
                        resultTextView.text = "0"
                        calculationTextView.text = formatResult(firstOperand) + " -"
                    }
                }
                operation = 2
                inputState = 2
                secondOperand = 0
                resultTextView.text = "0"
            }

            R.id.button_multiply -> {
                when (inputState) {
                    1 -> {
                        calculationTextView.text =
                            formatResult(firstOperand) + " *"

                    }

                    2 -> {
                        firstOperand = calculate()
                        calculationTextView.text = formatResult(firstOperand) + " *"
                        secondOperand = 0
                        resultTextView.text = "0"

                    }

                    3 -> {
                        firstOperand = resultTextView.text.toString().toInt()
                        secondOperand = 0
                        resultTextView.text = "0"
                        calculationTextView.text = formatResult(firstOperand) + " /"
                    }
                }
                operation = 3
                inputState = 2
                secondOperand = 0
                resultTextView.text = "0"

            }

            R.id.button_divide -> {
                when (inputState) {
                    1 -> {
                        calculationTextView.text = formatResult(firstOperand) + " /"

                    }

                    2 -> {
                        if (secondOperand != 0) {
                            firstOperand = calculate()
                            calculationTextView.text =
                                formatResult(firstOperand) + " /"
                            secondOperand = 0
                            resultTextView.text = "0"
                        }


                    }

                    3 -> {
                        firstOperand = resultTextView.text.toString().toInt()
                        secondOperand = 0
                        resultTextView.text = "0"
                        calculationTextView.text = formatResult(firstOperand) + " /"
                    }
                }

                operation = 4
                inputState = 2
                secondOperand = 0
                resultTextView.text = "0"
            }

            R.id.button_sign -> {
                when (inputState) {
                    1 -> {
                        firstOperand = -firstOperand
                        resultTextView.text = formatResult(firstOperand)
                    }

                    2 -> {
                        secondOperand = -secondOperand
                        resultTextView.text = formatResult(secondOperand)
                    }

                    3 -> {
                        inputState = 1
                        calculationTextView.text = ""
                        firstOperand = -resultTextView.text.toString().toInt()
                        resultTextView.text = formatResult(firstOperand)
                    }
                }

            }

            R.id.button_equal -> {
                if (inputState != 2) return

                val operator = checkOperator()
                val calculationResult = calculate()

                inputState = 3
                resultTextView.text = formatResult(calculationResult)
                calculationTextView.text =
                    formatResult(firstOperand) + " " + operator + " " + formatResult(secondOperand) + " = " + formatResult(
                        calculationResult
                    )
            }
        }

    }

    private fun addDigit(digit: Int) {
        when (inputState) {
            1 -> {
                firstOperand = firstOperand * 10 + digit
                resultTextView.text = formatResult(firstOperand)
            }

            2 -> {
                secondOperand = secondOperand * 10 + digit
                resultTextView.text = formatResult(secondOperand)
            }

            3 -> {
                firstOperand = digit
                secondOperand = 0
                resultTextView.text = formatResult(firstOperand)
                inputState = 1
            }
        }


    }

    private fun formatResult(result: Int): String {
        return String.format(Locale.getDefault(), "%,d", result)
    }

    private fun checkOperator(): String {
        var operator = "+"
        when (operation) {
            2 -> operator = "-"
            3 -> operator = "*"
            4 -> operator = "/"
        }

        return operator
    }

    private fun calculate(): Int {
        var calculationResult = 0
        when (operation) {
            1 -> calculationResult = firstOperand + secondOperand
            2 -> calculationResult = firstOperand - secondOperand
            3 -> calculationResult = firstOperand * secondOperand
            4 -> {
                if (secondOperand != 0)
                    calculationResult = firstOperand / secondOperand

            }
        }

        return calculationResult
    }
}
