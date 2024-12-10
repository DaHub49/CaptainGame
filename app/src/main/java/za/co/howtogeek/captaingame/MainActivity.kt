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
import androidx.compose.runtime.getValue
//import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import za.co.howtogeek.captaingame.ui.theme.CaptainGameTheme
import kotlin.random.Random

/*
Question:
Can you assign an initial value to a val and use a delegate

Answer:
No, in Kotlin, you cannot directly assign an initial value to a val or var property and simultaneously use a delegate.
You must choose between either using a delegate or directly assigning a value.
This is because the delegate itself is responsible for managing the property's value, and Kotlin does not allow mixing both approaches.

Why?
When you declare a property with a delegate (by), Kotlin delegates the value-handling logic to the delegate. [NB!!!]

Any initial value must be provided through the delegate itself, not directly assigned to the property.

Example of using a delegate:
kotlin
Copy code
val myProperty: String by lazy { "Initial Value from Delegate" }

fun main() {
    println(myProperty) // Prints: Initial Value from Delegate
}
What happens if you try to assign an initial value and use a delegate?
This would result in a compilation error:

kotlin
Copy code
val myProperty: String = "Initial Value" by lazy { "Value from Delegate" }
// Error: Initializers are not allowed when property has a delegate
If you want to initialize a delegate with a specific value, you can configure the delegate to handle it. For example:

kotlin
Copy code
val myProperty: Int by Delegates.observable(10) { _, old, new ->
    println("Changed from $old to $new")
}

fun main() {
    println(myProperty) // Prints: 10
}
Conclusion
You can’t assign an initial value directly when using a delegate, but you can configure the delegate to provide or manage the initial value.
 */

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
        //val treasuresFound = remember { mutableStateOf(0)}
        // Using the "by" keyword instead of the "=" allows you to call the value inside the val without casting it first.
        // Remember to import State.getValue()
        var treasuresFound by remember { mutableStateOf(0) }

        val direction = remember { mutableStateOf("North") }
        // stormOrTreasure val:
        val stormOrTreasure = remember { mutableStateOf("") }
        // health points (hp) val:
        var healthPoints by remember { mutableStateOf(100) }
        // isEnabled for buttons:
        var isEnabled by remember { mutableStateOf(true) }

        //when

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp) // Add 16dp margin to the top
        ) {
            Text(text = "Treasures Found: ${treasuresFound}")
            Text(text = "Current Direction: ${direction.value}")
            Text(text = "HP: ${healthPoints}")
            //stormOrTreasure Text Composable:
            Text(text = stormOrTreasure.value)
            Text(text = "\n")

            //do {
            Button(
                onClick = {
                    if (healthPoints == 0) {
                        stormOrTreasure.value += "\nGAME OVER!"
                        isEnabled = false // Disable the button after click
                    } else {

                        direction.value = "East"
                        if (Random.nextBoolean()) {
                            treasuresFound += 1
                            stormOrTreasure.value = "Found a Treasure!"
                        } else {
                            stormOrTreasure.value = "Storm Ahead!"
                            healthPoints -= 10
                            if (healthPoints == 0) {
                                stormOrTreasure.value += "\nGAME OVER!"
                                isEnabled = false // Disable the button after click
                            }
                        }
                    }
                },
                enabled = isEnabled // Control enabled state using the state variable
            ){
                Text("Sail East")
            }

            Button(onClick = {
                if (healthPoints == 0) {
                    stormOrTreasure.value += "\nGAME OVER!"
                    isEnabled = false // Disable the button after click
                } else {
                    direction.value = "West"
                    if (Random.nextBoolean()) {
                        treasuresFound += 1
                        stormOrTreasure.value = "Found a Treasure!"
                    } else {
                        stormOrTreasure.value = "Storm Ahead!"
                        healthPoints -= 10
                        if (healthPoints == 0) {
                            stormOrTreasure.value += "\nGAME OVER!"
                            isEnabled = false // Disable the button after click
                        }
                    }
                }
            },
                enabled = isEnabled // Control enabled state using the state variable
            ){
            Text("Sail West")
        }

        Button(onClick = {
            if (healthPoints == 0) {
                stormOrTreasure.value += "\nGAME OVER!"
                isEnabled = false // Disable the button after click
            } else {
                direction.value = "North"
                if (Random.nextBoolean()) {
                    treasuresFound += 1
                    stormOrTreasure.value = "Found a Treasure!"
                } else {
                    stormOrTreasure.value = "Storm Ahead!"
                    healthPoints -= 10
                    if (healthPoints == 0) {
                        stormOrTreasure.value += "\nGAME OVER!"
                        isEnabled = false // Disable the button after click
                    }
                }
            }
        },
            enabled = isEnabled // Control enabled state using the state variable
        ){
            Text("Sail North")
        }

        Button(onClick = {
            if (healthPoints == 0) {
                stormOrTreasure.value += "\nGAME OVER!"
                isEnabled = false // Disable the button after click
            } else {
                direction.value = "South"
                if (Random.nextBoolean()) {
                    treasuresFound += 1
                    stormOrTreasure.value = "Found a Treasure!"
                } else {
                    stormOrTreasure.value = "Storm Ahead!"
                    healthPoints -= 10
                    if (healthPoints == 0) {
                        stormOrTreasure.value += "\nGAME OVER!"
                        isEnabled = false // Disable the button after click
                    }
                }
            }
        },
            enabled = isEnabled // Control enabled state using the state variable
        ){
            Text("Sail South")
        }
        /*
        } while (healthPoints > 0)

        Text("GAME OVER! HP = $healthPoints")
        */

    }
}
}