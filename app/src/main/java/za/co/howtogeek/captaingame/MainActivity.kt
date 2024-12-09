package za.co.howtogeek.captaingame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import za.co.howtogeek.captaingame.ui.theme.CaptainGameTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CaptainGameTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CaptainGame()
                }
            }
        }
    }

    @Composable
    fun CaptainGame() {
        val treasuresFound = remember { mutableStateOf(0)}
        val direction = remember { mutableStateOf("North") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp) // Add 16dp margin to the top
        ) {
            Text(text = "Treasures Found: ${treasuresFound.value}")
            Text(text = "Current Direction: ${direction.value}")
            Button(onClick = {
                direction.value = "East"
                if (Random.nextBoolean()) {
                    treasuresFound.value += 1
                }
            }){
                Text("Sail East")
            }
            Button(onClick = {
                direction.value = "West"
                if (Random.nextBoolean()) {
                    treasuresFound.value += 1
                }
            }){
                Text("Sail West")
            }
            Button(onClick = {
                direction.value = "North"
                if (Random.nextBoolean()) {
                    treasuresFound.value += 1
                }
            }){
                Text("Sail North")
            }
            Button(onClick = {
                direction.value = "South"
                if (Random.nextBoolean()) {
                    treasuresFound.value += 1
                }
            }){
                Text("Sail South")
            }
        }
    }
}