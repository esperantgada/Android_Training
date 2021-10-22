package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip(){
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()

        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.twenty -> 0.20
            R.id.eighteen -> 0.18
            else -> 0.15
        }

        if (cost == null || cost == 0.0){
            //binding.tipResult.text = ""
            displayResult(0.0)
            return    /**If cost is null or 0,display 0 and leave the method without executing the other instructions**/
        }


        var result = tipPercentage * cost
        val roundUp = binding.roundUpSwitch.isChecked /**Return a Boolean after checking the state of switch**/
        //result = ceil(result) /**Conversion of the result**/

        /**If switch is turned on, perform this action**/
        if (roundUp){
            result = ceil(result)
        }

        displayResult(result)
    }

    /**Add this in string resource: <string name="tip_amount">Tip Amount: %s</string>**/

     fun displayResult(result: Double) {
        /**Format the result**/
        val formattedResult = NumberFormat.getCurrencyInstance().format(result)
        binding.tipResult.text = getString(R.string.tip_amount, formattedResult)
    }
}