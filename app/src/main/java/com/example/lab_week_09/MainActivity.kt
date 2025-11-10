package com.example.lab_week_09

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab_week_09.ui.theme.LAB_WEEK_09Theme
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LAB_WEEK_09Theme {
                // Surface sebagai container utama
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Scaffold untuk layout dasar (toolbar / floating action nanti)
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        // panggil Home dan teruskan padding
                        Home(modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}

@Composable
fun Home(modifier: Modifier = Modifier) {
    // contoh state sederhana untuk TextField
    val (text, setText) = remember { mutableStateOf("") }

    // Column untuk layout vertikal
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // judul (ambil dari strings.xml)
        Text(text = stringResource(id = R.string.list_title), style = MaterialTheme.typography.titleLarge)

        // input contoh
        TextField(
            value = text,
            onValueChange = setText,
            modifier = Modifier.padding(top = 8.dp)
        )

        // tombol contoh menambahkan (fungsi kosong untuk demonstrasi)
        Button(onClick = { /* TODO: tambahkan aksi */ }, modifier = Modifier.padding(top = 8.dp)) {
            Text(text = stringResource(id = R.string.button_click))
        }

        // contoh daftar sederhana (LazyColumn)
        LazyColumn(modifier = Modifier.padding(top = 12.dp)) {
            // contoh statis — nanti ganti dengan items(list)
            item {
                Text(text = "Tanu")
                Text(text = "Tina")
                Text(text = "Tono")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LAB_WEEK_09Theme {
        Home()
    }
}