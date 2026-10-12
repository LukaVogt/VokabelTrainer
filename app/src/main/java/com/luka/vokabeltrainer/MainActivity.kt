package com.luka.vokabeltrainer

import android.R.attr.height
import android.os.Bundle
import android.provider.CalendarContract
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.scrollableArea
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    var currentScreen by remember { mutableStateOf(0) }
    var frontSide by remember { mutableStateOf("") }
    var backSide by remember { mutableStateOf("") }

    val vocabList = remember { mutableStateListOf(
        Vocabulary("Hallo", "Hello"),
        Vocabulary("Bitte", "Please"),
        Vocabulary("Danke", "Thank you"),
        Vocabulary("Ja", "Yes"),
        Vocabulary("Nein", "No"),
        Vocabulary("Guten Morgen", "Good morning"),
        Vocabulary("Auf Wiedersehen", "Goodbye"),
        Vocabulary("Entschuldigung", "Excuse me"),
        Vocabulary("Wie geht es dir?", "How are you?"),
    ) }

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
                modifier = modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Vokabeln",
                    fontSize = 40.sp
                )

                LazyColumn(
                    modifier = Modifier.weight(0.7f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    items(vocabList) {vocabulary ->
                        var isClicked by rememberSaveable() {mutableStateOf(false)}

                        Card(
                            border = BorderStroke(1.5.dp, Color.Blue),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Cyan
                            ),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 6.dp),
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .height(150.dp)
                                .padding(vertical = 8.dp)
                                .clickable { isClicked = !isClicked}
                        ) {

                            Box(){

                                Button(
                                    onClick = {
                                        vocabList.remove(vocabulary)
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                                ) {
                                    Text(
                                        text = "-",
                                        fontSize = 30.sp
                                    )
                                }
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    if (!isClicked){
                                        Text(
                                            text = vocabulary.frontSide,
                                            fontSize = 35.sp
                                        )
                                    } else{
                                        Text(
                                            text = vocabulary.backSide,
                                            fontSize = 35.sp
                                        )
                                    }

                                }
                            }
                        }

                    }

                }

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = {
                        currentScreen = 2
                    },
                ) {
                    Text(
                        text = "+",
                        fontSize = 50.sp
                    )
                }
            }
        }
        2 ->{
            Column(
                modifier = modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "Kateikarte Hinzufügen",
                    fontSize = 30.sp
                )

                Spacer(modifier = Modifier.height(30.dp))

                OutlinedTextField(
                    value = frontSide,
                    onValueChange = {frontSide = it},
                    label = { Text("Vorderseite") }
                )

                Spacer(modifier = Modifier.height(30.dp))

                OutlinedTextField(
                    value = backSide,
                    onValueChange = {backSide = it},
                    label = { Text("Rückseite") }
                )

                Spacer(modifier = Modifier.height(100.dp))

                Button(
                    onClick = {
                        vocabList.add(Vocabulary(frontSide, backSide))

                        frontSide = ""
                        backSide = ""
                        currentScreen = 1
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                    modifier = Modifier
                        .width(200.dp)
                        .height(75.dp)
                ) {
                    Text(
                        text = "Hinzufügen",
                        fontSize = 20.sp
                    )
                }
            }
        }
    }

}
