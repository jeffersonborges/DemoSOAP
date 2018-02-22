package br.com.jborges.demosoap

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.PropertyInfo
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE

private val url = "http://10.3.2.42:8080/CalculadoraWSService/CalculadoraWS?wsdl"
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
        btSomar.setOnClickListener {
            CallWebService()
                    .execute(etNumero1.text.toString(),
                            etNumero2.text.toString(),
                            "+"
                    )
        }

        btSubtrair.setOnClickListener {
            CallWebService()
                    .execute(etNumero1.text.toString(),
                            etNumero2.text.toString(),
                            "-"
                    )
        }

        btMultiplicar.setOnClickListener {
            CallWebService()
                    .execute(etNumero1.text.toString(),
                            etNumero2.text.toString(),
                            "*"
                    )
        }

        btDividir.setOnClickListener {
            CallWebService()
                    .execute(etNumero1.text.toString(),
                            etNumero2.text.toString(),
                            "/"
                    )
        }
        btZerar.setOnClickListener {
            etNumero1.setText("0")
            etNumero2.setText("0")
            tvResultado.setText("0")
        }
    }

    inner class CallWebService : AsyncTask<String, Void, String>() {

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            tvResultado.text = result
        }

        override fun doInBackground(vararg params: String?): String {
            var result = ""
            val soapObject = SoapObject(namespace, methodName)

            val number1Info = PropertyInfo()
            number1Info.name = parametro1
            number1Info.value = params[0]
            number1Info.type = Integer::class.java

            soapObject.addProperty(number1Info)

            val number2Info = PropertyInfo()
            number2Info.name = parametro2
            number2Info.value = params[1]
            number2Info.type = Integer::class.java

            soapObject.addProperty(number2Info)

            val number3Info = PropertyInfo()
            number3Info.name = parametro3
            number3Info.value = params[2]
            number3Info.type = Integer::class.java

            soapObject.addProperty(number3Info)

            val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
            envelope.setOutputSoapObject(soapObject)

            val httpTransportSE = HttpTransportSE(url)
            try {
                httpTransportSE.call(soapAction, envelope)
                val soapPrimitive = envelope.response
                result = soapPrimitive.toString()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return result
        }


    }
}
