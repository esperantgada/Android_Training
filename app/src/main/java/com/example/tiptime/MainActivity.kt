package com.example.tiptime

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }

        // Set up a key listener on the EditText field to listen for "enter" button presses
        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(
                view,
                keyCode
            )
        }
    }

    private fun calculateTip() {
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        val cost = stringInTextField.toDoubleOrNull()

        when (binding.tipOptions.checkedRadioButtonId) {
            R.id.twenty -> {
                showResult(0.20)}

            R.id.eighteen -> {
                showResult(0.18)}

            else -> {
                showResult(0.15)
            }
        }

        /**If cost is null or 0,display 0 and leave the method without executing the other instructions**/

        if (cost == null || cost == 0.0) {
            displayResult(0.0)
            return
        }

    }

    private fun checkAmount() {
        if (binding.tipResult.text.isNotEmpty()){
            binding.tipResult.text = ""
        }
    }

    private fun showResult(d: Double){
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        var result = d * cost!!
        val roundUp = binding.roundUpSwitch.isChecked

        /**If switch is turned on, perform this action**/
        if (roundUp) {
            result = kotlin.math.ceil(result)
        }

        displayResult(result)
    }

    fun displayResult(result: Double) {
        /**Format the result**/
        val formattedResult = NumberFormat.getCurrencyInstance().format(result)
        binding.tipResult.text = getString(R.string.tip_amount, formattedResult)
    }

    /**Hide the keyboard**/
    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}