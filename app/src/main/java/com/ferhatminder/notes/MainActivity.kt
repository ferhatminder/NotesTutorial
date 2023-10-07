package com.ferhatminder.notes

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ferhatminder.notes.ui.theme.NotesTheme

class MainActivity : AppCompatActivity() {


    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val text = findViewById<TextView>(R.id.text_view)
        val button = findViewById<Button>(R.id.button)
        val composeView = findViewById<ComposeView>(R.id.compose)

        text.text = "Hello World!"
        button.setOnClickListener {
            val btn = it as Button
            counter++
            btn.text = counter.toString()
        }

        composeView.setContent {
            Greeting(
                modifier = Modifier
                    .fillMaxSize(),
                name = "Ferhat"
            )
        }

    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotesTheme {
        Greeting("Android")
    }
}