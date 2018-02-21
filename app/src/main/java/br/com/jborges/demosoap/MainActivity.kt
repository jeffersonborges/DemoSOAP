package br.com.jborges.demosoap

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

private val url = "http://pa02micro42:8080/CalculadoraWSService/CalculadoraWS?wsdl"
private val namespace = "http://heiderlopes.com.br/"
private val methodName = "calcular"
private val soapAction = namespace + methodName
private val parametro1 = "num1"
private val parametro2 = "num2"
private val parametro3 = "op"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    inner class CallWebService: AsyncTask<String, Void, String> {
        override fun doInBackground(vararg params: String?): String {
           
    }
}
