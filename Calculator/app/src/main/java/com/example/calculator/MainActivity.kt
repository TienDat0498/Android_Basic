package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener  {
    private lateinit var btn0: Button
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btn4: Button
    private lateinit var btn5: Button
    private lateinit var btn6: Button
    private lateinit var btn7: Button
    private lateinit var btn8: Button
    private lateinit var btn9: Button
    private lateinit var btnEqual: Button
    private lateinit var btnDivide: Button
    private lateinit var btnMulti: Button
    private lateinit var btnMinus: Button
    private lateinit var btnPlus: Button
    private lateinit var btnDot: Button
    private lateinit var btnDelete: Button
    private lateinit var btnClear: Button
    private lateinit var btnAC: Button
    private lateinit var txtView: TextView
    private lateinit var curString: String
    private lateinit var lastString: String
    private lateinit var operator: String
    private var isClickEqual = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

    }

   private fun init() {
        btn0 = findViewById(R.id.btn0)
        btn0.setOnClickListener(this)
        btn1 = findViewById(R.id.btn1)
        btn1.setOnClickListener(this)
        btn2 = findViewById(R.id.btn2)
        btn2.setOnClickListener(this)
        btn3 = findViewById(R.id.btn3)
        btn3.setOnClickListener(this)
        btn4 = findViewById(R.id.btn4)
        btn4.setOnClickListener(this)
        btn5 = findViewById(R.id.btn5)
        btn5.setOnClickListener(this)
        btn6 = findViewById(R.id.btn6)
        btn6.setOnClickListener(this)
        btn7 = findViewById(R.id.btn7)
        btn7.setOnClickListener(this)
        btn8 = findViewById(R.id.btn8)
        btn8.setOnClickListener(this)
        btn9 = findViewById(R.id.btn9)
        btn9.setOnClickListener(this)
        btnAC = findViewById(R.id.btnAC)
        btnAC.setOnClickListener(this)
        btnClear = findViewById(R.id.btnClear)
        btnClear.setOnClickListener(this)
        btnDelete = findViewById(R.id.btnDelete)
        btnDelete.setOnClickListener(this)
        btnPlus = findViewById(R.id.btnPlus)
        btnPlus.setOnClickListener(this)
        btnMinus = findViewById(R.id.btnMinus)
        btnMinus.setOnClickListener(this)
        btnMulti = findViewById(R.id.btnMulti)
        btnMulti.setOnClickListener(this)
        btnDivide = findViewById(R.id.btnDivide)
        btnDivide.setOnClickListener(this)
        btnEqual = findViewById(R.id.btnEqual)
        btnEqual.setOnClickListener(this)
        btnDot = findViewById(R.id.btnDot)
        btnDot.setOnClickListener(this)
        txtView = findViewById(R.id.textView)

        clickAC()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn0 -> clickNumberButton(0)
            R.id.btn1 -> clickNumberButton(1)
            R.id.btn2 -> clickNumberButton(2)
            R.id.btn3 -> clickNumberButton(3)
            R.id.btn4 -> clickNumberButton(4)
            R.id.btn5 -> clickNumberButton(5)
            R.id.btn6 -> clickNumberButton(6)
            R.id.btn7 -> clickNumberButton(7)
            R.id.btn8 -> clickNumberButton(8)
            R.id.btn9 -> clickNumberButton(9)
            R.id.btnPlus -> clickOperator("+")
            R.id.btnMinus -> clickOperator("-")
            R.id.btnMulti -> clickOperator("x")
            R.id.btnDivide -> clickOperator("/")
            R.id.btnEqual -> clickEqual()
            R.id.btnDelete -> clickDelete()
            R.id.btnClear -> clickClear()
            R.id.btnAC -> clickAC()


        }
    }

    private fun clickNumberButton(num: Short){
        if(isClickEqual){
            clickAC()
            isClickEqual=false
        }
        if(!isOutOfBound(parseToLong(curString))) curString+=num.toString()
        showCurrent()
    }

    private fun clickOperator(ope: String){

        if (curString.isNotEmpty() and lastString.isEmpty()){
            lastString = curString
            curString = ""
            operator = ope
        }
        else if(curString.isEmpty() and lastString.isNotEmpty()){
            operator = ope
        }

        else if (curString.isNotEmpty() and lastString.isNotEmpty()){
            clickEqual()
            isClickEqual = false
            lastString = curString
            curString = ""
            operator = ope

        }

    }

    private fun clickEqual(){
        isClickEqual = true
        if (lastString.isNotEmpty() and curString.isEmpty()){
            curString = lastString
            lastString = ""
            operator = ""
        }
        else if(lastString.isNotEmpty() and curString.isNotEmpty()){
            var result: Long = parseToLong(curString)
            when(operator){
                "+" -> result = parseToLong(lastString) + parseToLong(curString)
                "-" -> result = parseToLong(lastString) - parseToLong(curString)
                "x" -> result = parseToLong(lastString) * parseToLong(curString)
                "/" -> result = parseToLong(lastString) / parseToLong(curString)
            }
            curString = result.toString()
            operator = ""
            showCurrent()

        }
    }

    private fun clickDelete(){
        if(curString.length>1) curString = curString.substring(0, curString.length-1)
        else curString = ""
        showCurrent()
    }

    private fun clickClear(){
        curString = ""
        showCurrent()
    }

    private fun clickAC(){
        curString = ""
        lastString = ""
        operator = ""
        showCurrent()
    }

    private fun isOutOfBound(num: Long):Boolean{
        if(num.toString().length>=12) return true
        return false
    }

    private fun showCurrent(){
        curString = parseToLong(curString).toString()
        txtView.text = parseToLong(curString).toString()
    }

    private fun parseToLong(numString: String):Long{
        if(numString.isEmpty()){
            return 0
        }
        return numString.toLong()
    }

}