package com.luka.vokabeltrainer

import android.R.attr.height
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.luka.vokabeltrainer.ui.theme.VokabelTrainerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VokabelTrainerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(
    modifier: Modifier = Modifier
) {

    var currentScreen by remember { mutableStateOf(1) }

    when (currentScreen) {

        0 -> {
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = "Vokabel Trainer",
                    fontSize = 40.sp

                )

                Spacer(modifier = Modifier.height(40.dp))

                Button(
                    onClick = {
                        currentScreen = 1
                    },
                    modifier = Modifier
                        .height(100.dp)
                        .width(250.dp)
                ){
                    Text(
                        text = "Vokabeln",
                        fontSize = 35.sp
                    )
                }
            }

        }
        1 ->{
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Vokabeln",
                    fontSize = 40.sp
                )

                LazyColumn(
                    modifier = Modifier.fillMaxHeight(0.7f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                }
            }
        }
    }



}
