package com.shyam.contractorcalculator

import android.icu.text.NumberFormat
import android.icu.util.Currency
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var laboramt: EditText? =null;
    var materialamt: EditText? =null;
    var calculate: Button? = null;
    var subtotalamt: TextView? =null;
    var totalamt: TextView? =null;
    var taxamt: TextView? =null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        laboramt=  findViewById(R.id.editTextLabor);
        materialamt=  findViewById(R.id.editTextMaterials);
        calculate = findViewById(R.id.button) as Button;
        totalamt =  findViewById(R.id.textViewTotalVal);
        subtotalamt =  findViewById(R.id.textViewSubTotalVal);
        taxamt =  findViewById(R.id.textViewTaxVal);


        calculate!!.setOnClickListener{ calculateTotal(calculate!!) }
    }

    fun calculateTotal(button: Button) {
        var lbramt = if(laboramt!!.text.isNotEmpty()){laboramt!!.text.toString()}else{""}
        var lbrcostamt : Double = if(lbramt.isNotEmpty()){lbramt.toDouble()}else{0.00}

        var matamt = if(materialamt!!.text.isNotEmpty()){materialamt!!.text.toString()}else{""}
        var matcostamt : Double = if(matamt.isNotEmpty()){matamt.toDouble()}else{0.00}

        var subtotal = lbrcostamt+matcostamt
        var tax = (lbrcostamt+matcostamt)*0.5
        var total = subtotal+tax


        val moneyformat: NumberFormat = NumberFormat.getCurrencyInstance()
        moneyformat.setMaximumFractionDigits(2)
        moneyformat.setCurrency(Currency.getInstance("USD"))

        subtotalamt!!.text = moneyformat.format(subtotal).toString()
        taxamt!!.text = moneyformat.format(tax).toString()
        totalamt!!.text = moneyformat.format(total).toString()
    }
}