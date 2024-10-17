package com.example.a3usecasebasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppLayout(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun AppLayout(
    modifier: Modifier = Modifier,
    names: List<String> = listOf("1", "2")
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = modifier.padding(vertical = 4.dp)) {
            for (item in names) {
                Greeting("Android")
            }
        }
    }
}


/**
 *  function is screen with text
 */
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    // Don't do this! -> val expanded = mutableStateOf(false)
    // using remember because is used to guard against recomposition, so the state is not reset
    val expanded = remember { mutableStateOf(false) } // 變化展開和縮小視窗的按鈕
    val extraPadding = if (expanded.value) 48.dp else 0.dp // 展開和縮小視窗

    Surface(
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp),
        color = MaterialTheme.colorScheme.primary
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier.weight(1f)
                    .padding(bottom = extraPadding)) {
                Text(text = "Hello")
                Text(text = name)
            }

            ElevatedButton(onClick = { expanded.value = !expanded.value }) {
                Text(if (expanded.value) "Show less" else "Show more")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BasicsPreview() {
    AppLayout()
}