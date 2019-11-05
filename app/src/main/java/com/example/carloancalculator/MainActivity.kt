package com.example.carloancalculator

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.LocaleList
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.ConfigurationCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCalculate.setOnClickListener {
            calculateLoan(it)
        }
    }

    private fun calculateLoan(view:View){
        val locale = Locale.getDefault()
        val current = Currency.getInstance(locale).getSymbol()
        val inputmethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        Toast.makeText(this, "Calculate Loan", Toast.LENGTH_SHORT).show()
        if(editTextCarPrice.text.isEmpty()) {
            editTextCarPrice.setError(getString(R.string.error_input))
            editTextCarPrice.setHintTextColor(Color.RED)
            inputmethodManager.hideSoftInputFromWindow(view.windowToken,0)
            return
        }
        val downPayment = editTextDownPayment.text.toString().toInt()
        val carPrice = editTextCarPrice.text.toString().toInt()
        val loanPeriod = editTextLoanPeriod.text.toString().toInt()
        val interestRate = editTextInterestRate.text.toString().toFloat()

            //textViewCarLoan.setText(getString(R.string.loan)+"${loan}")
            textViewCarLoan.setText(getString(R.string.car_loan)+current+" "+"${(String.format("%.2f",(carPrice-downPayment).toFloat()))}")
            textViewInterest.setText(getString(R.string.interest)+current+" "+String.format("%.2f",((carPrice-downPayment)*interestRate*loanPeriod).toFloat()))
            textViewMonthlyRepayment.setText(getString(R.string.monthly_repayment)+current+" "+String.format("%.2f",(((carPrice-downPayment)+((carPrice-downPayment)*interestRate*loanPeriod))/loanPeriod/12).toFloat()))

            inputmethodManager.hideSoftInputFromWindow(view.windowToken,0)
    }

    fun reset(view: View){
        editTextCarPrice.setText("")
        editTextDownPayment.setText("")
        editTextInterestRate.setText("")
        editTextLoanPeriod.setText("")
        textViewCarLoan.setText(R.string.car_loan)
        textViewInterest.setText(R.string.interest)
        textViewMonthlyRepayment.setText(R.string.monthly_repayment)
    }
}
