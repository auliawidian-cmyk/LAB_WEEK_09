package com.example.lab_week_09

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import com.example.lab_week_09.ui.theme.LAB_WEEK_09Theme

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

// Data model Student (Part 2)
data class Student(var name: String)

@Composable
fun Home(modifier: Modifier = Modifier) {
    // mutable state list of Student
    val listData = remember {
        mutableStateListOf(
            Student("Tanu"),
            Student("Tina"),
            Student("Tono")
        )
    }

    // mutable state for input field
    var inputField by remember { mutableStateOf(Student("")) }

    // panggil HomeContent (child) -- parent mengontrol state & handler
    HomeContent(
        listData = listData,
        inputField = inputField,
        onInputValueChange = { input -> inputField = inputField.copy(name = input) },
        onButtonClick = {
            // validasi, jangan tambahkan jika kosong / hanya whitespace
            if (inputField.name.isNotBlank()) {
                listData.add(inputField)
                inputField = Student("") // reset input
            }
        },
        modifier = modifier
    )
}

@Composable
fun HomeContent(
    listData: SnapshotStateList<Student>,
    inputField: Student,
    onInputValueChange: (String) -> Unit,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // gunakan string resource dari modul (pastikan strings.xml ada)
                Text(
                    text = stringResource(id = R.string.enter_item),
                    style = MaterialTheme.typography.titleLarge
                )

                TextField(
                    value = inputField.name,
                    onValueChange = { onInputValueChange(it) },
                    modifier = Modifier.padding(top = 8.dp)
                )


                Button(
                    onClick = { onButtonClick() },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text(text = stringResource(id = R.string.button_click))
                }
            }
        }

        // tampilkan semua item dari listData
        items(listData) { student ->
            Column(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = student.name)
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