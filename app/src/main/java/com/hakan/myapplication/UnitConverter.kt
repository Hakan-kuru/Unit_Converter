package com.hakan.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hakan.myapplication.ui.theme.MyApplicationTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}
@Composable
fun UnitConverter(){
        var inputValue  by remember { mutableStateOf("") }
        var outputValue by remember { mutableStateOf("") }
        var inputUnit   by remember { mutableStateOf("") }
        var outputUnit  by remember { mutableStateOf("") }
        var iExpentent  by remember { mutableStateOf(false) }
        var oExpentent  by remember { mutableStateOf(false) }
        val conversionFactor  = remember { mutableStateOf(1.0) }
        val oConversionFactor = remember { mutableStateOf(1.0) }
    fun convertUnits(){

        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble*conversionFactor.value / oConversionFactor.value)
        outputValue = result.toString()
    }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Text("Unit Converter",style = MaterialTheme.typography.headlineLarge)
        Spacer(
            modifier = Modifier.height(16.dp)
        )
            OutlinedTextField(value = inputValue, onValueChange = {
                inputValue=it
                convertUnits()
            },
            label ={Text(text = "enter...")}
            )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            //Input box
            Box{
                //Input button
                Button(onClick = { iExpentent = true}) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow down")
                }//Output button
                DropdownMenu(expanded = iExpentent//menü başta kapalı
                    ,onDismissRequest = { iExpentent =false }) {
                    DropdownMenuItem(text = { Text(text = "mil") },
                        onClick = {
                            iExpentent =false
                            inputUnit = "mil"
                            conversionFactor.value = 1609.3
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text(text = "metre") },
                        onClick = {
                            iExpentent =false
                            inputUnit = "metre"
                            conversionFactor.value = 1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text(text = "feet") },
                        onClick = {
                            iExpentent =false
                            inputUnit = "feet"
                            conversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text( "santimetre") },
                        onClick = {
                            iExpentent = false
                            inputUnit = "santimetre"
                            conversionFactor.value =0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text("milimetre") },
                        onClick = {
                            iExpentent= false
                            inputUnit ="milimetre"
                            conversionFactor.value =0.001
                            convertUnits()
                        }
                    )
                }
            }//Output box
            Spacer(modifier = Modifier.width(16.dp))
            //Input box
            Box{
                //Input button
                Button(onClick = {oExpentent=true }) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow down")
                }//Output button
                DropdownMenu(expanded = oExpentent//menü başta kapalı
                    ,onDismissRequest = { oExpentent =false }) {
                    DropdownMenuItem(text = { Text(text = "mil") },
                        onClick = {
                            iExpentent =false
                            inputUnit = "mil"
                            conversionFactor.value = 1609.3
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text(text = "metre") },
                        onClick = {
                            oExpentent = false
                            outputUnit = "metre"
                            oConversionFactor.value = 1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text(text = "feet") },
                        onClick = {
                            oExpentent =false
                            outputUnit = "feet"
                            oConversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text( "santimetre") },
                        onClick = {
                            oExpentent = false
                            outputUnit = "santimetre"
                            oConversionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text("milimetre") },
                        onClick = {
                            oExpentent = false
                            outputUnit = "milimetre"
                            oConversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                }
            }//Output box
        }
        Spacer(modifier = Modifier.height(16.dp))
            //result text
        Text("result: $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium)
    }
}
@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}