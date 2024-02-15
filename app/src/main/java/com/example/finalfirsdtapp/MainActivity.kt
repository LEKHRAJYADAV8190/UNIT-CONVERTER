package com.example.finalfirsdtapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finalfirsdtapp.ui.theme.FinalFirsdtAppTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinalFirsdtAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    unitconverter()

                }
            }
        }
    }
}

@Composable
fun unitconverter(){
    var inputvalue by remember{ mutableStateOf("") }
    var outputvalue by remember{ mutableStateOf("") }
    var inputunit by remember { mutableStateOf("Metre") }
    var outputunit by remember{ mutableStateOf("Metre") }
    var iexpand by remember{ mutableStateOf(false) }
    var oexpand by remember{ mutableStateOf(false) }
    var iconversionfactor= remember {
        mutableStateOf(1.00) }
    var oconversionfactor = remember {
        mutableStateOf(1.00) }
    var searchingunit by remember{ mutableStateOf(inputvalue)}

    fun unitconverterfirst(){
        val inputvaluedouble=inputvalue.toDoubleOrNull()?:0.0
        val result=(inputvaluedouble * iconversionfactor.value*100.0/oconversionfactor.value).roundToInt()/100.0
        outputvalue=result.toString()
    }





    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement=Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Text("UNIT CONVERTER",style = MaterialTheme.typography.bodyMedium.copy(fontSize = 32.sp),
            color = Color.Green)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value =inputvalue, onValueChange ={
            inputvalue=it
            unitconverterfirst()


        },
            label={ Text(text = "input your value")})
        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Box(){
                Button(onClick = { iexpand=true}) {
                    Text(text = inputunit,style = MaterialTheme.typography.bodyMedium.copy(fontSize = 20.sp),
                        color = Color.Black)
                    Icon(Icons.Default.ArrowDropDown, contentDescription ="Arrow down" )
                }
                DropdownMenu(expanded =iexpand, onDismissRequest = { iexpand=false }) {
                    OutlinedTextField(value =searchingunit, onValueChange ={searchingunit=it})
                    DropdownMenuItem(text = {Text("Centimetre",style = MaterialTheme.typography.bodyMedium.copy(fontSize = 20.sp),
                        color = Color.Green) }, onClick = {
                        iexpand=false
                        inputunit="Centimetre"
                        iconversionfactor.value=0.01
                        unitconverterfirst()


                    }
                    )
                    DropdownMenuItem(text = {Text("Metre ",style = MaterialTheme.typography.bodyMedium.copy(fontSize = 20.sp),
                        color = Color.Green)}, onClick = {
                        iexpand=false
                        inputunit="Metre"
                        iconversionfactor.value=1.0

                    })
                    DropdownMenuItem(text = {Text("Milemetre",style = MaterialTheme.typography.bodyMedium.copy(fontSize = 20.sp),
                        color = Color.Green) }, onClick = {
                        iexpand=false
                        inputunit="Milemetre" +
                                ""
                        iconversionfactor.value=0.001
                        unitconverterfirst()
                    })
                }



            }
            Spacer(modifier = Modifier.width(16.dp))

            Box(){
                Button(onClick = {oexpand=true}) {
                    Text(text = outputunit,style = MaterialTheme.typography.bodyMedium.copy(fontSize = 20.sp),
                        color = Color.Black)
                    Icon(Icons.Default.ArrowDropDown, contentDescription ="Arrow down" )
                    DropdownMenu(expanded =oexpand, onDismissRequest = { oexpand=false}) {
                        DropdownMenuItem(text = {Text("Centimetre",style = MaterialTheme.typography.bodyMedium.copy(fontSize = 20.sp),
                            color = Color.Green) }, onClick = {
                            oexpand=false
                            outputunit="Centimetre"
                            oconversionfactor.value=0.01
                            unitconverterfirst()

                        })
                        DropdownMenuItem(text = {Text("Metre",style = MaterialTheme.typography.bodyMedium.copy(fontSize = 20.sp),
                            color = Color.Green) }, onClick = {
                            oexpand=false
                            outputunit="Metre"
                            oconversionfactor.value=1.00
                            unitconverterfirst()

                        })
                        DropdownMenuItem(text = {Text("Milemetre",style = MaterialTheme.typography.bodyMedium.copy(fontSize = 20.sp),
                            color = Color.Green) }, onClick = {
                            oexpand=false
                            outputunit="Milemetre"
                            oconversionfactor.value=0.001
                            unitconverterfirst()})
                    }



                }

            }

        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Result: ${outputvalue}:${outputunit}",
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 20.sp),
            color = Color.Green

             )

    }

}



@Preview(showBackground = true)
@Composable
fun unitconverterPreview(){
    unitconverter()
}