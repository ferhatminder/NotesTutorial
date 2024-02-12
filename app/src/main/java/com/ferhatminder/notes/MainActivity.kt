package com.ferhatminder.notes

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ferhatminder.notes.ui.theme.NotesTheme

class MainActivity : AppCompatActivity() {


    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val text = findViewById<TextView>(R.id.text_view)
        val button = findViewById<Button>(R.id.button)

        text.text = "Hello World!"
        button.setOnClickListener {
            val btn = it as Button
            counter++
            btn.text = counter.toString()
        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotesTheme {
        Greeting("Android")
    }
}